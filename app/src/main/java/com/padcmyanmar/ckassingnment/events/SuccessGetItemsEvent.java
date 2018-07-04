package com.padcmyanmar.ckassingnment.events;

import android.util.Log;

import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;

import java.util.ArrayList;
import java.util.List;

public class SuccessGetItemsEvent {

    private List<getNewProductVO> mproductList=new ArrayList<>();

    public List<getNewProductVO> getNewProductList() {
        return mproductList;
    }

    public SuccessGetItemsEvent(List<getNewProductVO> productList)

    {
//        Log.i("Success", "SuccessGetItemsEvent:"+productList.size() );
      mproductList = productList;
    }

}
