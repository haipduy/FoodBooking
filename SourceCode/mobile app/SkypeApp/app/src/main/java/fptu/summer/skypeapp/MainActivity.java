package fptu.summer.skypeapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;


import java.util.List;

import fptu.summer.skypeapp.model.Product;
import fptu.summer.skypeapp.persistence.ProductDBAdapter;
import fptu.summer.skypeapp.utils.APIUtils;
import fptu.summer.skypeapp.service.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends MasterActivity implements SearchView.OnQueryTextListener{


    private RecyclerView listView;
    private ProductService pService;
    private SearchView searchView;
    private ProductDBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.txtSearch);
        searchView.setOnQueryTextListener(this);

        listView = findViewById(R.id.listView);

        pService = APIUtils.getSOService();

        pService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    LinearLayoutManager linearLayoutManager
                            = new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false);
                    listView.setLayoutManager(linearLayoutManager);

                    adapter = new ProductDBAdapter(getApplicationContext(),response.body());

                    listView.setAdapter(adapter);
                }else {
                    int statusCode  = response.code();

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(listView!= null){
            adapter.filer(newText);
        }
        return false;
    }
}
