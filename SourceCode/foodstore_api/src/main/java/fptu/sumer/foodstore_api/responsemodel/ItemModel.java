package fptu.sumer.foodstore_api.responsemodel;

import java.io.Serializable;

public class ItemModel implements Serializable {
    private String productId;
    private String storeId;
    private int quantity;
    private  float price;

    public ItemModel(String productId, String storeId, int quantity, float price) {
        this.productId = productId;
        this.storeId = storeId;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemModel() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
