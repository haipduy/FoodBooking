package fptu.summer.skypeapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.presenter.HistoryPresenter;
import fptu.summer.skypeapp.view.adapter.OrderAdapter;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.view.interface_view.HistoryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements HistoryView {

    private RecyclerView listOrderView;
    private OrderService oService;
    private OrderAdapter oAdapter;
    private TextView txtViewMore;
    private HistoryPresenter historyPresenter;

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

        txtViewMore = findViewById(R.id.txtViewMore);
        listOrderView = findViewById(R.id.listOrderView);
        initHistoryPresenter();
        refreshViewHistory();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshViewHistory();
    }

    public void initHistoryPresenter() {
        historyPresenter = new HistoryPresenter(this);
    }

    @Override
    public void displayHistoryList(List<Order> orderList) {
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        listOrderView.setLayoutManager(linearLayoutManager);
        oAdapter = new OrderAdapter(getApplicationContext(), orderList);
        listOrderView.setAdapter(oAdapter);
    }

    public void refreshViewHistory() {
        if (MainActivity.account != null) {
            txtViewMore.setVisibility(View.GONE);
            String id = MainActivity.account.getUserId();
            if (id != null) {
                historyPresenter.loadHistory(id);
            }

        } else {
            txtViewMore.setVisibility(View.VISIBLE);
        }
    }


}
