package com.example.newapp.activity.live_pig;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.adapter.FinancialServicesAdapter;
import com.example.newapp.activity.financial_services.credit.CreditActivity;
import com.example.newapp.activity.financial_services.fund.FundActivity;
import com.example.newapp.activity.financial_services.futures.FuturesActivity;
import com.example.newapp.activity.financial_services.trust.TrustActivity;
import com.example.newapp.activity.live_pig.breed.BreedActivity;
import com.example.newapp.activity.live_pig.live.LiveActivity;
import com.example.newapp.activity.live_pig.piglet.PigLetActivity;
import com.example.newapp.activity.live_pig.semen.SemenActivity;
import com.example.newapp.activity.live_pig.sows.SowsActivity;
import com.example.newapp.activity.transaction_service.TransactionServiceActivity;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class LivePigActivity extends BaseActivity {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private FinancialServicesAdapter financialServicesAdapter;
    private ArrayList<RecordData> recordDataList;
    private TextView back_level_tv;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LivePigActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_live_pig;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                AppManager.getInstance().finishActivity(TransactionServiceActivity.class);
            }
        });
        back_level_tv = findViewById(R.id.back_level_tv);

        back_level_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        financialServicesAdapter = new FinancialServicesAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(financialServicesAdapter);
        financialServicesAdapter.refreshDataList(recordDataList);
        financialServicesAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://仔猪
                        PigLetActivity.start(LivePigActivity.this);
                        break;
                    case 1://生猪
                        LiveActivity.start(LivePigActivity.this);
                        break;
                    case 2://母猪
                        SowsActivity.start(LivePigActivity.this);
                        break;
                    case 3://
                        BreedActivity.start(LivePigActivity.this);
                        break;
                    case 4://
                        SemenActivity.start(LivePigActivity.this);
                        break;
                    default:

                }
            }

            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 仔猪、生猪、母猪、种猪、精液、
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"仔猪","生猪","母猪","种猪","精液"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
