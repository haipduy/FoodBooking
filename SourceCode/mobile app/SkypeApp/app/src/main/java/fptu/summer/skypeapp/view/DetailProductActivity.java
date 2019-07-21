package fptu.summer.skypeapp.view;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Cart;
import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.view.adapter.ProductDBAdapter;
import fptu.summer.skypeapp.database.CartDatabase;

import static fptu.summer.skypeapp.constants.RetrofitConstants.BASE_URL;
import static fptu.summer.skypeapp.utils.BundleString.PRODUCT_CODE;

public class DetailProductActivity extends MasterActivity {
    private Product product;
    TextView txtProductName, txtPriceDis, txtPrice, txtDescription, txtStore;
    ImageView imgProduct;
    Button btnAddToCart;
    CartDatabase cartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call from master activity
        setContentView(R.layout.activity_detail_product);

        product = (Product) getIntent().getSerializableExtra(PRODUCT_CODE);

        imgProduct = findViewById(R.id.imgProduct);
        //get all id in view
        txtProductName = findViewById(R.id.txtProductName);
        txtStore = findViewById(R.id.txtStore);
        txtPriceDis = findViewById(R.id.txtPriceDiscount);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);

        // set content into view
        Glide.with(getApplicationContext()).load(BASE_URL + product.getProImage()).into(imgProduct);
        txtProductName.setText(product.getProName());
        txtStore.setText(product.getStoreName());
        txtPriceDis.setText(product.getPriceDiscount() + "00");

        String htmlcontent = "<strike>" + product.getProPrice() + "00</strike>";
        txtPrice.setText(android.text.Html.fromHtml(htmlcontent));

        txtDescription.setText(product.getProDescription());


    }

    public void clickToAddToCart(View view) {

        final String productID = product.getId();

        cartDatabase = CartDatabase.getInstance(this);

        AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {

                Cart cart = new Cart();
                cart.setProductId(product.getId());
                cart.setStoreId(product.getStoreId());
                cart.setProductName(product.getProName());
                cart.setPrice(product.getPriceDiscount());

                List<Cart> listCart = cartDatabase.cartDAO().getAll();
                if (listCart != null) {
                    for (Cart cartEach : listCart) {

                        String pId = cartEach.getProductId();

                        if (productID.equals(pId)) {
                            int quantityUp = cartEach.getQuantity() + 1;
                            cartEach.setQuantity(quantityUp);
                            cartDatabase.cartDAO().update(cartEach);
                            return true;

                        }
                    }
                }
                cart.setQuantity(1);
                cartDatabase.cartDAO().insertCart(cart);
                return false;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                String msg = "";
                if (aBoolean) {
                    msg = "cập nhật";
//                    Toast.makeText(DetailProductActivity.this, "Update quantity", Toast.LENGTH_SHORT).show();
                } else {
                    msg = "thêm";
//                    Toast.makeText(DetailProductActivity.this, "Add new successful", Toast.LENGTH_SHORT).show();
                }
                showDialogCart(msg);

            }
        };
        asyncTask.execute();

    }

    public void clickToViewCart(View view) {
        Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
        startActivity(intent);
    }

    public void showDialogCart(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn đã " + msg + " item thành công!");
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
