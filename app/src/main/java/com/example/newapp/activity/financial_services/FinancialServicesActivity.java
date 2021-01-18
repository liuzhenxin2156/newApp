package com.example.newapp.activity.financial_services;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.adapter.FinancialServicesAdapter;
import com.example.newapp.activity.financial_services.credit.CreditActivity;
import com.example.newapp.activity.financial_services.crowdfunding_pig.CrowdfundingPigActivity;
import com.example.newapp.activity.financial_services.fund.FundActivity;
import com.example.newapp.activity.financial_services.futures.FuturesActivity;
import com.example.newapp.activity.financial_services.trust.TrustActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class FinancialServicesActivity extends BaseActivity {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private FinancialServicesAdapter financialServicesAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, FinancialServicesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_financial_services;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(v -> finish());

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
                    case 0://信贷
                        CreditActivity.start(FinancialServicesActivity.this,2);
                        break;
                    case 1://保险
                        InsuranceActivity.start(FinancialServicesActivity.this,2);
                        break;
                    case 2://期货
                        FuturesActivity.start(FinancialServicesActivity.this,2);
                        break;
                    case 3://信托
                        TrustActivity.start(FinancialServicesActivity.this,2);
                        break;
                    case 4://基金
                        FundActivity.start(FinancialServicesActivity.this,2);
                        break;
                    case 5://参股养猪
                        break;
                    case 6://众筹养猪
                        CrowdfundingPigActivity.start(FinancialServicesActivity.this);
                        break;
                    case 7://金融租赁
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

    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"信贷服务","保险服务","期货服务","信托服务","基金服务","参股养猪","众筹养猪","金融租赁"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
