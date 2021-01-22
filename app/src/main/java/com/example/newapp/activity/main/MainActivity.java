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
import com.example.newapp.activity.mine.MineFragment;
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
        mFragments.add(MineFragment.newInstance());
        // 初始化展示第一个Fragment
        setFragmentPosition(0);

        int i = Integer.valueOf("000").intValue();
        LogUtil.d("lzx-----》"," i " + i);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initViews() {
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
                setFragmentPosition(0);
                break;
            case R.id.item_3:
                setFragmentPosition(1);
                break;
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