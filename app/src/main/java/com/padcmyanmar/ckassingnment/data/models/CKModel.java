package com.padcmyanmar.ckassingnment.data.models;

import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;
import com.padcmyanmar.ckassingnment.network.ItemDataAgent;
import com.padcmyanmar.ckassingnment.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class CKModel
{
    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";
    private static CKModel objInstance;
    private ItemDataAgent mitemDataAgent;
    private Map<String,getNewProductVO> mProductMap;

    private CKModel()
    {
        mitemDataAgent= RetrofitDataAgentImpl.getSobjInstance();
        mProductMap=new HashMap<>();
        EventBus.getDefault().register(this);

    }

    public static CKModel getObjInstance()
    {
        if(objInstance==null)
        {
            objInstance=new CKModel();
        }

            return objInstance;

    }
    public void loadItemList()
    {
        mitemDataAgent.loadItemList(1,DUMMY_ACCESS_TOKEN);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTalks(SuccessGetItemsEvent event) {
        for (getNewProductVO tedTalks : event.getNewProductList()) {
            mProductMap.put(tedTalks.getProductId(), tedTalks);

        }
    }

    public getNewProductVO getTalksById(String productId){
        return mProductMap.get(productId);
    }


}
