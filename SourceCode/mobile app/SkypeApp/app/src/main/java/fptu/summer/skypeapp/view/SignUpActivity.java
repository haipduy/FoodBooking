package fptu.summer.skypeapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import fptu.summer.skypeapp.database.AccountDatabase;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.AccountRoom;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.utils.BundleString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    View view;
    EditText fullName, emailId, mobileNumber,password, confirmPassword;
    TextView login;
    Button signUpButton;
    CheckBox terms_conditions;
    AccountService accountService;
    AccountDatabase accountDatabase;
    AccountRoom accRoom = null;
    private String data;
    private String fullnameString;
    private String passwordString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);
        data = (String) getIntent().getSerializableExtra(BundleString.BUNDLE_QR_CODE);
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();

    }

    public void clickOnSignUp(View view) {
        fullnameString = fullName.getText().toString();
        final String emailid = emailId.getText().toString();
        final String mobilenumber = mobileNumber.getText().toString();
        passwordString = password.getText().toString();
        final String confipassword = confirmPassword.getText().toString();

        boolean valid = checkValidation(fullnameString,emailid,mobilenumber,passwordString,confipassword);
        if (valid) {
            Map<String,String> mAccount = new HashMap<>();
            mAccount.put("fullname", fullnameString);
            mAccount.put("email",emailid);
            mAccount.put("mobilenumber",mobilenumber);
            mAccount.put("password",passwordString);
            mAccount.put("confirmpassword",confipassword);

            accountDatabase = AccountDatabase.getInstance(this);
            accountService = APIUtils.accountService();
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accRoom = new AccountRoom();
//                    accRoom.setFullname(fullnameString);
                    accRoom.setUserPassword(passwordString);
                    accRoom.setTimeLogin(new Date().getTime());
                    accountDatabase.accountDAO().insertAccountRoom(accRoom);
                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                    return;

                }
            });

        }

    }

    private boolean checkValidation(String fullname, String emailid, String mobilenumber, String passWord, String confipassword) {
        if (fullname == null || fullname.trim().length() == 0) {
            return false;
        }
        if (emailid == null || emailid.trim().length() == 0){
            return false;
        }
        if (mobilenumber == null || mobilenumber.trim().length() == 0){
            return false;
        }
        if (passWord == null || passWord.trim().length() == 0){
            return false;
        }
        if (confipassword == null || confipassword.trim().length() == 0){
            return false;
        }
        return true;
    }


}
