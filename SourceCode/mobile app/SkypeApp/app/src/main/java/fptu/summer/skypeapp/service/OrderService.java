package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.ItemModel;
import fptu.summer.skypeapp.model.ItemRequestModel;
import fptu.summer.skypeapp.model.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @POST("orders")
    Call<Void> creataNewOrder(@Body ItemRequestModel listProduct);

    @GET("accounts/{id}/orders")
    Call<List<Order>> getListOrderByUserId(@Path("id") String id);

}
