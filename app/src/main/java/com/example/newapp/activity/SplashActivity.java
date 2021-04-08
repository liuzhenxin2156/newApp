package com.example.newapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.newapp.R;
import com.example.newapp.activity.login.LoginActivity;
import com.example.newapp.activity.main.MainActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.sp.FirstSpUtils;
import com.example.newapp.sp.UserSp;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

//192.173.104


/**
 * on: 2020/4/21
 * Created By Lzx
 * describe: 启动页  车辆定位
 */
public class SplashActivity extends BaseActivity {
    private AlertDialog exitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        hideBottomUIMenu();
        boolean firstShow = FirstSpUtils.getInstance().getFirstShow();
        LogUtil.i("lzx--------》", "" + firstShow);
        if (firstShow) { //为true不显示
            checkPermissions();
        } else {
            showFirstDialog();
        }
    }

    /**
     * Android 系统 大于6.0  需要申请权限
     * <6.0不需要
     */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            LogUtil.e("SplashActivity---->", "需要申请权限");
            showContacts();
        } else {
            LogUtil.e("SplashActivity---->", "权限成功");

            startMain();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @SuppressLint("CheckResult")
    public void showContacts() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {//开启权限
                            startMain();
                        } else {
                            startMain();
                            ToastUtil.showToast(SplashActivity.this, R.string.permissions_error_message);
                        }
                    }
                });
    }


    /**
     * 休眠3s进入 广告业 后期修改 网络加载广告
     */
    private void startMain() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (UserSp.getInstance().getIsLogin()){
                        MainActivity.start(this);
                    }else {
                        LoginActivity.start(SplashActivity.this);
                    }
                    finish();
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 协议框
     */
    private void showFirstDialog() {
        String str = getResources().getString(R.string.show_privacy_message);
        View view = getLayoutInflater().inflate(R.layout.dialog_first_service, null);
        exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView cancelTv = (TextView) view.findViewById(R.id.negative_tv);//取消按钮
        TextView confirmTv = (TextView) view.findViewById(R.id.positive_tv);//确定按钮
        TextView contentTv = (TextView) view.findViewById(R.id.content_tv);

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(str);
        final int start = str.indexOf("《");//第一个出现的位置
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.C4));
                ds.setUnderlineText(false);
            }
        }, start, start + 6, 0);

        int end = str.lastIndexOf("《");
        ssb.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.C4));
                ds.setUnderlineText(false);
            }
        }, end, end + 6, 0);

        contentTv.setMovementMethod(LinkMovementMethod.getInstance());
        contentTv.setText(ssb, TextView.BufferType.SPANNABLE);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
                AppManager.getInstance().AppExit();//退出
            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstSpUtils.getInstance().setFirstShow(true);
                boolean firstShow = FirstSpUtils.getInstance().getFirstShow();
                LogUtil.d("lzx------------》", "存过的" + firstShow);
                checkPermissions();
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
        Window alertWindow = exitDialog.getWindow();
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lParams = alertWindow.getAttributes();
       // lParams.height = (int) (display.getHeight() * 0.8);
        lParams.width = (int) (display.getWidth() * 0.85);
        alertWindow.setAttributes(lParams);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        //for new api versions.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }


}
