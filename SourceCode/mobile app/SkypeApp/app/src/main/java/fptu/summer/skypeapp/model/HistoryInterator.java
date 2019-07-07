package fptu.summer.skypeapp.model;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.List;

import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.service.ProductService;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.view.HistoryActivity;
import fptu.summer.skypeapp.view.adapter.OrderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryInterator {

    private OrderService oService;
    private LoadHistoryListener historyListener;
    private List<Order> orderList;

    public HistoryInterator(LoadHistoryListener historyListener) {
        this.historyListener = historyListener;
    }

    public void loadListProduct(String id) {
        oService = APIUtils.getOrderService();
        oService.getListOrderByUserId(id).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    orderList = response.body();
                    historyListener.onLoadOrderSuccess(orderList);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                historyListener.onLoadOrderFailure("Can't call api");
            }
        });
    }
}
