package com.padcmyanmar.ckassingnment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.adapters.DetailAdapter;
import com.padcmyanmar.ckassingnment.adapters.ItemAdapter;
import com.padcmyanmar.ckassingnment.adapters.LikeAdapter;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;

public class DetailActivity extends AppCompatActivity implements ItemDelegate {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RecyclerView rvDetail=findViewById(R.id.rv_detail);
        RecyclerView rvLike=findViewById(R.id.rv_like);



        DetailAdapter detailAdapter=new DetailAdapter(this);
        rvDetail.setAdapter(detailAdapter);
        rvDetail.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        LikeAdapter likeAdapter=new LikeAdapter(this);
        rvLike.setAdapter(likeAdapter);
        rvLike.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false));

    }


    @Override
    public void onTapItem(getNewProductVO product) {

    }
}
