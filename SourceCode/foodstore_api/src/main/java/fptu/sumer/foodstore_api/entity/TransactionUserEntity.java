package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "TransactionUser", schema = "dbo", catalog = "FoodSystem")
public class TransactionUserEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", updatable = false)
    private int id;
    @Basic
    @Column(name = "BankId")
    private int bankId;

    @Basic
    @Column(name = "PayId")
    private int payId;
    @Basic
    @Column(name = "TransDate")
    private Date transDate;

    public TransactionUserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }
}
