package com.example.newapp.activity.vip.free_vip_service;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.activity.vip.free_vip_service.legal.LegalActivity;
import com.example.newapp.activity.vip.toll_vip_service.special_offer.VipSpecialOfferActivity;
import com.example.newapp.activity.vip.free_vip_service.statute.StatuteActivity;
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
 * 免费会员服务
 */
public class FreeVipServiceActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private TextView back_level_tv;
    private FreeVipServiceAdapter freeVipServiceAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, FreeVipServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_free_vip_service;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        freeVipServiceAdapter = new FreeVipServiceAdapter(R.layout.gangtie_item, recordDataList,this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(freeVipServiceAdapter);
        freeVipServiceAdapter.refreshDataList(recordDataList);
        freeVipServiceAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://
                        break;
                    case 1://
                        break;
                    case 2:
                        //法规咨询
                        StatuteActivity.start(FreeVipServiceActivity.this);
                        break;
                    case 3://
                        break;
                    case 4://
                        break;
                    case 5://
                        break;
                    case 6:
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
                AppManager.getInstance().finishActivity(VipServiceActivity.class);
                break;
            default:
        }
    }

    /**
     * 期货资讯
     *                                信贷咨询
     *                                保险咨询
     *                                现货资讯
     *                                防疫咨询
     *                                养殖咨询
     *                                法规咨询：农业执法机构提供
     *                                法律咨询：咨询、顾问（律师提供）
     *                                会员特价
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"保险咨询","信贷咨询","法规咨询"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
