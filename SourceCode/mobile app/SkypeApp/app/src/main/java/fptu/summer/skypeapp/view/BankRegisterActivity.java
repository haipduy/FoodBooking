package fptu.summer.skypeapp.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.BankAccount;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankRegisterActivity extends AppCompatActivity {

    private EditText edtBanlkFullName, edtBankNumber, edtBankExpired;
    private Button btnRegisterBank;
    private BankService bankService;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_register);
        initView();
        initData();
    }

    public void initView() {
        edtBanlkFullName = findViewById(R.id.edtBanlkFullName);
        edtBankNumber = findViewById(R.id.edtBankNumber);

        edtBankExpired = findViewById(R.id.edtBankExpired);

        btnRegisterBank = findViewById(R.id.btnRegisterBank);
    }
    public void initData(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        edtBankExpired.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(BankRegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtBankExpired.setText(sdf.format(myCalendar.getTime()));
    }


    public void clickToRegisterBank(View view) {
        String fullname = edtBanlkFullName.getText().toString().trim();
        String bankNumber = edtBankNumber.getText().toString().trim();
        String bankExpired = edtBankExpired.getText().toString().trim();
        final boolean result = valiRegisterForm(fullname, bankNumber, bankExpired);
        if (result) {

            String userId = MainActivity.account.getUserId();
            int number = Integer.parseInt(bankNumber);

            BankAccount bankAccount = new BankAccount(userId, fullname, number, 0, bankExpired, 1);
            bankService = APIUtils.bankService();
            bankService.createBankAccount(bankAccount).enqueue(new Callback<BankAccount>() {
                @Override
                public void onResponse(Call<BankAccount> call, Response<BankAccount> response) {
                    if(response.isSuccessful()){
                        MainActivity.bankAccount = response.body();
                        showDialog("Tạo tài khoản ngân hàng thành công");
                        Intent intent = new Intent(BankRegisterActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        showDialog("Tài khoản đã tồn tại.");
                    }
                }

                @Override
                public void onFailure(Call<BankAccount> call, Throwable t) {
                    showDialog("Tạo tài khoản không thành công.");
                }
            });
        }

    }

    public boolean valiRegisterForm(String fullname, String bankNumber, String bankExpired) {
        if (fullname.length() == 0) {
            showDialog("Tên không được để trống");
            return false;
        }
        if (bankNumber.length() == 0) {
            showDialog("Số tài khoản không được để");
            return false;
        }
        if (bankExpired.length() == 0) {
            showDialog("Vui lòng chọn ngày hết hạn của tài khoản");
            return false;
        }
        return true;
    }

    public void showDialog(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
