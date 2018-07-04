package com.padcmyanmar.ckassingnment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.padcmyanmar.ckassingnment.R;
import com.padcmyanmar.ckassingnment.adapters.ItemAdapter;
import com.padcmyanmar.ckassingnment.data.models.CKModel;
import com.padcmyanmar.ckassingnment.data.vos.getNewProductVO;
import com.padcmyanmar.ckassingnment.delegate.ItemDelegate;
import com.padcmyanmar.ckassingnment.events.SuccessGetItemsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements ItemDelegate {

    private ItemAdapter mitemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvItem=findViewById(R.id.rv_item);

//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loadinf...");
//        progressDialog.show();

        CKModel.getObjInstance().loadItemList();

        Log.i("MainActivity",CKModel.getObjInstance().getTalksById())

        ItemAdapter itemAdapter=new ItemAdapter(this);
        rvItem.setAdapter(itemAdapter);
       // rvItem.setLayoutManager(new GridLayoutManager(this,2));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        Intent intent=new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("productid",product.getProductId());
        startActivity(intent);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessTedTalks(SuccessGetItemsEvent event)
    {
        Log.i("onSuccessTedTalks","onSuccessTedTalks ");
        mitemAdapter.setProductList(event.getNewProductList());


    }
}
