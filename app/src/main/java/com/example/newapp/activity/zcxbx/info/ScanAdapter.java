package com.example.newapp.activity.zcxbx.info;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.data.ScanData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 9:33
 * @Description :
 */
public class ScanAdapter extends BaseRecyclerViewAdapter<ScanData, BaseRecyclerViewHolder> {

    private Context mContext;

    public ScanAdapter(int layoutResId, Context mContext) {
        super(layoutResId, null);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder viewHolder,ScanData data, final int position) {
        ImageView im_icon = viewHolder.getView(R.id.im_icon);
        TextView tv_scanname = viewHolder.getView(R.id.tv_scanname);
        im_icon.setImageResource(data.getImageViewID());
        tv_scanname.setText(data.getName());
    }
}
