package fptu.summer.skypeapp.view.adapter;

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

import fptu.summer.skypeapp.view.CartActivity;
import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Cart;
import fptu.summer.skypeapp.database.CartDatabase;
import fptu.summer.skypeapp.view.LoginActivity;
import fptu.summer.skypeapp.view.ProfileActivity;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolderCart> {
    private CartDatabase cartDatabase;
    // la actiivity hien hanh
    private CartActivity context;
    //data json
    List<Cart> listCart;

    public CartAdapter(Context context, List<Cart> listCart) {
        this.context = (CartActivity) context;
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
            txtPriceDis.setText(cart.price + "00");
            btnQuantity.setNumber(cart.quantity + "");

            //delete item from cart
            btnRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    deleteItem(cart);
                    showDialogDeleteItemfromCart(cart);
//                    listCart.remove(cart);
//                    notifyDataSetChanged();
                    context.udpatePrice(listCart);

                }
            });
            // update quantity
            btnQuantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    int quanti =Integer.parseInt(btnQuantity.getNumber()) ;
                    cart.setQuantity(quanti);
                    btnQuantity.setNumber(cart.quantity + "");
                    updateQuantitty(cart);
                    notifyDataSetChanged();
                    context.udpatePrice(listCart);
                }
            });
        }
    }
    private void updateQuantitty(final Cart cart){
        cartDatabase = CartDatabase.getInstance(context);
        class UpdateQuantity extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDAO().update(cart);
                return null;
            }
        }
        UpdateQuantity uq = new UpdateQuantity();
        uq.execute();
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
                if(aVoid == null){
                    listCart.remove(cart);
                    notifyDataSetChanged();
                    showDialogDeleteItemSuccess();
                }
            }
        }
        DeleteItem dt = new DeleteItem();
        dt.execute();
    }

    public void showDialogDeleteItemfromCart(final Cart cart) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Bạn muốn xóa item khỏi giỏ hàng?");

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItem(cart);
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

    public void showDialogDeleteItemSuccess() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Đã xóa thành công!");
        builder1.setCancelable(true);
        builder1.setNegativeButton(
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
