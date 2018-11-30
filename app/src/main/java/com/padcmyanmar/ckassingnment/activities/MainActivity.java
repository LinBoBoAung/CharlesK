package com.padcmyanmar.ckassingnment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.adapters.ItemAdapter;
import com.padcmyanmar.ckassingnment.data.models.CKModel;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;
import com.padcmyanmar.ckassingnment.events.ApiErrorEvent;
import com.padcmyanmar.ckassingnment.events.SuccessFoceRefreshGetItemEvent;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;
import com.padcmyanmar.ckassingnment.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ItemDelegate {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

   // @BindView(R.id.vp_empty)
    //EmptyViewPod vpempty;


    private ItemAdapter mitemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        RecyclerView rvItem = findViewById(R.id.rv_item);
        rvItem.setLayoutManager(new GridLayoutManager(this, 2));
        rvItem.setHasFixedSize(true);
        mitemAdapter = new ItemAdapter(this);
        swipeRefreshLayout.setRefreshing(true);
        rvItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    CKModel.getObjInstance().loadItemList();

                }
            }

            private boolean isListEndReached = false;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visisbleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItemCount = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (visisbleItemCount + pastVisibleItemCount < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });

        rvItem.setAdapter(mitemAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // CKModel.getObjInstance().forceRefreshItemList();
                CKModel.getObjInstance().loadItemList();


            }
        });
      //  vpempty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.emtpy_msg));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void onTapItem(getNewProductVO product) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("productid", product.getProductId());//idကိုပို့ေပးလိုက္ခ်င္လို့
        startActivity(intent);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessgetItem(SuccessGetItemsEvent event) {
        Log.i("onSuccessItem", "onSuccessgetItem");
       // mitemAdapter.appendItemList(event.getNewProductList());//replace မလုပ္ဘဲdataကိုappend လုပ္တာ//setဆိုလုုိ့ရွိရင္replaceလုပ္တာ



    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onFailgetItem(ApiErrorEvent event) {
//
//        swipeRefreshLayout.setRefreshing(false);
//        vpempty.setVisibility(View.VISIBLE);
//
//
//    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onSuccessForceRefreshGetItem(SuccessFoceRefreshGetItemEvent event) {
//        mitemAdapter.setProductList(event.getNewProductList());
//        swipeRefreshLayout.setRefreshing(false);
//    }
}
