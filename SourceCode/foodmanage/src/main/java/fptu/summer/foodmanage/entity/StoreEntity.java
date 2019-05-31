package fptu.summer.foodmanage.entity;

import javax.persistence.*;


@Entity
@Table(name = "Store", schema = "dbo", catalog = "FoodSystem")
public class StoreEntity {
    private String storeCode;
    private String storeName;
    private String storeAddress;
    private String storeImage;
    private int storePhoneNo;
    private int storeStatus;

    public StoreEntity() {
    }

    @Id
    @Column(name = "StoreCode")
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }


    @Basic
    @Column(name = "StoreName")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "StoreAddress")
    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Basic
    @Column(name = "StoreImage")
    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    @Basic
    @Column(name = "StorePhoneNo")
    public int getStorePhoneNo() {
        return storePhoneNo;
    }

    public void setStorePhoneNo(int storePhoneNo) {
        this.storePhoneNo = storePhoneNo;
    }


    @Basic
    @Column(name = "StoreStatus")
    public int getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(int storeStatus) {
        this.storeStatus = storeStatus;
    }
}
