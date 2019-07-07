package fptu.summer.skypeapp.view.interface_view;

import java.util.List;

import fptu.summer.skypeapp.model.entity.Order;

public interface HistoryView {
    void displayHistoryList(List<Order> orderList);
}
