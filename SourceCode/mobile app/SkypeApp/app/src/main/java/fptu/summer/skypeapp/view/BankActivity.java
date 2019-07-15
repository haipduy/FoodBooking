package fptu.summer.skypeapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.BankAccount;

public class BankActivity extends AppCompatActivity {

    private TextView txtAmount, txtNameBankAcc, txtNumberBank, txtExpiredDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        initialView();
        initialData();
    }
    public void initialView() {
        txtAmount = findViewById(R.id.txtAmount);
        txtNameBankAcc = findViewById(R.id.txtNameBankAccount);
        txtNumberBank = findViewById(R.id.txtNumberBank);
        txtExpiredDate = findViewById(R.id.txtExpiredDate);
    }

    public void initialData() {

        BankAccount accountBank = MainActivity.bankAccount;
        if (accountBank != null) {
            txtAmount.setText((int) accountBank.getAccMoney() + " Dong");
            txtNameBankAcc.setText(accountBank.getAccName());
            txtNumberBank.setText(accountBank.getAccCardNo() + "");
            txtExpiredDate.setText(accountBank.getExpiredDate() +"");
        } else {
            txtAmount.setText("No data");
            txtNameBankAcc.setText("No data");
            txtNumberBank.setText("No data");
            txtExpiredDate.setText("No data");
        }
    }
}
