package com.example.newapp.activity.vip;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.vip.apply_invoice.ApplyInvoiceActivity;
import com.example.newapp.activity.vip.free_vip_service.FreeVipServiceActivity;
import com.example.newapp.activity.vip.free_vip_service.FreeVipServiceAdapter;
import com.example.newapp.activity.vip.refund_logout.RefundLogOutActivity;
import com.example.newapp.activity.vip.special_pay_service.SpecialPayActivity;
import com.example.newapp.activity.vip.toll_vip_service.TollVipServiceActivity;
import com.example.newapp.activity.vip.vip_member.VipMemberActivity;
import com.example.newapp.activity.vip.vip_pay.VipPayActivity;
import com.example.newapp.activity.vip.vip_register.VipRegisterActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

public class VipServiceActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private VipServiceAdapter vipServiceAdapter;
    private ArrayList<RecordData> recordDataList;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, VipServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vip_service;
    }

    @Override
    protected void initViews() {
        addData();
        mBackTv = findViewById(R.id.back_tv);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(v -> finish());

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
                    case 0://会员注册
                        VipRegisterActivity.start(VipServiceActivity.this);
                        break;
                    case 1://会员积分
                        VipMemberActivity.start(VipServiceActivity.this);
                        break;
                    case 2://会员缴费
                        VipPayActivity.start(VipServiceActivity.this);
                        break;
                    case 3://申领发票
                        ApplyInvoiceActivity.start(VipServiceActivity.this);
                        break;
                    case 4://退费注销
                        RefundLogOutActivity.start(VipServiceActivity.this);
                        break;
                    case 5://收费服务
                        TollVipServiceActivity.start(VipServiceActivity.this);
                        break;
                    case 6://免费服务
                        FreeVipServiceActivity.start(VipServiceActivity.this);
                        break;
                    case 7://特种收费服务
                        SpecialPayActivity.start(VipServiceActivity.this);
                        break;
                    case 8:
                        break;
                    case 9:

                        break;
                    case 10:

                        break;

                    case 11:

                        break;
                    case 12:

                        break;


                    case 13:

                        break;

                    case 14:

                        break;

                    case 15:

                        break;

                    case 16:

                        break;
                    case 17:

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
        String [] strings = {"会员注册"," 会员积分"," 会员缴费","申领发票","退费注销","收费服务","免费服务","特别服务"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
