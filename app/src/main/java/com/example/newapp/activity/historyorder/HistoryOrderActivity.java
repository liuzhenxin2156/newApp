package com.example.newapp.activity.historyorder;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.example.newapp.R;
import com.example.newapp.activity.mine.bulletin.BulletinActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;

/**
 * @ProjectName : NewApp
 * @Author : lzx
 * @Time : 2021/1/25 9:07
 * @Description : 历史订单
 */
public class HistoryOrderActivity extends BaseActivity {
    private ImageView back_iv;

    public static void start(Context context) {
        Intent intent = new Intent(context, HistoryOrderActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_history_order;
    }

    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        back_iv.setOnClickListener(v -> finish());
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
