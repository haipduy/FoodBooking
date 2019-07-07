package fptu.summer.skypeapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {


    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("total")
    @Expose
    private float total;
    @SerializedName("notes")
    @Expose
    private String notes;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
}
