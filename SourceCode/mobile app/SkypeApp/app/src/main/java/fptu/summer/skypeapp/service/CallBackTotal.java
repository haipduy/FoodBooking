package fptu.summer.skypeapp.service;

import java.util.List;

import fptu.summer.skypeapp.model.Cart;

public interface CallBackTotal {
    void udpatePrice(List<Cart> cartList);
}
