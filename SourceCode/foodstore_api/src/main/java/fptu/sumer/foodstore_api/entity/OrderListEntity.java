package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "OrderList", schema = "dbo", catalog = "FoodSystem")
public class OrderListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private int orderId;

    @Basic
    @Column(name = "UserId")
    private String userId;

    @Basic
    @Column(name = "OrderDate")
    private Date orderDate;

    @Basic
    @Column(name = "Total")
    private float total;

    @Basic
    @Column(name = "Notes")
    private String notes;


    public OrderListEntity() {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
