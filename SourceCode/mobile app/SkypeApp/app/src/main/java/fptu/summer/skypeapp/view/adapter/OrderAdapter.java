package fptu.summer.skypeapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.view.DetailOrderActivity;
import fptu.summer.skypeapp.view.DetailProductActivity;

import static fptu.summer.skypeapp.utils.BundleString.*;

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
        private LinearLayout containerHistory;


        public ViewHolderOrder(@NonNull View itemView) {
            super((itemView));
            txtHistoryDate = itemView.findViewById(R.id.txtHistoryOrderDate);
            txtHistoryId = itemView.findViewById(R.id.txtHistoryOrderId);
            txtHistoryAmount = itemView.findViewById(R.id.txtHistoryOrderAmount);
            containerHistory = itemView.findViewById(R.id.containerHistory);

        }

        void setData(final Order order) {
            txtHistoryDate.setText(order.getOrderDate() + "");
            txtHistoryId.setText(order.getOrderId()+"");
            txtHistoryAmount.setText("$" + order.getTotal());

            containerHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailOrderActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(ORDER_CODE,order);
                    context.startActivity(intent);
                }
            });
        }
    }


}
