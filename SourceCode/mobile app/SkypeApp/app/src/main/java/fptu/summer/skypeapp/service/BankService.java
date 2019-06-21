package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.BankAccount;
import fptu.summer.skypeapp.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankService {
    @GET("bankaccounts/{id}")
    Call<BankAccount> getBankAccountByUserId(@Path("id") String id);
}
