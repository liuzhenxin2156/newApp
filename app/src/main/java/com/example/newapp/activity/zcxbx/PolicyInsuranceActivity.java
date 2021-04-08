package com.example.newapp.activity.zcxbx;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.newapp.R;
import com.example.newapp.activity.claim_check.ClaimCheckActivity;
import com.example.newapp.activity.publicwelfare.PublicWelfareActivity;
import com.example.newapp.activity.zcxbx.info.InsuranceInfoCollectionActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/6 16:13
 * @Description :
 */
public class PolicyInsuranceActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout  rl;
    private ImageView mBackIv;
    private RelativeLayout claim_check_rl;

    public static void start(Context context) {
        Intent intent = new Intent(context, PolicyInsuranceActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_policy_insurance;
    }

    @Override
    protected void initViews() {
        rl = findViewById(R.id.rl);
        mBackIv  = findViewById(R.id.back_iv);
        claim_check_rl = findViewById(R.id.claim_check_rl);
        rl.setOnClickListener(this);
        claim_check_rl.setOnClickListener(this);
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            case R.id.rl: //投保信息采集
                InsuranceInfoCollectionActivity.start(PolicyInsuranceActivity.this);
                break;
            case  R.id.claim_check_rl://理赔审核
                ClaimCheckActivity.start(PolicyInsuranceActivity.this);
                break;
            default:
        }
    }
}
