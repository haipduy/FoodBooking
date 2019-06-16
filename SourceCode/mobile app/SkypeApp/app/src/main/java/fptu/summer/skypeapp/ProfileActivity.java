package fptu.summer.skypeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class ProfileActivity extends AppCompatActivity {

    AccountService accountService;
    AccountDatabase accountDatabase;
    AccountRoom accRoom;
    Button btnLogin;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        if (MainActivity.account != null) {
            btnLogin.setText("Loged");
            btnLogin.setEnabled(false);
        }
        else{
            btnLogin.setText("Login");
            btnLogin.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }
}
