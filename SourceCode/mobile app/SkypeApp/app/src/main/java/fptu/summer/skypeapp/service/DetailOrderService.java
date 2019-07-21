package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.model.entity.Order;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailOrderService {
    @GET("detailorder/{id}")
    Call<List<DetailOrder>> getListDetailOrderByOrderId(@Path("id") int id);
}
