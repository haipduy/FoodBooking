package fptu.summer.skypeapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import fptu.summer.skypeapp.model.Account;
import fptu.summer.skypeapp.model.BankAccount;
import fptu.summer.skypeapp.model.Cart;
import fptu.summer.skypeapp.model.ItemModel;
import fptu.summer.skypeapp.model.ItemRequestModel;
import fptu.summer.skypeapp.persistence.CartAdapter;
import fptu.summer.skypeapp.remote.CartDatabase;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends MasterActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CartDatabase cartDatabase;
    private TextView txtTotal, txtNotes;
    private Toolbar toolbar;
    private Account account;
    private OrderService orderService;
    private BankService bankService;

    private boolean checkValidOrder = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_homes:

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_favorites:
                        intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);

                        break;
                }
                return true;
            }
        });

        txtNotes = findViewById(R.id.txtNotes);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cart");

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
                if (carts != null) {
                    cartAdapter = new CartAdapter(getApplicationContext(), carts);
                    recyclerView.setAdapter(cartAdapter);

                    for (Cart carttt : carts) {
                        float totalOneProduct = carttt.getPrice() * carttt.getQuantity();
                        totalMoney += totalOneProduct;
                    }

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
        account = MainActivity.account;

        validationOrder(account);

        if(checkValidOrder){
            System.out.println("co account va co tai khoan roi");
        }else{
            System.out.println("Sai -----------------------");
        }
//        AsyncTask<Void, Void, List<Cart>> asyncTask = new AsyncTask<Void, Void, List<Cart>>() {
//            String userId = account.getUserId();
//            float total = Float.parseFloat(txtTotal.getText().toString().trim());
//            String notes = txtNotes.getText().toString().trim();
//            List<ItemModel> listProduct = null;
//            @Override
//            protected List<Cart> doInBackground(Void... voids) {
//                List<Cart> cartList = cartDatabase.cartDAO().getAll();
//                return cartList;
//
//            }
//            @Override
//            protected void onPostExecute(final List<Cart> carts) {
//                for (Cart cart : carts) {
//                    ItemModel item = new ItemModel(cart.productId, cart.storeId, cart.quantity, cart.price);
//                    System.out.println(item.getProductId() + "=------------------------------------product ID");
//                    if (listProduct == null) {
//                        listProduct = new ArrayList<>();
//                    }
//                    listProduct.add(item);
//                }
//                ItemRequestModel itemRequest = new ItemRequestModel(userId, total, notes, listProduct);
//                //call service
//                orderService = APIUtils.getOrderService();
//
//                System.out.println(itemRequest.getListProduct().size() + " -----------------------");
//                orderService.creataNewOrder(itemRequest).enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()) {
//
//                            Toast.makeText(CartActivity.this, "Create order OK", Toast.LENGTH_SHORT).show();
//                            deleteAllItem();
//                            GetListItem();
//                            Intent intent = new Intent(CartActivity.this, MainActivity.class);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(CartActivity.this, "Create order fail", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        t.printStackTrace();
//                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//        };
//        asyncTask.execute();
    }

    public boolean validationOrder( Account account) {
        //check account co login hay khong
        if (account == null) {
            // chua login
            System.out.println("--- Chua login --- ");
            checkValidOrder = false;
            return false;
        }
        //check account co tai tai khoan hay khong
        String userID = account.getUserId();
        System.out.println("User id -----------" +userID);
        // call api lay tai khaon ngan hang
        bankService = APIUtils.bankService();
        bankService.getBankAccountByUserId(userID).enqueue(new Callback<BankAccount>() {
            @Override
            public void onResponse(Call<BankAccount> call, Response<BankAccount> response) {
                if(response.isSuccessful()){
                    BankAccount bankAccount = response.body();
                    if(bankAccount != null){
                        System.out.println("--- Co Bank Account ---");
                        return;
                    }

                } else{
                    System.out.println("--- Khong co Bank Account ---");
                    checkValidOrder = false;
                    return;
                }
            }
            @Override
            public void onFailure(Call<BankAccount> call, Throwable t) {

            }
        });
        return true;
    }

    private void deleteAllItem() {
        cartDatabase = CartDatabase.getInstance(this);
        class DeleteItem extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected Boolean doInBackground(Void... voids) {
                cartDatabase.cartDAO().deleteAllCart();
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aVoid) {
                if (aVoid) {
                    Toast.makeText(CartActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        }
        DeleteItem dt = new DeleteItem();
        dt.execute();
    }


}
