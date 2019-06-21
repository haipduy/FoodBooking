package fptu.summer.skypeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ItemRequestModel implements Serializable {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("total")
    @Expose
    private float total;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("listProduct")
    @Expose
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
