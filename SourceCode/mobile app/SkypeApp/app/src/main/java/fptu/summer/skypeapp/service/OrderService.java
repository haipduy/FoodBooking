package fptu.summer.skypeapp.service;

import java.util.List;
import java.util.Map;

import fptu.summer.skypeapp.model.ItemRequestModel;
import fptu.summer.skypeapp.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @POST("/orders")
    void creataNewOrder(
            @Path("userId")String userId,
            @Path("total")float total,
            @Path("notes")String notes,
            @Body List<ItemRequestModel> listProduct
    );
//    @GET("products")
//    Call<List<Product>> getProducts();
}
