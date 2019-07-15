package fptu.summer.skypeapp.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import fptu.summer.skypeapp.model.entity.BankAccount;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.view.CamViewActivity;
import fptu.summer.skypeapp.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamViewPresenter {

    private Context context;
    private BankService bankService;

    public static Boolean resultUpdateMoney = false;

    public CamViewPresenter(Context context) {
        this.context = context;
    }

    public void updateMoneyBank(int bankId, float amount) {
        Map<String, String> bank = new HashMap<>();
        bank.put("money", amount + "");
        bankService = APIUtils.bankService();
        bankService.updateBankAccountById(bankId, bank).enqueue(new Callback<BankAccount>() {
            @Override
            public void onResponse(Call<BankAccount> call, Response<BankAccount> response) {
                if (response.isSuccessful()) {
                    MainActivity.bankAccount = response.body();
                    Toast.makeText(context, "Đã nộp thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BankAccount> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
