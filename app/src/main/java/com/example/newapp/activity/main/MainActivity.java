package com.example.newapp.activity.main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.newapp.R;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.fragment.main_fragment.HomeMainFragment;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ToastUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author 56454
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView mBottomNavigationView;//底部导航栏
    private List<Fragment> mFragments;
    private int lastIndex;
    RxPermissions rxPermissions = new RxPermissions(this);
    /**
     * 跳转
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * Android 系统 大于6.0  需要申请权限
     * <6.0不需要
     */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            LogUtil.e("SplashActivity---->", "需要申请权限");
            showContacts();
        }
    }
    public void showContacts() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {//开启权限

                        } else {
                            ToastUtil.showToast(MainActivity.this, R.string.permissions_error_message);
                        }
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("SplashActivity---->", "需要申请权限");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initDatas() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeMainFragment.newInstance());
        // 初始化展示第一个Fragment
        setFragmentPosition(0);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initViews() {
        checkPermissions();
        mBottomNavigationView = findViewById(R.id.bottom_view);
        //  BottomNavigationUtils.closeAnimation(mBottomNavigationView);//关闭偏移动画
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    /**
     * 显示 隐藏fragment
     *
     * @param position
     */
    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.fragment_content, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * onNavigationItemSelected监听
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_1:
            default:
                setFragmentPosition(0);
                break;
        }
        return true;
    }


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                ToastUtil.showToast(this, R.string.kill_app);
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}