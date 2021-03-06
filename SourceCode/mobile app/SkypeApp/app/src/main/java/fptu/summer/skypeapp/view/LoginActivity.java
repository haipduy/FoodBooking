package fptu.summer.skypeapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.AccountRoom;
import fptu.summer.skypeapp.database.AccountDatabase;
import fptu.summer.skypeapp.model.entity.BankAccount;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.utils.BundleString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView txtSignUp;
    EditText edtUsername, edtPassword;
    Button btnLogin, btnCancel;
    AccountService accountService;
    AccountDatabase accountDatabase;
    BankService bankService;

    AccountRoom accRoom = null;
    String username = null;
    private String data;

    CheckBox ckShowPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
        initData();
//        data = (String) getIntent().getSerializableExtra(BundleString.BUNDLE_QR_CODE);

    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        ckShowPass = findViewById(R.id.show_hide_password);
        txtSignUp = findViewById(R.id.txtSignUp);
    }
    private void initData(){
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToSignUp();
            }
        });
    }
    public void clickToSignUp(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


    public void clickToLogin(View view) {
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        boolean valid = validdateLogin(username, password);
        if (valid) {
            Map<String, String> mAccount = new HashMap<>();
            mAccount.put("username", username);
            mAccount.put("password", password);

            accountDatabase = AccountDatabase.getInstance(this);

            accountService = APIUtils.accountService();

            accountService.checkLogin(mAccount).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        MainActivity.account = response.body();
                        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
                            @Override
                            protected Boolean doInBackground(Void... voids) {
                                accRoom = new AccountRoom();
                                accRoom.setUserId(username);
                                accRoom.setUserPassword(password);
                                accRoom.setTimeLogin(new Date().getTime());
                                accountDatabase.accountDAO().insertAccountRoom(accRoom);
                                return true;
                            }
                        };
                        String userId = MainActivity.account.getUserId();
                        loadBankAccount(userId);
                        asyncTask.execute();
                        setResult(RESULT_OK, null);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "can't connect", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please input username or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickToCancel(View view) {

    }

    public boolean validdateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            return false;
        }
        return true;
    }


    public void clickToShowPassword(View view) {
        if (ckShowPass.isChecked()) {
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void loadBankAccount(String userID) {
        bankService = APIUtils.bankService();
        bankService.getBankAccountByUserId(userID).enqueue(new Callback<BankAccount>() {
            @Override
            public void onResponse(Call<BankAccount> call, Response<BankAccount> response) {
                if (response.isSuccessful()) {

                    MainActivity.bankAccount = response.body();

                } else {
                    Toast.makeText(LoginActivity.this, "Ban chua co tai khoan thanh toan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BankAccount> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
