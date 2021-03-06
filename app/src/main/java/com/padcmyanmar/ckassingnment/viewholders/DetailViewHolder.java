package com.padcmyanmar.ckassingnment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_bag)
    ImageView ivBag;


    private ItemDelegate mItemDelegate;
    private getNewProductVO mProduct;
    private View view;

    public DetailViewHolder(View itemView, ItemDelegate itemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemDelegate = itemDelegate;
        view = itemView;


    }

    public void setItemData(final getNewProductVO product) {
        mProduct = product;

        Glide.with(ivBag.getContext())
                .load(product.getProductImage())
                .into(ivBag);


    }
}