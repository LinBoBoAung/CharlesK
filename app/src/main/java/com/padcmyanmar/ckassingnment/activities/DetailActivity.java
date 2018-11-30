package com.padcmyanmar.ckassingnment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.adapters.DetailAdapter;
import com.padcmyanmar.ckassingnment.adapters.ItemAdapter;
import com.padcmyanmar.ckassingnment.adapters.LikeAdapter;
import com.padcmyanmar.ckassingnment.data.models.CKModel;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;
import com.padcmyanmar.ckassingnment.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements ItemDelegate {

    // @BindView(R.id.swipe_refresh_layout)
    //SwipeRefreshLayout swipeRefreshLayout;

    private DetailAdapter mDetailAdapter;
    private LikeAdapter mLikeAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RecyclerView rvDetail = findViewById(R.id.rv_detail);
        RecyclerView rvLike = findViewById(R.id.rv_like);


        DetailAdapter detailAdapter = new DetailAdapter(this);
        rvDetail.setAdapter(detailAdapter);
        rvDetail.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));


        LikeAdapter likeAdapter = new LikeAdapter(this);
        rvLike.setAdapter(likeAdapter);
        rvLike.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        String productId = getIntent().getStringExtra("productid");
        Log.d("DetailActivity", "productid" + productId);


        getNewProductVO product = CKModel.getObjInstance().getTalksById(productId);


    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessgetItem(SuccessGetItemsEvent event) {
        Log.i("onSuccessItem", "onSuccessgetItem");
        mDetailAdapter.setProductList(event.getNewProductList());
        mLikeAdapter.setProductList(event.getNewProductList());
        //swipeRefreshLayout.setRefreshing(false);


    }


    @Override
    public void onTapItem(getNewProductVO product) {

    }
}
