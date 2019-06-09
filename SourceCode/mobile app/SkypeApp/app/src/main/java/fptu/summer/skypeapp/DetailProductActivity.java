package fptu.summer.skypeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import fptu.summer.skypeapp.model.Cart;
import fptu.summer.skypeapp.model.Product;
import fptu.summer.skypeapp.persistence.ProductDBAdapter;
import fptu.summer.skypeapp.remote.CartDatabase;

import static fptu.summer.skypeapp.constants.RetrofitConstants.BASE_URL;

public class DetailProductActivity extends MasterActivity {
    private Product product;
    TextView txtProductName,txtPriceDis,txtPrice,txtDescription,txtStore;
    ImageView imgProduct;
    Button btnAddToCart;
    CartDatabase cartDatabase;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call from master activity
        setContentView(R.layout.activity_detail_product);

        product = (Product) getIntent().getSerializableExtra(ProductDBAdapter.ViewHolderProduct.PRODUCT_CODE);

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
        txtPrice.setText(product.getProPrice()+"00");
        txtDescription.setText(product.getProDescription());



    }

    public void clickToAddToCart(View view) {

        final String productID = product.getId();

        cartDatabase = CartDatabase.getInstance(this);

        AsyncTask<Void,Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {

                Cart cart= new Cart();
                cart.setProductId(product.getId());
                cart.setStoreId(product.getStoreId());
                cart.setProductName(product.getProName());
                cart.setPrice(product.getPriceDiscount());

                List<Cart> listCart = cartDatabase.cartDAO().getAll();
                if(listCart!=null){
                    for(Cart cartEach : listCart){

                        String pId = cartEach.getProductId();

                        if(productID.equals(pId)){
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
                if(aBoolean) {
                    Toast.makeText(DetailProductActivity.this, "Update quantity",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailProductActivity.this, "Add new successful",Toast.LENGTH_SHORT).show();
                }

            }
        };
        asyncTask.execute();

    }

    public void clickToViewCart(View view) {
        Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
        startActivity(intent);
    }

}
