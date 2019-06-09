package fptu.summer.foodmanage.entity;

import javax.persistence.*;

@Entity
@Table(name = "DetailOrder", schema = "dbo", catalog = "FoodSystem")
public class DetailOrderEntity {
    private int orderId;
    private String productId;
    private  int quantity;
    private float price;

    public DetailOrderEntity() {
    }

    public DetailOrderEntity(int orderId, String productId, int quantity, float price) {
        orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }



    @Id
    @Column(name = "OrderId")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "ProductId")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
