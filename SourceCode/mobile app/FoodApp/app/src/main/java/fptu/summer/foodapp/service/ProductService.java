package fptu.summer.foodapp.service;

import java.util.List;

import fptu.summer.foodapp.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    // lay all product
    @GET("/products")
    Call<List<Product>> getProducts();

}
