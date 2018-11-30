package com.padcmyanmar.ckassingnment.data.models;

import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.events.SuccessFoceRefreshGetItemEvent;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;
import com.padcmyanmar.ckassingnment.network.ItemDataAgent;
import com.padcmyanmar.ckassingnment.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CKModel {
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static CKModel objInstance;
    private ItemDataAgent mitemDataAgent;
    private Map<String, getNewProductVO> mProductMap;
    private int mpage;

    private CKModel() {
        mitemDataAgent = RetrofitDataAgentImpl.getSobjInstance();
        mProductMap = new HashMap<>();
        mpage = 1;
        EventBus.getDefault().register(this);

    }

    public static CKModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new CKModel();
        }

        return objInstance;

    }

    public void loadItemList() {
        mitemDataAgent.loadItemList(mpage, DUMMY_ACCESS_TOKEN, false);
    }

    public void forceRefreshItemList() {
        mpage = 1;
        mitemDataAgent.loadItemList(1, DUMMY_ACCESS_TOKEN, true);//Initial page မွာပဲdataအသစိရလားမရလားစစ္တာ
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetItem(SuccessGetItemsEvent event)//pageတစ္ခုက္ုloadလုပ္ျ႔ီပီးရင္ ေနာက္page တစ္ခုက္ုသံြားq``1
    {
        setDataIntoRepo(event.getNewProductList());
        mpage++;
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetItem(SuccessFoceRefreshGetItemEvent event) {
        setDataIntoRepo(event.getNewProductList());
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    private void setDataIntoRepo(List<getNewProductVO> itemList) {//code purpose duplicate ျဖစ္လို့helper methodအေနနဲ့ ခံြဲေရးထားတာ
        for (getNewProductVO product : itemList) {
            mProductMap.put(product.getProductId(), product);


        }
    }

    public getNewProductVO getTalksById(String productId)//idေပးျပီးdataျပနိထုတ္
    {
        return mProductMap.get(productId);
    }


}
