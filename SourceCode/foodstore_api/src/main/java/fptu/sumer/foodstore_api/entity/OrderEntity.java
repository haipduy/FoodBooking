package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Order", schema = "dbo", catalog = "FoodSystem")
public class OrderEntity {

    private int orderId;
    private String cusId;
    private String orderDate;
    private float total;
    private String notes;
    List<DetailOrderEntity> detailList;


    public OrderEntity() {
    }

    public OrderEntity(int orderId, String cusId, String orderDate, float total, String notes, List<DetailOrderEntity> detailList) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.orderDate = orderDate;
        this.total = total;
        this.notes = notes;
        this.detailList = detailList;
    }

    @Id

    @Column(name = "OrderId")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CusId")
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "OrderDate")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "Total")
    public float getTotal() {
        return total;
    }


    public void setTotal(float total) {
        this.total = total;
    }

    @Basic
    @Column(name = "Notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public void setDetailList(List<DetailOrderEntity> detailList) {
        this.detailList = detailList;
    }
}
