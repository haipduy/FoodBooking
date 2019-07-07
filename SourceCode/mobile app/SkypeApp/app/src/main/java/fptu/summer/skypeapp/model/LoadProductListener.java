package fptu.summer.skypeapp.model;

import java.util.List;

public interface LoadProductListener {
    void onLoadProductSuccess(List<Product> listProduct);
    void onLoadProductFailure(String message);
}
