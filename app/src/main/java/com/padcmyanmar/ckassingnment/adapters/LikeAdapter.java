package com.padcmyanmar.ckassingnment.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;
import com.padcmyanmar.ckassingnment.viewholders.DetailViewHolder;
import com.padcmyanmar.ckassingnment.viewholders.ItemViewHolder;
import com.padcmyanmar.ckassingnment.viewholders.LikeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeViewHolder> {
    private ItemDelegate mItemDelegate;
    private List<getNewProductVO> mProduct;

    public LikeAdapter(ItemDelegate ItemDelegate) {
        mItemDelegate = ItemDelegate;
        mProduct = new ArrayList<>();

    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_like, parent, false);
        return new LikeViewHolder(view, mItemDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        holder.setItemData(mProduct.get(position));

    }



    @Override
    public int getItemCount() {
        return (mProduct.size());
    }

    public void setProductList(List<getNewProductVO> product) {
        mProduct = product;
        notifyDataSetChanged();
    }
}


