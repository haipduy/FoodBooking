package fptu.summer.skypeapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Cart;
import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.presenter.ProductPresenter;

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderAdapter.ViewHolderDetailOrder> {
    private Context context;
    List<DetailOrder> listDetailOrder;
    private ProductPresenter productPresenter;

    public DetailOrderAdapter(Context context, List<DetailOrder> listDetailOrder) {
        this.context = context;
        this.listDetailOrder = listDetailOrder;
    }

    @NonNull
    @Override
    public ViewHolderDetailOrder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_order, viewGroup, false);
//        return new CartAdapter.ViewHolderCart(view);
        return new DetailOrderAdapter.ViewHolderDetailOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDetailOrder viewHolderDetailOrder, int i) {
        viewHolderDetailOrder.setData(listDetailOrder.get(i));
    }

    @Override
    public int getItemCount() {
        return listDetailOrder.size();
    }

    public class ViewHolderDetailOrder extends RecyclerView.ViewHolder {
        private TextView txtDetailName, txtDetailQuantity, txtDetailPrice;

        public ViewHolderDetailOrder(@NonNull View itemView) {
            super((itemView));
            txtDetailName = itemView.findViewById(R.id.txtDetailName);
            txtDetailQuantity = itemView.findViewById(R.id.txtDetailQuantity);
            txtDetailPrice = itemView.findViewById(R.id.txtDetailPrice);

        }

        void setData(final DetailOrder detailOrder) {
            txtDetailName.setText(detailOrder.getProductName());
            txtDetailQuantity.setText(detailOrder.getQuantity() + "");
            txtDetailPrice.setText(detailOrder.getPrice() + "00");

        }
    }


}
