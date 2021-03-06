package com.example.newapp.activity.pork_trade;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.transaction_service.TransactionServiceActivity;
import com.example.newapp.activity.vip.VipServiceAdapter;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class PorkTradeActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private VipServiceAdapter vipServiceAdapter;
    private ArrayList<RecordData> recordDataList;
    private TextView back_level_tv;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, PorkTradeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pork_trade;
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
        vipServiceAdapter = new VipServiceAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(vipServiceAdapter);
        vipServiceAdapter.refreshDataList(recordDataList);
        vipServiceAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://

                        break;
                    case 1://

                        break;
                    case 2://

                        break;
                    case 3://

                        break;
                    case 4://

                        break;
                    case 5://

                        break;
                    case 6://

                        break;
                    case 7:
                        break;
                    case 8://

                        break;
                    case 9:

                        break;
                    case 10:

                        break;

                    case 11:

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
        String [] strings = {"白条"," 猪头"," 猪蹄","白脏","红脏","猪尾","肋排",
                "猪腿（前腿、后退）","猪肘","猪血","猪皮","定制"
               };
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
