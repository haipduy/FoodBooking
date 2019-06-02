package fptu.summer.foodcourseapp.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // cau hinh retrofit (k can sua, chi thay ipv4)
    private static Retrofit retrofit = null;
    public static String baseUrl = "http://192.168.1.145:8080/";
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
