package fptu.summer.skypeapp.utils;

import fptu.summer.skypeapp.service.AccountService;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.service.DetailOrderService;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.service.ProductService;

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
    public static DetailOrderService getDetailOrderService(){
        return  RetrofitClient.getClient().create(DetailOrderService.class);
    }
}
