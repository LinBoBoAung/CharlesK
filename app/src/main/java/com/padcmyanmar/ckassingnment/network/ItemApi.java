package com.padcmyanmar.ckassingnment.network;

import com.padcmyanmar.ckassingnment.utils.ItemConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ItemApi
{
    @FormUrlEncoded
    @POST(ItemConstants.GET_NEWS)
Call<GetItemResponse> loadItemList(
       @Field(ItemConstants.PARAM_ACCESS_TOKEN) String accessToken,
       @Field(ItemConstants.PARAM_PAGE) int page);
}
