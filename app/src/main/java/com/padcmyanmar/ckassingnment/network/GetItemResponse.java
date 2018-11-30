package com.padcmyanmar.ckassingnment.network;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;

import java.util.List;

public class GetItemResponse
{
    public static List<getNewProductVO> getNewProduct;
    @SerializedName("code")
  private  int code;
@SerializedName("message")
  private   String message;

@SerializedName("apiVersion")
    private String apiVersion;

@SerializedName("page")
   private String page;

@SerializedName("newProducts")
private List<getNewProductVO> getNewProducts;

    public static List<getNewProductVO> getNewProduct() {
        return getNewProduct;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<getNewProductVO> getGetNewProducts() {
        return getNewProducts;
    }

    public boolean isResponseOK()
    {
        return(code==200 &&getNewProducts!=null);
    }
}
