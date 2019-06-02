package fptu.summer.foodcourseapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//model

public class Product {
    // lay id ben trong chuoi json
    @SerializedName("proId")
    @Expose
    private String id;

    @SerializedName("proName")
    @Expose
    private String proName;

    @SerializedName("storeName")
    @Expose
    private String storeName;

    @SerializedName("proPrice")
    @Expose
    private float proPrice;

    @SerializedName("priceDiscount")
    @Expose
    private  float priceDiscount;

    @SerializedName("proImage")
    @Expose
    private String proImage;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public Product(String id, String proName) {
        this.id = id;
        this.proName = proName;
    }
}
