package fptu.summer.skypeapp.model;

import java.util.List;

import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.model.entity.Product;

public interface LoadHistoryListener {
    void onLoadOrderSuccess(List<Order> orderList);
    void onLoadOrderFailure(String message);
}
