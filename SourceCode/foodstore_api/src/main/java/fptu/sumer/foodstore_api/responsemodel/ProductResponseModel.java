package fptu.sumer.foodstore_api.responsemodel;


import fptu.sumer.foodstore_api.entity.CategoryEntity;

public class ProductResponseModel {
    private String proId;
    private String storeId;
    private String storeName;
    private String proName;
    private float proPrice;
    private float priceDiscount;
    private String ProImage;
    private int proQuantity;
    private String proDescription;
    private int proStatus;
    private CategoryEntity category;


    public ProductResponseModel(String proId, String storeId, String storeName, String proName, float proPrice, float priceDiscount, String proImage, int proQuantity, String proDescription, int proStatus, CategoryEntity category) {
        this.proId = proId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.proName = proName;
        this.proPrice = proPrice;
        this.priceDiscount = priceDiscount;
        ProImage = proImage;
        this.proQuantity = proQuantity;
        this.proDescription = proDescription;
        this.proStatus = proStatus;
        this.category = category;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public float getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(float priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getProImage() {
        return ProImage;
    }

    public void setProImage(String proImage) {
        ProImage = proImage;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public int getProStatus() {
        return proStatus;
    }

    public void setProStatus(int proStatus) {
        this.proStatus = proStatus;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
