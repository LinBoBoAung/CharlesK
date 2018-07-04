package com.padcmyanmar.ckassingnment.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;
import com.padcmyanmar.ckassingnment.viewholders.ItemViewHolder;

public class DetailAdapter extends RecyclerView.Adapter {

    private ItemDelegate mItemDelegate;

    public DetailAdapter(ItemDelegate ItemDelegate) {
        mItemDelegate = ItemDelegate;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.view_holder_detail,parent,false);
        return new ItemViewHolder(view,mItemDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 5;
    }
}
