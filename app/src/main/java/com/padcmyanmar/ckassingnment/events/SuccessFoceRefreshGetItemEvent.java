package com.padcmyanmar.ckassingnment.events;

import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;

import java.util.List;

public class SuccessFoceRefreshGetItemEvent extends SuccessGetItemsEvent
{


    public SuccessFoceRefreshGetItemEvent(List<getNewProductVO> list) {

        super(list);
    }
}
