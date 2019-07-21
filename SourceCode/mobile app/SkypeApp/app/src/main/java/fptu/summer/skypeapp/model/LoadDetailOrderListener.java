package fptu.summer.skypeapp.model;

import java.util.List;

import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.model.entity.Order;

public interface LoadDetailOrderListener {
    void onLoadDetailOrderSuccess(List<DetailOrder> detailOrderList);
    void onLoadDetailOrderFailure(String message);
}
