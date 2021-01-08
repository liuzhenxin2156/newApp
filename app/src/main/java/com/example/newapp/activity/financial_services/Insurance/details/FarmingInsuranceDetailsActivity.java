package com.example.newapp.activity.financial_services.Insurance.details;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newapp.R;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;

/**
 * 养殖险保险介绍
 *
 * @author 56454
 */
public class FarmingInsuranceDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;
    private int typeID;
    private Button insured_now_btn;


    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context, int type) {
        Intent intent = new Intent(context, FarmingInsuranceDetailsActivity.class);
        intent.putExtra("id", type);
        context.startActivity(intent);
    }

    /**
     * 获取参数
     */
    private void getArguments() {
        this.typeID = this.getIntent().getIntExtra("id", -1);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_insurance_details;
    }


    @Override
    protected void initViews() {
        getArguments();
        mBackIv = findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(this);
        insured_now_btn  = findViewById(R.id.insured_now_btn);
        insured_now_btn.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.insured_now_btn:
                break;
            default:
        }
    }
}
