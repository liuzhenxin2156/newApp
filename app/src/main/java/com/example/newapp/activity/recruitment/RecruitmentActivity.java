package com.example.newapp.activity.recruitment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.animal_protection_products.AnimalProtectionActivity;
import com.example.newapp.activity.construction_investment.ConstructionInvestmentActivity;
import com.example.newapp.activity.quarantine.QuarantineAdapter;
import com.example.newapp.activity.transaction_service.TransactionServiceActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruitmentActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private QuarantineAdapter quarantineAdapter;
    private ArrayList<RecordData> recordDataList;
    private TextView back_level_tv;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, RecruitmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_recruitment;
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
                AppManager.getInstance().finishActivity(ConstructionInvestmentActivity.class);
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
        quarantineAdapter = new QuarantineAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(quarantineAdapter);
        quarantineAdapter.refreshDataList(recordDataList);
        quarantineAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
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


    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"场长招聘/应聘","饲养员招聘/应聘","养殖顾问招聘/应聘","驻场兽医招聘/应聘",
        "后勤保障招聘/应聘"," 配料员招聘/应聘","仓储保管招聘/应聘","其他"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
