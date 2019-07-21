package fptu.summer.skypeapp.presenter;

import java.util.List;

import fptu.summer.skypeapp.model.HistoryInterator;
import fptu.summer.skypeapp.model.LoadHistoryListener;
import fptu.summer.skypeapp.model.entity.Order;
import fptu.summer.skypeapp.view.interface_view.HistoryView;

public class HistoryPresenter implements LoadHistoryListener {
    private HistoryView historyView;
    private HistoryInterator historyInterator;

    public HistoryPresenter(HistoryView historyView) {
        this.historyView = historyView;
        this.historyInterator = new HistoryInterator(this);

    }
    public void loadHistory(String id){
        historyInterator.loadListProduct(id);
    }

    @Override
    public void onLoadOrderSuccess(List<Order> orderList) {
        historyView.displayHistoryList(orderList);
    }

    @Override
    public void onLoadOrderFailure(String message) {

    }
}
