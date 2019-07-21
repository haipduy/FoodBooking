package fptu.summer.skypeapp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    AccountService accountService;

    EditText edtSUsername, edtSName, edtSPassword, edtSConfirm, edtSAddress, edtSPhone, edtSEmail;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {

        edtSUsername = findViewById(R.id.edtSUsername);
        edtSName = findViewById(R.id.edtSName);
        edtSPassword = findViewById(R.id.edtSPassword);
        edtSConfirm = findViewById(R.id.edtSConfirm);
        edtSAddress = findViewById(R.id.edtSAddress);
        edtSPhone = findViewById(R.id.edtSPhone);
        edtSEmail = findViewById(R.id.edtSEmail);
        btnRegister = findViewById(R.id.btnRegister);

    }

    private void initData() {

    }

    public void clickToSignUp(View view) {
        String username = edtSUsername.getText().toString().trim();
        String fullname = edtSName.getText().toString().trim();
        String password = edtSPassword.getText().toString().trim();
        String confirmpassword = edtSConfirm.getText().toString().trim();
        String address = edtSAddress.getText().toString().trim();
        String phone = edtSPhone.getText().toString().trim();
        String email = edtSEmail.getText().toString().trim();

        boolean result = valiRegisterForm(username,fullname,password,confirmpassword,address,phone,email);
        if(result){
            int phoneNumber = Integer.parseInt(phone);
            Account accountToRegister = new Account(username,password,fullname,address,phoneNumber,email);
            //call api to i nser new
            accountService = APIUtils.accountService();
            accountService.registerAccount(accountToRegister).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(response.isSuccessful()){
                        // thêm tai khoan ngan hàng

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        showDialog("Bạn đã tạo tài khoản thành công.");
                        finish();
                    }else{
                        showDialog("Tên đăng nhập đã tồn lại, xin hãy thử lại.");
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });

        }

    }
    public boolean valiRegisterForm(String username, String fullname, String password, String confirm, String address, String phone, String email){
        if (username.length() == 0){
            showDialog("Tên đăng nhập không được để trống");
            return false;
        }
        if(fullname.length() == 0){
            showDialog("Họ và tên không được để trống");
            return false;
        }
        if(password.length() == 0){
            showDialog("Mật khẩu không được để trống");
            return false;
        }
        if(confirm.length() == 0){
            showDialog("Xác nhận mật khẩu không được để trống");
            return false;
        }
        if(!password.equals(confirm)){
            showDialog("Mật khẩu phải giống nhau");
            return false;
        }
        if(address.length() == 0){
            showDialog("Địa chỉ không được để trống");
            return false;
        }
        if(phone.length() == 0){
            showDialog("Số điện thoại không được để trống");
            return false;
        }
        if(email.length() == 0){
            showDialog("Địa chỉ mail không được để trốngkhông được để trống");
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
