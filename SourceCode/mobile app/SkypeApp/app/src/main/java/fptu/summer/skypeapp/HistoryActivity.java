package fptu.summer.skypeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import fptu.summer.skypeapp.model.Order;
import fptu.summer.skypeapp.persistence.OrderAdapter;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView listOrderView;
    private OrderService oService;
    private OrderAdapter oAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_orders);
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

        listOrderView = findViewById(R.id.listOrderView);
        if(MainActivity.account != null){
            String id =  MainActivity.account.getUserId();
            if(id !=null){
                Toast.makeText(HistoryActivity.this, id,Toast.LENGTH_SHORT).show();

                oService = APIUtils.getOrderService();
                oService.getListOrderByUserId(id).enqueue(new Callback<List<Order>>(){
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(HistoryActivity.this, "Goi duoc roi+"+response.body().size(),Toast.LENGTH_SHORT).show();
                            LinearLayoutManager linearLayoutManager
                                    = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
                            listOrderView.setLayoutManager(linearLayoutManager);
                            oAdapter = new OrderAdapter(getApplicationContext(), response.body());
                            listOrderView.setAdapter(oAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(HistoryActivity.this, "Goi api fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }else{
            Toast.makeText(HistoryActivity.this, "Login de xem noi dung nay",Toast.LENGTH_SHORT).show();
        }
    }

}
