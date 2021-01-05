package com.example.newapp.activity.financial_services.futures;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.adapter.FinancialServicesAdapter;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class FuturesActivity extends BaseActivity {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private FinancialServicesAdapter financialServicesAdapter;
    private ArrayList<RecordData> recordDataList;
    private TextView back_level_tv;
    private int typeID;
    private ImageView arrow_kill;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context,int type) {
        Intent intent = new Intent(context, FuturesActivity.class);
        intent.putExtra("id", type);
        context.startActivity(intent);
    }

    /**
     * 获取参数
     */
    private void getArguments() {
        this.typeID = this.getIntent().getIntExtra("id", -1);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_futures;
    }

    @Override
    protected void initViews() {
        addData();
        getArguments();
        arrow_kill = findViewById(R.id.arrow_kill);
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
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
                    case 0://

                        break;
                    case 1://

                        break;
                    case 2://自主填报

                        break;
                    default:

                }
            }

            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
        if (typeID==1){
            arrow_kill.setVisibility(View.GONE);
            back_level_tv.setVisibility(View.GONE);
        }
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
                if (typeID==1){
                    finish();
                }else {
                    finish();
                    AppManager.getInstance().finishActivity(FinancialServicesActivity.class);
                }
                break;
            default:
        }
    }

    /**
     * 期货：套期保值、基差、生猪期货、玉米期货、大豆期货、豆粕期货、指数期货
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"套期保值","基差","生猪期货","玉米期货","大豆期货","豆粕期货","指数期货"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
