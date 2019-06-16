package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Payment", schema = "dbo", catalog = "FoodSystem")
public class PaymentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PayId")
    private int payId;

    @Basic
    @Column(name = "OrderId")
    private int orderId;
    @Basic
    @Column(name = "PayAmount")
    private float payAmount;
    @Basic
    @Column(name = "PayDate")
    private Date payDate;

    public PaymentEntity() {
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
