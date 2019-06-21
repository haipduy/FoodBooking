package fptu.sumer.foodstore_api.responsemodel;

import java.io.Serializable;
import java.util.List;

public class ItemRequestModel implements Serializable {
    private String userId;
    private float total;
    private String notes;
    private List<ItemModel> listProduct;

    public ItemRequestModel(String userId, float total, String notes, List<ItemModel> listProduct) {

        this.userId = userId;
        this.total = total;
        this.notes = notes;
        this.listProduct = listProduct;
    }

    public ItemRequestModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ItemModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ItemModel> listProduct) {
        this.listProduct = listProduct;
    }
}
