package fptu.summer.skypeapp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class BankAccount implements Serializable {

    @SerializedName("bankId")
    @Expose
    private int bankId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("accName")
    @Expose
    private  String accName;
    @SerializedName("accCardNo")
    @Expose
    private int accCardNo;
    @SerializedName("accMoney")
    @Expose
    private float accMoney;
    @SerializedName("expiredDate")
    @Expose
    private String expiredDate;
    @SerializedName("isActive")
    @Expose
    private int isActive;

    public BankAccount() {
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

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
