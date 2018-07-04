package com.padcmyanmar.ckassingnment.viewholders;

import android.content.ClipData;
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

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_first)
    ImageView ivFirst;

    @BindView(R.id.tv_itemname)
    TextView itemName;
//    @BindView(R.id.iv_second)
//    ImageView ivSecond;

    private ItemDelegate mItemDelegate;
    private getNewProductVO mProduct;

    public ItemViewHolder(View itemView, ItemDelegate itemDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mItemDelegate=itemDelegate;


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemDelegate.onTapItem(mProduct);

            }
        });

    }
 public void setItemData(getNewProductVO product)
   {
//     mProduct=product;
//     itemName.setText(mProduct.getProductTitle().toString());

        Glide.with(ivFirst.getContext())
                .load(product.getProductImage())
                .into(ivFirst);


}
}
