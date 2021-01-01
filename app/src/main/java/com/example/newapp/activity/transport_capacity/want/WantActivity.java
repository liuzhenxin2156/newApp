package com.example.newapp.activity.transport_capacity.want;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.activity.transport_capacity.TransportCapacityActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.AppManager;

public class WantActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;

    private TextView back_level_tv;



    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, WantActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_want;
    }

    @Override
    protected void initViews() {

        mBackTv = findViewById(R.id.back_tv);

        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);

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
                AppManager.getInstance().finishActivity(TransportCapacityActivity.class);
                break;
            default:
        }
    }

}
