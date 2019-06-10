package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;


@Entity
@Table(name = "Product", schema = "dbo", catalog = "FoodSystem")
public class ProductEntity {
    private String proId;
    private String storeId;
    private String proName;
    private float proPrice;
    private float priceDiscount;
    private String ProImage;
    private int proQuantity;
    private String proDescription;
    private int proStatus;
    private int categoryId;

    public ProductEntity() {
    }

    @Id
    @Column(name = "ProId")
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    @Basic
    @Column(name = "StoreId")
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "ProName")
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Basic
    @Column(name = "ProPrice")
    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    @Basic
    @Column(name = "PriceDiscount")
    public float getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(float priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    @Basic
    @Column(name = "ProImage")
    public String getProImage() {
        return ProImage;
    }

    public void setProImage(String proImage) {
        ProImage = proImage;
    }

    @Basic
    @Column(name = "ProQuantity")
    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    @Basic
    @Column(name = "ProDescription")
    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    @Basic
    @Column(name = "ProStatus")
    public int getProStatus() {
        return proStatus;
    }

    public void setProStatus(int proStatus) {
        this.proStatus = proStatus;
    }

    @Basic
    @Column(name = "CategoryId")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
