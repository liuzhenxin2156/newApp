package com.example.newapp.fragment;

import com.example.newapp.R;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;

public class PigFragment extends BaseFragment {

    public static PigFragment newInstance() {
        PigFragment fragment = new PigFragment();
        return fragment;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.pig_fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
