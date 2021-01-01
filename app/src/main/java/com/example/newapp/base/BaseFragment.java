package com.example.newapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.rx.RxManager;


/**
 * on: 2020/4/21
 * Created By Lzx
 * describe:基类 Fragment
 */
public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements View.OnClickListener{


    protected View mRootView;

    protected P mPresenter;

    private boolean mIsViewPrepared;    // 标识fragment视图已经初始化完毕
    private boolean mHasLoadData;   // 标识已经触发过懒加载数据
    protected RxManager mRxManager = new RxManager();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.i("BaseFragment", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i("BaseFragment", "onCreateView");
        if (this.mRootView == null) {
            this.mRootView = inflater.inflate(this.getContentViewId(), container, false);
            this.initView();
            this.initListener();
        }
        return this.mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtil.i("BaseFragment", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter = this.createPresenter();
        this.mIsViewPrepared = true;
        this.lazyLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("BaseFragment", "setUserVisibleHint isVisibleToUser=" + isVisibleToUser);
        if (isVisibleToUser) {
            this.lazyLoadData();
        }
    }

    @Override
    public void onResume() {
        LogUtil.i("BaseFragment", "onResume");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        LogUtil.i("BaseFragment", "onDestroyView");
        super.onDestroyView();
        if (this.mRootView != null && this.mRootView.getParent() != null) {
            ((ViewGroup) this.mRootView.getParent()).removeView(this.mRootView);
        }
        this.mIsViewPrepared = false;
    }

    @Override
    public void onDestroy() {
        LogUtil.i("BaseFragment", "onDestroy");
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.unSubscribe();
        }
        if (null != this.mRxManager) {
            this.mRxManager.clear();
            this.mRxManager = null;
        }
    }

    /**
     * 懒加载数据
     */
    private void lazyLoadData() {
        LogUtil.i("BaseFragment", "===UserVisibleHint==" + super.getUserVisibleHint() + ",mHasLoadData==" + mHasLoadData + ",mIsViewPrepared==" + this.mIsViewPrepared);
        if (super.getUserVisibleHint() && !this.mHasLoadData && this.mIsViewPrepared) {
            LogUtil.i("BaseFragment", "initData");
            this.initData();
            this.mHasLoadData = true;
        }
    }


    public RxManager getRxManager() {
        return mRxManager == null ? new RxManager() : mRxManager;
    }
    /**
     * 获取布局文件id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化组件
     */
    protected void initView() {

    }

    /**
     * 初始化监听器
     */
    protected void initListener() {

    }

    /**
     * 创建presenter
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();

        }
}
