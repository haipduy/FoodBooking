package fptu.summer.skypeapp.service;

import fptu.summer.skypeapp.model.entity.BankAccount;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankService {
    @GET("bankaccounts/{id}")
    Call<BankAccount> getBankAccountByUserId(@Path("id") String id);
}
