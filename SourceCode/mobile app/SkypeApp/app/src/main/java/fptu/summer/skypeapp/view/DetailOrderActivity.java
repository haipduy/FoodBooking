package fptu.summer.skypeapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.presenter.DetailOrderPresenter;
import fptu.summer.skypeapp.view.adapter.DetailOrderAdapter;
import fptu.summer.skypeapp.view.interface_view.DetailListOrderView;

import static fptu.summer.skypeapp.utils.BundleString.*;

public class DetailOrderActivity extends AppCompatActivity implements DetailListOrderView {

    private Order order;
    private TextView txtDetailOrderTitle, txtDetailOrderId, txtDetailOrderPrice, txtDetailOrderDate;
    private RecyclerView listDetailOrder;
    private DetailOrderPresenter detailOrderPresenter;
    private DetailOrderAdapter detailOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        initDetailOrderPresenter();
        innitView();
        innitData();


    }

    private void innitView() {
        txtDetailOrderTitle = findViewById(R.id.txtDetailOrderTitle);
        txtDetailOrderId = findViewById(R.id.txtDetailOrderId);
        txtDetailOrderPrice = findViewById(R.id.txtDetailOrderPrice);
        txtDetailOrderDate = findViewById(R.id.txtDetailOrderDate);
        listDetailOrder = findViewById(R.id.listDetailOrder);
    }

    private void innitData() {
        order = (Order) getIntent().getSerializableExtra(ORDER_CODE);
        txtDetailOrderTitle.setText("Order #" + order.getOrderId());
        txtDetailOrderId.setText("OrderId #" +order.getOrderId());
        txtDetailOrderPrice.setText(order.getTotal() + "00");
        txtDetailOrderDate.setText(order.getOrderDate() + "");
        detailOrderPresenter.loadDetailListOrder(order.getOrderId());
    }
    public void initDetailOrderPresenter(){
        detailOrderPresenter = new DetailOrderPresenter(this);
    }

    @Override
    public void displayDetailOrderList(List<DetailOrder> detailOrderList) {
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        listDetailOrder.setLayoutManager(linearLayoutManager);
        detailOrderAdapter = new DetailOrderAdapter(getApplicationContext(), detailOrderList);
        listDetailOrder.setAdapter(detailOrderAdapter);
    }
}
