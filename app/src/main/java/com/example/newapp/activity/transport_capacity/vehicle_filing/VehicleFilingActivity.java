package com.example.newapp.activity.transport_capacity.vehicle_filing;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
import com.example.newapp.activity.financial_services.adapter.FinancialServicesAdapter;
import com.example.newapp.activity.financial_services.credit.CreditActivity;
import com.example.newapp.activity.transport_capacity.TransportCapacityActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleFilingActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;

    private TextView back_level_tv;



    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, VehicleFilingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vehicle_filing;
    }

    @Override
    protected void initViews() {

        mBackTv = findViewById(R.id.back_tv);

        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);

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
            case R.id.back_level_tv:
                finish();
                break;
            case R.id.back_tv:
                finish();
                AppManager.getInstance().finishActivity(TransportCapacityActivity.class);
                break;
            default:
        }
    }

}
