package fptu.summer.skypeapp;

import android.content.Context;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;
import java.util.List;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.Cart;
import fptu.summer.skypeapp.persistence.CartAdapter;
import fptu.summer.skypeapp.remote.CartDatabase;
import fptu.summer.skypeapp.service.CallBackTotal;

import static fptu.summer.skypeapp.constants.RetrofitConstants.TIME_OUT;

public class CartActivity extends MasterActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CartDatabase cartDatabase;
    private TextView txtTotal;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("");
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

    public void clicktoSubmitOrder(View view) {
//        @PathVariable String userId,
//        @PathVariable float total,
//        @PathVariable String notes,
//        @RequestBody List<ItemResponseModel> listProduct
        Account account = MainActivity.account;
        if(MainActivity.account!=null){
            String userID = account.getUserId();
        }
    }
}
