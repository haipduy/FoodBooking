package fptu.summer.skypeapp.model;

import java.util.List;

import fptu.summer.skypeapp.model.entity.Product;

public interface LoadProductListener {
    void onLoadProductSuccess(List<Product> listProduct);
    void onLoadProductFailure(String message);
}
