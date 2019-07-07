package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    // lay all product
    @GET("products")
    Call<List<Product>> getProducts();

}
