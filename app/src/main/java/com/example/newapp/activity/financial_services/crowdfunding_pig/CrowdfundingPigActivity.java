package com.example.newapp.activity.financial_services.crowdfunding_pig;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
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

/**
 * 众筹养猪
 */
public class CrowdfundingPigActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private TextView back_level_tv;
    private CrowdfundingPigAdapter crowdfundingPigAdapter;
    private ArrayList<RecordData> recordDataList;
    private ImageView arrow_kill;
    private int typeID;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, CrowdfundingPigActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_crowdfunding_pig;
    }

    /**
     * 获取参数
     */
    private void getArguments() {
        this.typeID = this.getIntent().getIntExtra("id", -1);
    }
    @Override
    protected void initViews() {
        addData();
        arrow_kill = findViewById(R.id.arrow_kill);
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(layoutManager);
        crowdfundingPigAdapter = new CrowdfundingPigAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(crowdfundingPigAdapter);
        crowdfundingPigAdapter.refreshDataList(recordDataList);
        crowdfundingPigAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
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
                AppManager.getInstance().finishActivity(FinancialServicesActivity.class);
                break;
            default:
        }
    }

    /**
     * 发起众筹、参与众筹
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"发起众筹","参与众筹"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
