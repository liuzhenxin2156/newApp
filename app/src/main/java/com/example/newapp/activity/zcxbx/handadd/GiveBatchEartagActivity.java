package com.example.newapp.activity.zcxbx.handadd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.activity.zcxbx.selecteartag.ScanEarTagActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.ToastUtil;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 9:45
 * @Description : 手工录入耳标
 */
public class GiveBatchEartagActivity extends BaseActivity {
    private String st;
    private int length;
    private static final int REQUEST_CODE_SCAN = 22;
    int max = 1000;
    public static void start(Activity context) {
        Intent intent = new Intent(context, GiveBatchEartagActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("data", bundle);
        context.startActivityForResult(intent,REQUEST_CODE_SCAN);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_give_batch_eartag;
    }

    @Override
    protected void initViews() {
        ImageView iv_left = (ImageView) findViewById(R.id.back_iv);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        ((TextView) findViewById(R.id.add_eartag_type)).setText(Html.fromHtml(String.format(
                "请录入<font color=red>%s</font>耳标起始和终止号段", "猪")));
        final AutoCompleteTextView et_num = (AutoCompleteTextView) findViewById(R.id.add_eartag_num);
        final EditText et_start = findViewById(R.id.add_eartag_start);
        final EditText et_end =  findViewById(R.id.add_eartag_end);

        final TextView et_length =  findViewById(R.id.eartag_length);
        int a = max - length;
        et_length.setText(Html.fromHtml(String.format(
                "当前最多还可输入<font color=red>%d</font>个耳标记录", a)));
        TextView iv_right =  findViewById(R.id.titlebar_right1);
        iv_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String num = et_num.getText().toString().trim();
                String start = et_start.getText().toString().trim();
                String end = et_end.getText().toString().trim();
                if (num.length() == 7 && start.length() == 8 && end.length() == 8) {
                    try {
                        int n = Integer.valueOf(num);
                        int s = Integer.valueOf(start);
                        int e = Integer.valueOf(end);
                        if (e < s) {
                            ToastUtil.showToast(GiveBatchEartagActivity.this,"起始号段不能大于结束号段");
                        } else if (e - s + 1 > (max-length)) {
                            ToastUtil.showToast(GiveBatchEartagActivity.this,"当前最多可发放" + (max-length) + "耳标记录");
                        } else {
                            //判断扫描耳标是否与动物类型匹配 TODO 判断 noStart
                                Intent i = new Intent();
                                i.putExtra("num", n);
                                i.putExtra("start", s);
                                i.putExtra("end", e);
                                setResult(RESULT_OK, i);
                                finish();
                        }
                    } catch (Exception e) {
                        ToastUtil.showToast(GiveBatchEartagActivity.this,"号码必须是数字");
                    }
                } else {
                    ToastUtil.showToast(GiveBatchEartagActivity.this,"号码长度输入有误");
                }
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
}
