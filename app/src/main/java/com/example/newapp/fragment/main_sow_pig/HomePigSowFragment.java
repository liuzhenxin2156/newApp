package com.example.newapp.fragment.main_sow_pig;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.fragment.main_piglet.HomePigLetAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * 母猪
 * @author 56454
 */
public class HomePigSowFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<RecordData> recordDataList;
    private HomePigLetAdapter mainPigLetAdapter;

    public static HomePigSowFragment newInstance() {
        HomePigSowFragment fragment = new HomePigSowFragment();
        return fragment;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.main_pig_sow_fragment;
    }


    @Override
    protected void initView() {
        super.initView();
        addData();
        recyclerView = mRootView.findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        mainPigLetAdapter = new HomePigLetAdapter(R.layout.gangtie_item, recordDataList,getActivity());
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(mainPigLetAdapter);
        mainPigLetAdapter.refreshDataList(recordDataList);
        mainPigLetAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){

                    default:
                }
            }

            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 母猪购置、母猪出售、母猪监测、母猪免疫、母猪检疫、母猪运输、母猪屠宰、母生猪饲喂、母猪保险、母猪贷款、母猪配种、母猪消杀
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"母猪购置","母猪出售","母猪监测","母猪免疫","母猪检疫","母猪运输","母猪屠宰","母生猪饲喂","母猪保险","母猪贷款","母猪配种","母猪消杀"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
