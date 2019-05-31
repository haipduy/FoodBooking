package fptu.summer.foodmanage.entity;

import javax.persistence.*;


@Entity
@Table(name = "Product", schema = "dbo", catalog = "FoodSystem")
public class ProductEntity {
    private String ProCode;
    private String StoreId;
    private String ProName;
    private int ProPrice;
    private String ProImage;
    private int ProQuantity;
    private String ProDescription;
    private int ProStatus;
    private int categoryId;

    public ProductEntity() {
    }

    @Id
    @Column(name = "ProCode")
    public String getProCode() {
        return ProCode;
    }

    public void setProCode(String proCode) {
        ProCode = proCode;
    }

    @Basic
    @Column(name = "StoreId")

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    @Basic
    @Column(name = "ProName")

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    @Basic
    @Column(name = "ProPrice")
    public int getProPrice() {
        return ProPrice;
    }

    public void setProPrice(int proPrice) {
        ProPrice = proPrice;
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
        return ProQuantity;
    }

    public void setProQuantity(int proQuantity) {
        ProQuantity = proQuantity;
    }

    @Basic
    @Column(name = "ProDescription")
    public String getProDescription() {
        return ProDescription;
    }

    public void setProDescription(String proDescription) {
        ProDescription = proDescription;
    }



    @Basic
    @Column(name = "ProStatus")
    public int getProStatus() {
        return ProStatus;
    }

    public void setProStatus(int proStatus) {
        ProStatus = proStatus;
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
