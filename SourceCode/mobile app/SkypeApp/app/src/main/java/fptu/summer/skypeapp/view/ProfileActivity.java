package fptu.summer.skypeapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.AccountRoom;
import fptu.summer.skypeapp.database.AccountDatabase;
import fptu.summer.skypeapp.service.AccountService;

import static fptu.summer.skypeapp.utils.BundleString.REQUEST_CODE_LOGIN;
import static fptu.summer.skypeapp.utils.BundleString.REQUEST_CODE_QRCODE;


public class ProfileActivity extends AppCompatActivity {

    AccountService accountService;
    AccountDatabase accountDatabase;
    AccountRoom accRoom;
    LinearLayout btnLogin, btnBankAccount;
    String username = null;
    Account account;
    TextView myAccount, myWallet;
    LinearLayout btnQRCode;

    String data = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_homes:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_orders:
                        intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_account:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);

                        break;
                }
                return true;
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnBankAccount = findViewById(R.id.btnBankAccount);

        myAccount = findViewById(R.id.myAccount);

        refreshView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_CODE_LOGIN);
            }
        });


//        data = (String) getIntent().getSerializableExtra(BUNDLE_QR_CODE);
//
//        if (data != null) {
//            QRCodeEncoder(data);
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshView();

    }

    private void QRCodeEncoder(String data) {
        Toast.makeText(ProfileActivity.this, data, Toast.LENGTH_SHORT).show();
    }


    public void clickToLogOut(View view) {

        MainActivity.account = null;
        accountDatabase = AccountDatabase.getInstance(this);
        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                accountDatabase.accountDAO().deleteAllAccount();
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (!aBoolean) {

                    Toast.makeText(ProfileActivity.this, "Log out fail", Toast.LENGTH_SHORT).show();

                }
            }
        };
        asyncTask.execute();
        refreshView();
    }

    public void refreshView() {
        account = MainActivity.account;
        if (account != null) {
            btnLogin.setVisibility(View.GONE);
            myAccount.setText(account.getUserName());
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            myAccount.setText("My Account");
        }

    }


    public void clickQRCode(View view) {
        if (MainActivity.account == null) {
            Intent  intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if(MainActivity.bankAccount!=null){
            //co account
            Intent intent = new Intent(ProfileActivity.this, CamViewActivity.class);
            startActivityForResult(intent, REQUEST_CODE_QRCODE);
        }else{
            Toast.makeText(ProfileActivity.this, "Bạn chưa có tài khoản ngân hàng", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickToviewBankAccount(View view) {
        if (MainActivity.account == null) {
          Intent  intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ProfileActivity.this, BankActivity.class);
            startActivity(intent);
        }


    }
}
