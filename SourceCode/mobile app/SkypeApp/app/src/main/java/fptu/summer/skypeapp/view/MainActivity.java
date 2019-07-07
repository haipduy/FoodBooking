package fptu.summer.skypeapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.AccountRoom;
import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.view.adapter.ProductDBAdapter;
import fptu.summer.skypeapp.database.AccountDatabase;
import fptu.summer.skypeapp.presenter.ProductPresenter;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.service.ProductService;
import fptu.summer.skypeapp.view.interface_view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static fptu.summer.skypeapp.constants.RetrofitConstants.TIME_OUT;


public class MainActivity extends MasterActivity implements SearchView.OnQueryTextListener, MainView {
    ImageView imgImageViewCart;

    private RecyclerView listView;
    private ProductService pService;
    private SearchView searchView;
    private ProductDBAdapter adapter;
    public static Account account = null;
    AccountDatabase accountDatabase;
    AccountService accountService;
    AccountRoom accRoom;
    ProductPresenter productPresenter;

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector  barcodeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
        textView = (TextView)findViewById(R.id.scanner);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this,barcodeDetector)
                .setRequestedPreviewSize(640,480).build();
        // ----------------
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_homes);
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

        //-------------
        imgImageViewCart = findViewById(R.id.imgCart);
        searchView = findViewById(R.id.txtSearch);
        searchView.setOnQueryTextListener(this);

        listView = findViewById(R.id.listView);
        initPresenter();
        productPresenter.loadData();

        getUserAccount();


    }

    public void initPresenter() {
        productPresenter = new ProductPresenter(this);
    }

    @Override
    public void displayListProduct(List<Product> productList) {
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);
        adapter = new ProductDBAdapter(getApplicationContext(), productList);
        listView.setAdapter(adapter);
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
        accountService = APIUtils.accountService();
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

    public void clickToViewCart(View view) {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
