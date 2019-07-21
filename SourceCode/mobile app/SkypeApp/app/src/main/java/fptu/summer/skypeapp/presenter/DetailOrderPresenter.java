package fptu.summer.skypeapp.presenter;

import java.util.List;

import fptu.summer.skypeapp.model.DetailOrderInterator;
import fptu.summer.skypeapp.model.LoadDetailOrderListener;
import fptu.summer.skypeapp.model.entity.DetailOrder;
import fptu.summer.skypeapp.view.interface_view.DetailListOrderView;

public class DetailOrderPresenter implements LoadDetailOrderListener {
    private DetailListOrderView detailListOrderView;
    private DetailOrderInterator detailOrderInterator;

    public DetailOrderPresenter(DetailListOrderView detailListOrderView) {
        this.detailListOrderView = detailListOrderView;
        this.detailOrderInterator = new DetailOrderInterator(this);
    }
    public void loadDetailListOrder(int orderId){
        detailOrderInterator.loadListDetailOrder(orderId);
    }

    @Override
    public void onLoadDetailOrderSuccess(List<DetailOrder> detailOrderList) {
        detailListOrderView.displayDetailOrderList(detailOrderList);
    }

    @Override
    public void onLoadDetailOrderFailure(String message) {

    }
}
