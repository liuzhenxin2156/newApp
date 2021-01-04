package com.example.newapp.fragment.main_piglet;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * 仔猪
 * @author 56454
 */
public class HomePigLetFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<RecordData> recordDataList;
    private HomePigLetAdapter mainPigLetAdapter;

    public static HomePigLetFragment newInstance() {
        HomePigLetFragment fragment = new HomePigLetFragment();
        return fragment;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.main_piglet_fragment;
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
     * 仔猪销售、仔猪购买、仔猪贷款、仔生猪饲喂、教槽料、仔猪免疫、仔猪检疫、仔猪阉割、仔猪保暖、仔猪降温、仔猪饲养、仔猪消杀、仔猪戴标、仔猪保育
     */
    private void addData(){
        recordDataList = new ArrayList<>();
        String [] strings = {"仔猪销售","仔猪购买","仔猪贷款","仔生猪饲喂","教槽料","仔猪免疫","仔猪检疫","仔猪阉割","仔猪保暖","仔猪降温","仔猪饲养","仔猪消杀","仔猪戴标","仔猪保育"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }
}
