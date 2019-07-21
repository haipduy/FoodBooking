package fptu.summer.skypeapp.service;

import java.util.Map;

import fptu.summer.skypeapp.model.entity.BankAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BankService {
    @GET("bankaccounts/{id}")
    Call<BankAccount> getBankAccountByUserId(@Path("id") String id);

    @PUT("bankaccounts/{id}")
    Call<BankAccount> updateBankAccountById(@Path("id") int id,@Body Map<String, String> params);

    @POST("bankaccounts")
    Call<BankAccount> createBankAccount(@Body BankAccount bankAccount);

}
