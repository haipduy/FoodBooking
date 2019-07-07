package fptu.summer.skypeapp.model;

import java.util.ArrayList;
import java.util.List;


import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.service.ProductService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductInterator {

    private ProductService productService;

    private LoadProductListener productListener;

    private List<Product> productList = new ArrayList<>();

    public ProductInterator(LoadProductListener productListener) {
        this.productListener = productListener;
    }

    public void loadListProduct(){

        productService = APIUtils.getSOService();
        productService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    productListener.onLoadProductSuccess(productList);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                productListener.onLoadProductFailure("Can't call api");

            }
        });
    }
}
