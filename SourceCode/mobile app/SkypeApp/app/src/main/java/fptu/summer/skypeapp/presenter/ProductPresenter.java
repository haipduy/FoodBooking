package fptu.summer.skypeapp.presenter;

import java.util.List;


import fptu.summer.skypeapp.model.LoadProductListener;
import fptu.summer.skypeapp.model.entity.Product;
import fptu.summer.skypeapp.model.ProductInterator;
import fptu.summer.skypeapp.view.interface_view.MainView;

public class ProductPresenter implements LoadProductListener {
    private ProductInterator productInterator;
    private MainView mainView;

    public ProductPresenter(MainView mainView) {

        this.mainView = mainView;
        productInterator = new ProductInterator(this);
    }

    public void loadData() {
        productInterator.loadListProduct();
    }

    @Override
    public void onLoadProductSuccess(List<Product> listProduct) {
        mainView.displayListProduct(listProduct);
    }

    @Override
    public void onLoadProductFailure(String message) {
       // code for error with message
    }
}
