package fptu.summer.skypeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.AccountRoom;
import fptu.summer.skypeapp.model.Product;
import fptu.summer.skypeapp.persistence.ProductDBAdapter;
import fptu.summer.skypeapp.remote.AccountDatabase;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.service.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static fptu.summer.skypeapp.constants.RetrofitConstants.TIME_OUT;


public class MainActivity extends MasterActivity implements SearchView.OnQueryTextListener {


    private RecyclerView listView;
    private ProductService pService;
    private SearchView searchView;
    private ProductDBAdapter adapter;
    public static Account account = null;
    AccountDatabase accountDatabase;
    AccountService accountService;
    AccountRoom accRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ----------------
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_homes:

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_favorites:
                        intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);

                        break;
                }
                return true;
            }
        });


        //-------------
        searchView = findViewById(R.id.txtSearch);
        searchView.setOnQueryTextListener(this);

        listView = findViewById(R.id.listView);

        pService = APIUtils.getSOService();

        pService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    LinearLayoutManager linearLayoutManager
                            = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                    listView.setLayoutManager(linearLayoutManager);

                    adapter = new ProductDBAdapter(getApplicationContext(), response.body());

                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });

        getUserAccount();


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (listView != null) {
            adapter.filer(newText);
        }
        return false;
    }

    public void getUserAccount() {
        accountDatabase = AccountDatabase.getInstance(this);
        AsyncTask<Void, Void, AccountRoom> asyncTask = new AsyncTask<Void, Void, AccountRoom>() {
            @Override
            protected AccountRoom doInBackground(Void... voids) {
                accRoom = accountDatabase.accountDAO().getAccount();
                return accRoom;
            }

            @Override
            protected void onPostExecute(AccountRoom accRoom) {
                if (accRoom != null) {
                    if (new Date().getTime() - accRoom.getTimeLogin() < TIME_OUT) {
                        Toast.makeText(getApplicationContext(), accRoom.getTimeLogin() + "", Toast.LENGTH_SHORT).show();
                        Map<String, String> mAccount = new HashMap<>();
                        mAccount.put("username", accRoom.getUserId());
                        mAccount.put("password", accRoom.getUserPassword());
                        exercuteAsyncTask(mAccount);
                    }
                }
            }
        };
        asyncTask.execute();
    }

    public void exercuteAsyncTask(Map<String, String> mAccount) {
        accountDatabase = AccountDatabase.getInstance(this);
        accountService = APIUtils.checkLoginService();
        accountService.checkLogin(mAccount).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    MainActivity.account = response.body();

                    AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            AccountRoom accountRoom = new AccountRoom();
                            accountRoom.setUserId(accRoom.getUserId());
                            accountRoom.setUserPassword(accRoom.getUserPassword());
                            accountRoom.setTimeLogin(new Date().getTime());
                            accountDatabase.accountDAO().insertAccountRoom(accountRoom);
                            return true;
                        }
                    };
                    asyncTask.execute();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "can't connect", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
