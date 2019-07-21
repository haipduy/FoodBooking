package fptu.summer.skypeapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;

public class InforActivity extends AppCompatActivity {

    private TextView txtAccountName,txtAUsername;
    private TextView txtAName,txtAPhone, txtAEmail,txtAAddress;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        initView();
        initData();
    }
    public void initView(){
        txtAccountName = findViewById(R.id.txtAccountName);
        txtAUsername = findViewById(R.id.txtAUsername);
        txtAName = findViewById(R.id.txtAName);
        txtAPhone = findViewById(R.id.txtAPhone);
        txtAEmail = findViewById(R.id.txtAEmail);
        txtAAddress = findViewById(R.id.txtAAddress);

    }
    public void initData(){
        account = MainActivity.account;
        txtAccountName.setText(account.getUserName());
        txtAUsername.setText(account.getUserId());
        txtAName.setText(account.getUserName());
        txtAPhone.setText(account.getPhone()+"");
        txtAEmail.setText(account.getEmail());
        txtAAddress.setText(account.getUserAddress());

    }
}
