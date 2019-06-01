package fptu.summer.foodapp.ultis;

import fptu.summer.foodapp.service.ProductService;

public class ProductUlti {
    public static ProductService getSOService() {
        return RetrofitClient.getClient().create(ProductService.class);
    }
}
