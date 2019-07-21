package fptu.summer.skypeapp.model;

import java.util.List;

import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.service.DetailOrderService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderInterator {

    private DetailOrderService detailOrderService;
    private LoadDetailOrderListener detailOrderListener;
    private List<DetailOrder> detailOrderList;

    public DetailOrderInterator(LoadDetailOrderListener detailOrderListener) {
        this.detailOrderListener = detailOrderListener;
    }

    public void loadListDetailOrder(int id){
        detailOrderService = APIUtils.getDetailOrderService();
        detailOrderService.getListDetailOrderByOrderId(id).enqueue(new Callback<List<DetailOrder>>() {
            @Override
            public void onResponse(Call<List<DetailOrder>> call, Response<List<DetailOrder>> response) {
                if (response.isSuccessful()) {
                    detailOrderList = response.body();
                    detailOrderListener.onLoadDetailOrderSuccess(detailOrderList);
                }
            }

            @Override
            public void onFailure(Call<List<DetailOrder>> call, Throwable t) {
                detailOrderListener.onLoadDetailOrderFailure("Không thể gọi api.");
            }
        });
    }
}
