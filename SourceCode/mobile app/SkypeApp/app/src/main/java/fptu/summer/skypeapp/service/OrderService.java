package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.ItemModel;
import fptu.summer.skypeapp.model.ItemRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface OrderService {
    @POST("orders")
    Call<Void> creataNewOrder(@Body ItemRequestModel listProduct);

}
