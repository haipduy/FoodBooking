package fptu.summer.skypeapp.service;

import java.util.Map;

import fptu.summer.skypeapp.model.entity.Account;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {
    @POST("account/login")
    Call<Account> checkLogin(@Body Map<String, String> params);
}
