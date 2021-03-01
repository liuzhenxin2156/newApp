package com.example.newapp.activity.mine.bulletin;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newapp.R;
import com.example.newapp.activity.mine.changeinfo.ChangeInfoActivity;
import com.example.newapp.activity.mine.set.accountsafe.AccountSafeActivity;
import com.example.newapp.activity.mine.set.agreement.AgreementActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;

/**
 * @ProjectName : NewApp
 * @Author :  lzx
 * @Time : 2021/1/22 17:36
 * @Description : 公告栏
 */
public class BulletinActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_iv;

    private LinearLayout ll_agreement;//用户协议
    private LinearLayout ll_privacy;//隐私政策
    private LinearLayout ll_register;//隐私政策

    public static void start(Context context) {
        Intent intent = new Intent(context, BulletinActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_bulletin;
    }

    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        back_iv.setOnClickListener(v -> finish());
        ll_agreement = findViewById(R.id.ll_agreement);
        ll_privacy = findViewById(R.id.ll_privacy);
        ll_register = findViewById(R.id.ll_agreement_register);
        ll_agreement.setOnClickListener(this);
        ll_privacy.setOnClickListener(this);
        ll_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_agreement:
                AgreementActivity.start(this, 0);//0为用户服务
                break;
            case R.id.ll_privacy:
                AgreementActivity.start(this, 1);//1为隐私政策
                break;
            case R.id.ll_agreement_register://账号与安全
                AgreementActivity.start(this, 3);//1为隐私政策
                break;
            default:
        }
    }
    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
