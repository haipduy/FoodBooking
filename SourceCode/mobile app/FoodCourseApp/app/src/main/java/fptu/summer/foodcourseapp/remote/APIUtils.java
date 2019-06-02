package fptu.summer.foodcourseapp.remote;

public class APIUtils {
    public static ProductService getSOService() {
        return RetrofitClient.getClient().create(ProductService.class);
    }
}
