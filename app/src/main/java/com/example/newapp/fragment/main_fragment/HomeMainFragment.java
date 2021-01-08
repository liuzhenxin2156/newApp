package com.example.newapp.fragment.main_fragment;

import androidx.fragment.app.Fragment;

import com.example.newapp.R;
import com.example.newapp.weight.CustomViewPager;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.fragment.home.HomeFragment;
import com.example.newapp.fragment.PigMainPagerAdapter;
import com.example.newapp.fragment.main_breed_pig.HomeBreedPigFragment;
import com.example.newapp.fragment.main_pig.HomePigFragment;
import com.example.newapp.fragment.main_piglet.HomePigLetFragment;
import com.example.newapp.fragment.main_sow_pig.HomePigSowFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeMainFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private CustomViewPager customViewPager;
    private PigMainPagerAdapter pigMainPagerAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表


    /**
     * 初始化页面 可以传值
     *
     * @return
     */
    public static HomeMainFragment newInstance() {
        HomeMainFragment fragment = new HomeMainFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.home_main_fragment;
    }

    @Override
    protected void initView() {
        super.initView();
        mTabLayout = mRootView.findViewById(R.id.tab_layout);
        customViewPager = mRootView.findViewById(R.id.fragment_view_pager);
        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(HomeFragment.newInstance());
        list_fragment.add(HomePigLetFragment.newInstance());
        list_fragment.add(HomePigFragment.newInstance());
        list_fragment.add(HomePigSowFragment.newInstance());
        list_fragment.add(HomeBreedPigFragment.newInstance());
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("首页");
        list_title.add("仔猪");
        list_title.add("生猪");
        list_title.add("母猪");
        list_title.add("种猪");

        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称

        for (int i = 0; i < list_title.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(i)));
        }

        pigMainPagerAdapter = new PigMainPagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        customViewPager.setAdapter(pigMainPagerAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(customViewPager);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
