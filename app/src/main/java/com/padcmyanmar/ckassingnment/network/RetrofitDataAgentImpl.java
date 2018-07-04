package com.padcmyanmar.ckassingnment.network;

import android.util.Log;

import com.padcmyanmar.ckassingnment.events.ApiErrorEvent;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;
import com.padcmyanmar.ckassingnment.utils.ItemConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements ItemDataAgent {
    private ItemApi mItemApi;

    private  RetrofitDataAgentImpl()
    {
        final OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ItemConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mItemApi=retrofit.create(ItemApi.class);
    }


    private static RetrofitDataAgentImpl sobjInstance;



    public static RetrofitDataAgentImpl getSobjInstance() {

        if(sobjInstance ==null)
        {
            sobjInstance =new RetrofitDataAgentImpl();
        }
        return sobjInstance;
    }

    @Override
    public void loadItemList(int page, String accessToken)
    {
       Call<GetItemResponse> loadItemCall= mItemApi.loadItemList(accessToken,page);
       loadItemCall.enqueue(new Callback<GetItemResponse>() {
           @Override
           public void onResponse(Call<GetItemResponse> call, Response<GetItemResponse> response)
           {
               GetItemResponse itemResponse=response.body();
               if(itemResponse!=null && itemResponse.isResponseOK())
               {
                   Log.i("Retro",itemResponse.getGetNewProducts().size()+"");
                   SuccessGetItemsEvent event=new SuccessGetItemsEvent(itemResponse.getGetNewProducts());
                   EventBus.getDefault().post(event);
               }
               else
               {
                   if(itemResponse==null)
                   {
                       ApiErrorEvent event=new ApiErrorEvent("Empty Respons in Network Call");
                       EventBus.getDefault().post(event);
                   }
               }


           }

           @Override
           public void onFailure(Call<GetItemResponse> call, Throwable t) {
               ApiErrorEvent event=new ApiErrorEvent(t.getMessage());
               EventBus.getDefault().post(event);


           }
       });


    }
}
