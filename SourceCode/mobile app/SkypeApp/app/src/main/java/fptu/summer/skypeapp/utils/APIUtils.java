package fptu.summer.skypeapp.utils;

import fptu.summer.skypeapp.remote.RetrofitClient;
import fptu.summer.skypeapp.service.ProductService;

public class APIUtils {
    public static ProductService getSOService() {
        return RetrofitClient.getClient().create(ProductService.class);
    }
}
