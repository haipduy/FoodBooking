package fptu.summer.foodapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("proName")
    @Expose
    private String proName;

    @SerializedName("proPrice")
    @Expose
    private float proPrice;

    @SerializedName("priceDiscount")
    @Expose
    private float priceDiscount;

    @SerializedName("proImage")
    @Expose
    private String proImageUrl;



    public Product() {
    }

    public Product(String proName, float proPrice, float priceDiscount, String proImageUrl) {
        this.proName = proName;
        this.proPrice = proPrice;
        this.priceDiscount = priceDiscount;
        this.proImageUrl = proImageUrl;
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

    public String getProImageUrl() {
        return proImageUrl;
    }

    public void setProImageUrl(String proImageUrl) {
        this.proImageUrl = proImageUrl;
    }
}
