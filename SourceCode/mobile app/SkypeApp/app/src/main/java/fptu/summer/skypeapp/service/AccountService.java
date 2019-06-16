package fptu.summer.skypeapp.service;

import java.util.List;
import java.util.Map;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountService {
    @POST("account/login")
    Call<Account> checkLogin(@Body Map<String, String> params);
}
