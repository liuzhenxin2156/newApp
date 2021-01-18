package com.example.newapp.activity.transport_capacity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.adapter.FinancialServicesAdapter;
import com.example.newapp.activity.financial_services.credit.CreditActivity;
import com.example.newapp.activity.financial_services.fund.FundActivity;
import com.example.newapp.activity.financial_services.futures.FuturesActivity;
import com.example.newapp.activity.financial_services.trust.TrustActivity;
import com.example.newapp.activity.transaction_service.TransactionServiceActivity;
import com.example.newapp.activity.transport_capacity.capacity_release.CapacityReleaseActivity;
import com.example.newapp.activity.transport_capacity.demand.DemandActivity;
import com.example.newapp.activity.transport_capacity.vehicle_filing.VehicleFilingActivity;
import com.example.newapp.activity.transport_capacity.want.WantActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class TransportCapacityActivity extends BaseActivity implements View.OnClickListener {
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
        Intent intent = new Intent(context, TransportCapacityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transport_capacity;
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
                    case 0://车辆备案
                        VehicleFilingActivity.start(TransportCapacityActivity.this);
                        break;
                    case 1://运力发布
                        CapacityReleaseActivity.start(TransportCapacityActivity.this);
                        break;
                    case 2://运输需求
                        DemandActivity.start(TransportCapacityActivity.this);
                        break;
                    case 3://我要运猪
                        WantActivity.start(TransportCapacityActivity.this);
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
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.back_tv:
                finish();
                break;
            default:
        }
    }
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"车辆备案","我能运猪","车辆分布","我要运猪"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }


}
