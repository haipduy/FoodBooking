package fptu.summer.skypeapp.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static fptu.summer.skypeapp.constants.RetrofitConstants.BASE_URL;

public class RetrofitClient {

    private static Retrofit retrofit = null;
//    public static String baseUrl = "http://192.168.100.18:8080/";
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
