package fptu.summer.skypeapp.utils;

import fptu.summer.skypeapp.remote.RetrofitClient;
import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.service.ProductService;
import retrofit2.Retrofit;

public class APIUtils {
    public static ProductService getSOService() {
        return RetrofitClient.getClient().create(ProductService.class);
    }
    public static OrderService getOrderService(){
        return RetrofitClient.getClient().create(OrderService.class);
    }
    public static AccountService accountService(){
        return  RetrofitClient.getClient().create(AccountService.class);
    }
    public static BankService bankService(){
        return  RetrofitClient.getClient().create(BankService.class);
    }
}
