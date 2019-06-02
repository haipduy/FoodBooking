package fptu.summer.foodcourseapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fptu.summer.foodcourseapp.model.Product;
import fptu.summer.foodcourseapp.remote.APIUtils;
import fptu.summer.foodcourseapp.remote.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private TextView mTextMessage;
    private RecyclerView listView;
    private ProductService pService;
    private SearchView searchView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        listView = findViewById(R.id.listView);
        mTextMessage = findViewById(R.id.message);

        //Set Data
                pService = APIUtils.getSOService();
        // lay service ra,
        pService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false);
                    listView.setLayoutManager(linearLayoutManager);
                     adapter = new Adapter(getApplicationContext(),response.body());
                    listView.setAdapter(adapter);
                }else {
                    int statusCode  = response.code();
                    Toast.makeText(MainActivity.this, statusCode+"", Toast.LENGTH_SHORT).show();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
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
