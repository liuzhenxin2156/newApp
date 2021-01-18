package com.example.newapp.activity.construction_investment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.breeding_consulting.BreeConsultActivity;
import com.example.newapp.activity.breeding_service.BreedingServiceAdapter;
import com.example.newapp.activity.cooperation.CooperationActivity;
import com.example.newapp.activity.environmental.EnvironmentalActivity;
import com.example.newapp.activity.epidemic.EpidemicActivity;
import com.example.newapp.activity.immunization_service.ImmunizationServiceActivity;
import com.example.newapp.activity.land_info.LandInfoActivity;
import com.example.newapp.activity.old_fashioned.OldFashionedActivity;
import com.example.newapp.activity.pig_farm.PigFarmActivity;
import com.example.newapp.activity.pig_food.PigFoodActivity;
import com.example.newapp.activity.recruitment.RecruitmentActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class ConstructionInvestmentActivity extends BaseActivity {

    private TextView mBackTv;
    private RecyclerView recyclerView;
    private BreedingServiceAdapter breedingServiceAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, ConstructionInvestmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_construction;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(v -> finish());

        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        breedingServiceAdapter = new BreedingServiceAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(breedingServiceAdapter);
        breedingServiceAdapter.refreshDataList(recordDataList);
        breedingServiceAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://猪场建设
                        PigFarmActivity.start(ConstructionInvestmentActivity.this);
                        break;
                    case 1://人员招聘
                        RecruitmentActivity.start(ConstructionInvestmentActivity.this);
                        break;
                    case 2://合作招募
                        CooperationActivity.start(ConstructionInvestmentActivity.this);
                        break;
                    case 3://土地信息
                        LandInfoActivity.start(ConstructionInvestmentActivity.this);
                        break;
                    case 4://环保测评
                        EnvironmentalActivity.start(ConstructionInvestmentActivity.this);
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
        String [] strings = {"猪场建设","人员招聘","合作招募","土地信息","环保测评"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
