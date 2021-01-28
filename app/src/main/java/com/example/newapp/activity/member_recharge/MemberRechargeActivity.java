package com.example.newapp.activity.member_recharge;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RechargeTypeBean;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.FullyGridLayoutManager;
import com.example.newapp.weight.RecyclerViewSpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName : NewApp
 * @Author : lzx
 * @Time : 2021/1/25 14:10
 * @Description : 会员充值
 */
public class MemberRechargeActivity extends BaseActivity implements View.OnClickListener,MyItemClickListener {
    private ImageView back_iv;
    private  RecyclerView mRecyclerView;
    private CheckBox cb_wechat;
    private CheckBox  balance_cb_alipay;
    private Button  balance_btn_commit;
    private BalanceAdapter         mAdapter;
    private List<RechargeTypeBean> rechargeTypeBeanList;
    private RelativeLayout rl_wechatpay;
    private RelativeLayout  balance_alipay;


    public static void start(Context context) {
        Intent intent = new Intent(context, MemberRechargeActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_member_recharge;
    }


    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        mRecyclerView = findViewById(R.id.balance_recyclerView);
        cb_wechat = findViewById(R.id.cb_wechat);
        balance_cb_alipay = findViewById(R.id.balance_cb_alipay);
        balance_btn_commit = findViewById(R.id.balance_btn_commit);
        rl_wechatpay = findViewById(R.id.rl_wechatpay);
        balance_alipay = findViewById(R.id.balance_alipay);
        back_iv.setOnClickListener(this);
        cb_wechat.setOnClickListener(this);
        balance_cb_alipay.setOnClickListener(this);
        balance_btn_commit.setOnClickListener(this);
        rl_wechatpay.setOnClickListener(this);
        balance_alipay.setOnClickListener(this);

        initRecyclerView();
    }

    /**
     * 50  300  600  3年/1500
     */
    @Override
    protected void initDatas() {
        rechargeTypeBeanList = new ArrayList<>();
        rechargeTypeBeanList.clear();

        RechargeTypeBean  rechargeTypeBean  =  new RechargeTypeBean();
        rechargeTypeBean.money = 50+"元";
        rechargeTypeBean.times = "一个月";
        rechargeTypeBeanList.add(rechargeTypeBean);

        RechargeTypeBean  rechargeTypeBean1  =  new RechargeTypeBean();
        rechargeTypeBean1.money = 300+"元";
        rechargeTypeBean1.times = "六个月";
        rechargeTypeBeanList.add(rechargeTypeBean1);

        RechargeTypeBean  rechargeTypeBean2  =  new RechargeTypeBean();
        rechargeTypeBean2.money = 600+"元";
        rechargeTypeBean2.times = "一年";
        rechargeTypeBeanList.add(rechargeTypeBean2);

        RechargeTypeBean  rechargeTypeBean3  =  new RechargeTypeBean();
        rechargeTypeBean3.money = 1500+"元";
        rechargeTypeBean3.times = "三年";
        rechargeTypeBeanList.add(rechargeTypeBean3);

        mAdapter.updateDatas(rechargeTypeBeanList);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        manager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new BalanceAdapter(this, MemberRechargeActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION,24);//top间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,24);//底部间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION,24);//左间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION,24);//右间距
        mRecyclerView.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.cb_wechat:
                cb_wechat.setChecked(true);
                balance_cb_alipay.setChecked(false);
                break;
            case R.id.balance_cb_alipay:
                balance_cb_alipay.setChecked(true);
                cb_wechat.setChecked(false);
                break;
            case R.id.rl_wechatpay:
                cb_wechat.setChecked(true);
                balance_cb_alipay.setChecked(false);
                break;
            case R.id.balance_alipay:
                balance_cb_alipay.setChecked(true);
                cb_wechat.setChecked(false);
                break;
            case R.id.balance_btn_commit:
                ToastUtil.showToast(this,"恭喜你，充值成功！");
                break;
            default:
        }
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

}
