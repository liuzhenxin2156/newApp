package com.example.newapp.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;


import com.example.newapp.R;
import com.example.newapp.data.GangTieData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

public class GangTieXianHuoAdapter extends BaseRecyclerViewAdapter<GangTieData, BaseRecyclerViewHolder> {
    private List<GangTieData> mDates;
    private Context mContext;
    private GridLayoutManager mGridLayoutManager;

    public GangTieXianHuoAdapter(int layoutResId, List<GangTieData> dataList, Context context, GridLayoutManager gridLayoutManager) {
        super(layoutResId, dataList);
        mDates = dataList;
        mContext = context;
        mGridLayoutManager = gridLayoutManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(BaseRecyclerViewHolder viewHolder, GangTieData data, final int position) {
        TextView title = viewHolder.getView(R.id.name_tv);
        TextView go_num_tv = viewHolder.getView(R.id.go_num_tv);
        TextView money_tv = viewHolder.getView(R.id.money_tv);

        title.setText(data.getName());
        go_num_tv.setText(data.getGoNum());
        money_tv.setText(data.getMoney());
    }
}
