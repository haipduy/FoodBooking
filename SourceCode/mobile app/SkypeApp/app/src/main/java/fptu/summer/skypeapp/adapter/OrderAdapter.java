package fptu.summer.skypeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolderOrder> {
    private Context context;
    List<Order> listOrder;

    public OrderAdapter(Context context, List<Order> listOrder) {
        this.context = context;
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_layout, viewGroup, false);
//        return new CartAdapter.ViewHolderCart(view);
        return new OrderAdapter.ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOrder viewHolderOrder, int i) {
        viewHolderOrder.setData(listOrder.get(i));
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder {
        private TextView txtHistoryId;
        private TextView txtHistoryAmount;
        private TextView txtHistoryDate;


        public ViewHolderOrder(@NonNull View itemView) {
            super((itemView));
            txtHistoryDate = itemView.findViewById(R.id.txtHistoryOrderDate);
            txtHistoryId = itemView.findViewById(R.id.txtHistoryOrderId);
            txtHistoryAmount = itemView.findViewById(R.id.txtHistoryOrderAmount);

        }

        void setData(final Order order) {
            txtHistoryDate.setText(order.getOrderDate() + "");
            txtHistoryId.setText(order.getOrderId()+"");
            txtHistoryAmount.setText("$" + order.getTotal());
        }
    }


}
