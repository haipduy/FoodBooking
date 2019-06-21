package fptu.summer.skypeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.AccountRoom;
import fptu.summer.skypeapp.remote.AccountDatabase;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    AccountService accountService;
    EditText edtUsername,edtPassword;
    Button btnLogin,btnCancel;
    AccountDatabase accountDatabase;
    AccountRoom accRoom = null;
    String username = null;
    LoginButton btnLoginFacebook;
    CallbackManager callbackManager;
    public static GoogleSignInClient mGoogleSignInClient;



    CheckBox ckShowPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        edtUsername= findViewById(R.id.edtUsername);
        edtPassword= findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        ckShowPass = findViewById(R.id.show_hide_password);


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.login_layout);
        btnLoginFacebook = (LoginButton) findViewById(R.id.login_button);
        ((LoginButton) btnLoginFacebook).registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login attempt canceled.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Login attempt failed.", Toast.LENGTH_LONG).show();
            }
        });
        setUpGmail();


    }




    public void clickToLogin(View view) {
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        boolean valid = validdateLogin(username,password);
        if(valid){
            Map<String,String> mAccount = new HashMap<>();
            mAccount.put("username", username);
            mAccount.put("password", password);

            accountDatabase = AccountDatabase.getInstance(this);

            accountService = APIUtils.checkLoginService();

            accountService.checkLogin(mAccount).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.isSuccessful()){
                        MainActivity.account = response.body();
                        AsyncTask<Void,Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
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
                        asyncTask.execute();
                        setResult(RESULT_OK, null);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "can't connect", Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Please input username or password", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickToCancel(View view) {

    }

    public boolean validdateLogin(String username, String password){
        if (username ==null || username.trim().length() == 0){
            return false;
        }
        if(password == null ||password.trim().length() == 0){
            return false;
        }
        return true;
    }


    int RC_SIGN_IN = 1;
    private void setUpGmail(){
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.revokeAccess();
        signInButton.setOnClickListener(this);
    }
    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else{
            //FB
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this, "signInResult:failed code=" + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null)
        Toast.makeText(this, account.getEmail(), Toast.LENGTH_SHORT).show();
    }

    public void clickToShowPassword(View view) {
        if (ckShowPass.isChecked()){
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
