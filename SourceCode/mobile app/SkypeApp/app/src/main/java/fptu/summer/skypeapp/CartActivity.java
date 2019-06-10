package fptu.summer.skypeapp;

import android.content.Context;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import fptu.summer.skypeapp.model.Cart;
import fptu.summer.skypeapp.persistence.CartAdapter;
import fptu.summer.skypeapp.remote.CartDatabase;
import fptu.summer.skypeapp.service.CallBackTotal;

public class CartActivity extends MasterActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CartDatabase cartDatabase;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.listCart);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        cartDatabase = CartDatabase.getInstance(this);

        txtTotal = findViewById(R.id.total);

        GetListItem();

    }

    @Override
    public Context getApplicationContext() {
        return this;
    }



    float totalMoney = 0;

    public void GetListItem() {
        class GetListItem extends AsyncTask<Void, Void, List<Cart>> {

            @Override
            protected List<Cart> doInBackground(Void... voids) {
                List<Cart> cartList = cartDatabase.cartDAO().getAll();
                return cartList;
            }

            @Override
            protected void onPostExecute(final List<Cart> carts) {
                super.onPostExecute(carts);
                cartAdapter = new CartAdapter(getApplicationContext(), carts);
                recyclerView.setAdapter(cartAdapter);

                for (Cart carttt : carts) {
                    float totalOneProduct = carttt.getPrice() * carttt.getQuantity();
                    totalMoney += totalOneProduct;
                }
                txtTotal.setText(totalMoney + "");
            }
        }
        GetListItem gl = new GetListItem();
        gl.execute();
    }

    public void udpatePrice(List<Cart> cartList) {
        totalMoney = 0;
        for (Cart carttt : cartList) {
            float totalOneProduct = carttt.getPrice() * carttt.getQuantity();
            totalMoney += totalOneProduct;
        }
        txtTotal.setText(totalMoney + "");
        Toast.makeText(CartActivity.this, "Update price successfully!", Toast.LENGTH_SHORT).show();
    }
}
