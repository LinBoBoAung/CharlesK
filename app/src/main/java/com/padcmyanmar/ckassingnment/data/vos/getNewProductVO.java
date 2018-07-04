package com.padcmyanmar.ckassingnment.data.vos;

import com.google.gson.annotations.SerializedName;

public class getNewProductVO {
    @SerializedName("product-id")
    private String productId;

    @SerializedName("product-image")
    private String productImage;

    @SerializedName("product-title")
    private String productTitle;

    public String getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
