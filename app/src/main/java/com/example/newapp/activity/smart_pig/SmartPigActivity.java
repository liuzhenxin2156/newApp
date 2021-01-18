package com.example.newapp.activity.smart_pig;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.ai.AiActivity;
import com.example.newapp.activity.animal_protection_products.AnimalProtectionActivity;
import com.example.newapp.activity.bulk_trade.BulkTradeActivity;
import com.example.newapp.activity.live_pig.LivePigActivity;
import com.example.newapp.activity.means_production.MeansProductionActivity;
import com.example.newapp.activity.pig_trade.PigTradeActivity;
import com.example.newapp.activity.pork_trade.PorkTradeActivity;
import com.example.newapp.activity.software.SoftWareActivity;
import com.example.newapp.activity.transaction_service.TransactionServiceAdapter;
import com.example.newapp.activity.transport_capacity.TransportCapacityActivity;
import com.example.newapp.activity.video.VideoActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class SmartPigActivity extends BaseActivity {

    private TextView mBackTv;
    private RecyclerView recyclerView;
    private SmartPigAdapter smartPigAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, SmartPigActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_smart_pig;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(v -> finish());

        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        smartPigAdapter = new SmartPigAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(smartPigAdapter);
        smartPigAdapter.refreshDataList(recordDataList);
        smartPigAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://人工智能
                        AiActivity.start(SmartPigActivity.this);
                        break;
                    case 1://软件定制
                        SoftWareActivity.start(SmartPigActivity.this);
                        break;
                    case 2://视频监管
                        VideoActivity.start(SmartPigActivity.this);
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

    //仔猪","生猪","母猪","种猪","精液"
    /**
     * 仔猪、生猪、母猪、种猪、精液、
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"人工智能","软件定制","视频监管"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
