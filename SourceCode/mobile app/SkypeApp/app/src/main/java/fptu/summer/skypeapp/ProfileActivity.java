package fptu.summer.skypeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.AccountRoom;
import fptu.summer.skypeapp.remote.AccountDatabase;
import fptu.summer.skypeapp.service.AccountService;


public class ProfileActivity extends AppCompatActivity {

    AccountService accountService;
    AccountDatabase accountDatabase;
    AccountRoom accRoom;
    Button btnLogin;
    String username = null;
    Account account;

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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 123);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        account = MainActivity.account;
        if (account != null) {
            btnLogin.setText(account.getUserName());
            btnLogin.setEnabled(false);
        } else {
            setTextLoginForButton();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

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
        setTextLoginForButton();

    }

    public void setTextLoginForButton() {
        btnLogin.setText("Login");
        btnLogin.setEnabled(true);
    }

    public void clickToViewCredit(View view) {
        account = MainActivity.account;
        if(account !=null){
            Intent intent = new Intent(getApplicationContext(), CreditActivity.class);
            intent.putExtra("ACCOUNT", account);
            startActivity(intent);
        }else{
            //di den trang login
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
