package fptu.summer.skypeapp.persistence;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import fptu.summer.skypeapp.CartActivity;
import fptu.summer.skypeapp.DetailProductActivity;
import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.Cart;
import fptu.summer.skypeapp.remote.CartDatabase;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    private CartDatabase cartDatabase;
    // la actiivity hien hanh
    private Context context;
    //data json
    List<Cart> listCart;

    public CartAdapter(Context context, List<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, viewGroup, false);
        return new CartAdapter.ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart viewHolderCart, int i) {
        viewHolderCart.setData(listCart.get(i));
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder {
        private TextView txtProductName, txtPriceDis;
        private ElegantNumberButton btnQuantity;
        private Button btnRemoveItem;


        public ViewHolderCart(@NonNull View itemView) {
            super((itemView));
            txtProductName = itemView.findViewById(R.id.cart_item_Name);
            txtPriceDis = itemView.findViewById(R.id.cart_item_Price);
            btnQuantity = (ElegantNumberButton) itemView.findViewById(R.id.btnQuantity_count);

            btnRemoveItem = itemView.findViewById(R.id.btnRemoveItem);

        }
        void setData(final Cart cart) {

            txtProductName.setText(cart.productName);
            txtPriceDis.setText(cart.price + "");
            btnQuantity.setNumber(cart.quantity + "");

            //delete item from cart
            btnRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(cart);
//                    Intent intent = new Intent(context,CartActivity.class);
//                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    context.startActivity(intent);

                }
            });
        }
    }

    private void deleteItem(final Cart  cart){
        cartDatabase = CartDatabase.getInstance(context);
        class DeleteItem extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDAO().delete(cart);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(aVoid != null){
                    Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        }
        DeleteItem dt = new DeleteItem();
        dt.execute();
    }



}
