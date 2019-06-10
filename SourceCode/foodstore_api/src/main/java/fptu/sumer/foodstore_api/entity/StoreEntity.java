package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;


@Entity
@Table(name = "Store", schema = "dbo", catalog = "FoodSystem")
public class StoreEntity {
    private String storeId;
    private String userId;
    private String storeName;
    private String storeAddress;
    private String storeImage;
    private int storePhoneNo;
    private int storeStatus;

    public StoreEntity() {
    }

    @Id
    @Column(name = "StoreId")
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "UserId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
