package com.example.newapp.activity.immunization_service;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.pig_food.PigFoodActivity;
import com.example.newapp.activity.pig_food.PigFoodAdapter;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class ImmunizationServiceActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private ImmunizationServiceAdapter immunizationServiceAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, ImmunizationServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_immunization_service;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(v -> finish());

        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(layoutManager);
        immunizationServiceAdapter = new ImmunizationServiceAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(immunizationServiceAdapter);
        immunizationServiceAdapter.refreshDataList(recordDataList);
        immunizationServiceAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:
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
     *   蓝耳病
     *         口蹄疫
     *         猪瘟
     *         非洲猪瘟
     *         萎缩鼻炎
     *         附红细胞体病
     *         猪霉形体病
     *         传染性胸膜炎
     *         猪痢
     *         大肠杆菌病
     *         慢性猪瘟
     *         伪狂犬病
     *         乙型脑炎
     *         衣原体
     *         细小病毒
     *         弓形体病
     *         五号病
     *         传染性胃炎
     *         传染性腹泻
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"蓝耳病"," 口蹄疫"," 猪瘟"," 非洲猪瘟"," 萎缩鼻炎","附红细胞体病","猪霉形体病"
        ,"传染性胸膜炎","猪痢","大肠杆菌病","慢性猪瘟","伪狂犬病","乙型脑炎","衣原体","细小病毒","弓形体病"
        ,"五号病","传染性胃炎","传染性腹泻"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
