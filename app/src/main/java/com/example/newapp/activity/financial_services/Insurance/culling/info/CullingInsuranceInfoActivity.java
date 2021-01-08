package com.example.newapp.activity.financial_services.Insurance.culling.info;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.culling.CullingInsuranceActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.weight.SignatureView;

/**
 * 扑杀险信息
 * @author 56454
 */
public class CullingInsuranceInfoActivity extends BaseActivity {
    private SignatureView  signatureView;
    private Button  add_beneficiary_btn;//+受益人
    private Button   be_insured_people_btn;//+添加被保险人
    private ImageView  back_iv;

    public static void start(Context context) {
        Intent intent = new Intent(context, CullingInsuranceInfoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_culling_insurance_info;
    }

    @Override
    protected void initViews() {
        signatureView  = findViewById(R.id.signature_view);
        add_beneficiary_btn = findViewById(R.id.add_beneficiary_btn);
        be_insured_people_btn = findViewById(R.id.be_insured_people_btn);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }



}
