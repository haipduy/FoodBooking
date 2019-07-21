package fptu.summer.skypeapp.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Account;
import fptu.summer.skypeapp.model.entity.BankAccount;
import fptu.summer.skypeapp.model.entity.Cart;
import fptu.summer.skypeapp.model.entity.ItemModel;
import fptu.summer.skypeapp.model.entity.ItemRequestModel;
import fptu.summer.skypeapp.view.adapter.CartAdapter;
import fptu.summer.skypeapp.database.CartDatabase;
import fptu.summer.skypeapp.service.BankService;
import fptu.summer.skypeapp.service.OrderService;
import fptu.summer.skypeapp.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CartDatabase cartDatabase;
    private TextView txtTotal, txtNotes;
    private Account account;
    private OrderService orderService;

    private TextView txtAmountcart;


    private float totalMoney = 0;
    private boolean bankAccountIsExist = true;

    NotificationManager manager;
    int NotificationId = 999;
    int numMsg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        txtNotes = findViewById(R.id.txtNotes);
        txtAmountcart = findViewById(R.id.txtAmountCart);


        recyclerView = findViewById(R.id.listCart);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        cartDatabase = CartDatabase.getInstance(this);

        txtTotal = findViewById(R.id.total);

        GetListItem();
        loadBankAccount();

    }

    @Override
    public Context getApplicationContext() {
        return this;
    }


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

    @Override
    protected void onResume() {
        super.onResume();
        loadBankAccount();
    }

    // request code 222.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 222) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
                loadBankAccount();
            }
        }
    }

    public void udpatePrice(List<Cart> cartList) {
        totalMoney = 0;
        for (Cart carttt : cartList) {
            float totalOneProduct = carttt.getPrice() * carttt.getQuantity();
            totalMoney += totalOneProduct;
        }
        int total1 = (int) totalMoney;
        txtTotal.setText(totalMoney + "00 dong");
        Toast.makeText(CartActivity.this, "Update price successfully!", Toast.LENGTH_SHORT).show();
    }

    public void clicktoSubmitOrder(View view) {
        account = MainActivity.account;
        BankAccount bankAccount = MainActivity.bankAccount;



        checkBankAccount(account, bankAccount);

        if (bankAccountIsExist) {

            System.out.println("co account va co tai khoan roi");

            AsyncTask<Void, Void, List<Cart>> asyncTask = new AsyncTask<Void, Void, List<Cart>>() {
                String userId = account.getUserId();
                float total = Float.parseFloat(txtTotal.getText().toString().trim());
                String notes = txtNotes.getText().toString().trim();
                List<ItemModel> listProduct = null;

                @Override
                protected List<Cart> doInBackground(Void... voids) {
                    List<Cart> cartList = cartDatabase.cartDAO().getAll();
                    return cartList;

                }

                @Override
                protected void onPostExecute(final List<Cart> carts) {
                    if (!carts.isEmpty()) {
                        for (Cart cart : carts) {
                            ItemModel item = new ItemModel(cart.productId, cart.storeId, cart.quantity, cart.price);
                            if (listProduct == null) {
                                listProduct = new ArrayList<>();
                            }
                            listProduct.add(item);
                        }
                        ItemRequestModel itemRequest = new ItemRequestModel(userId, total, notes, listProduct);
                        //call service
                        orderService = APIUtils.getOrderService();

                        System.out.println(itemRequest.getListProduct().size() + " -----------------------");
                        orderService.creataNewOrder(itemRequest).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    showDialogCreateOrderOK();
//                                    Toast.makeText(CartActivity.this, "Create order OK", Toast.LENGTH_SHORT).show();
                                    deleteAllItem();
                                    GetListItem();
                                    displayNotification();
                                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                                    startActivity(intent);


                                } else {
                                    showDialog("Mua hàng thất bại, vui lòng thử lại.");
                                    Toast.makeText(CartActivity.this, "Create order fail", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        showDialog("Giỏ hàng trống, vui lòng chọn sản phẩm");
//                        Toast.makeText(CartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                    }

                }
            };
            asyncTask.execute();
        }


    }



    //check account co tai tai khoan hay khong
    private void checkBankAccount(Account account, BankAccount bankAccount) {
        if (account == null) {
//            Toast.makeText(CartActivity.this, "Chua loginn", Toast.LENGTH_SHORT).show();
            showDialogCheckLogin();
            bankAccountIsExist = false;
            return;
        } else if (bankAccount == null) {
            showDialogBankAccount();
//            Toast.makeText(CartActivity.this, "Ban chua co tai khoan thanh toan", Toast.LENGTH_SHORT).show();
            bankAccountIsExist = false;
        } else if (bankAccount != null) {
            float accMoney = bankAccount.getAccMoney();
            if (accMoney == 0 || accMoney < totalMoney) {
                showDialogNotsuccess();
//                Toast.makeText(CartActivity.this, "Tai khoan thanh toan cua ban khong du", Toast.LENGTH_SHORT).show();
                bankAccountIsExist = false;
            }
        }

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

                }
            }
        }
        DeleteItem dt = new DeleteItem();
        dt.execute();

    }


    public void loadBankAccount() {
        Account account = MainActivity.account;
        if (account == null) {
            txtAmountcart.setText("Bạn chưa đăng nhập!");
        } else {
            BankAccount bankAccount = MainActivity.bankAccount;

            if (bankAccount == null) {
                txtAmountcart.setText("Bạn chưa có tài khoản!");
                return;
            }

            int amount = (int) bankAccount.getAccMoney();
            txtAmountcart.setText("$ " + amount + " Dong");
        }

    }


    private void displayNotification() {
        NotificationCompat.Builder buider = new NotificationCompat.Builder(this);
        buider.setContentTitle("Thông Báo");
        buider.setContentText("Đặt hàng thành công!");
        buider.setTicker("Message Alert");
        buider.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        buider.setNumber(++numMsg);

        Intent intent = new Intent(this, CartActivity.class);

        TaskStackBuilder stack = TaskStackBuilder.create(this);
        stack.addParentStack(CartActivity.class);
        stack.addNextIntent(intent);

        PendingIntent pending = stack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        buider.setContentIntent(pending);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NotificationId, buider.build());

    }

    public void showDialogCheckLogin() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn chưa đăng nhập. Đăng nhập để mua hàng?");

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                        startActivityForResult(intent,2222);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void showDialogBankAccount() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ví của bạn không đủ để thanh toán!");

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        return;
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void showDialogNotsuccess() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn chưa có ví để thành toán!");

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        return;
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void showDialogCreateOrderOK() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn đã mua hàng thành công!");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void showDialog(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
