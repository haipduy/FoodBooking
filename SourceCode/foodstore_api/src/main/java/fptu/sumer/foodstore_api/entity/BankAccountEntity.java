package fptu.sumer.foodstore_api.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@ApiModel(description = "All details about the Bank Account. ")
@Table(name = "BankAccount", schema = "dbo", catalog = "FoodSystem")
public class BankAccountEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BankId")
    private int bankId;

    @Basic
    @Column(name = "UserId")
    private String userId;

    @Basic
    @Column(name = "AccName")
    private  String accName;
    @Basic
    @Column(name = "AccCardNo")
    private int accCardNo;

    @Basic
    @Column(name = "AccMoney")
    private float accMoney;

    @Basic
    @Column(name = "ExpiredDate")
    private Date expiredDate;

    @Basic
    @Column(name = "IsActive")
    private int isActive;

    public BankAccountEntity() {
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public int getAccCardNo() {
        return accCardNo;
    }

    public void setAccCardNo(int accCardNo) {
        this.accCardNo = accCardNo;
    }

    public float getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(float accMoney) {
        this.accMoney = accMoney;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
