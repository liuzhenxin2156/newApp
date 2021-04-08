package com.example.newapp.activity.zcxbx.selecteartag;

import android.content.Context;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.data.EarTag;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 9:33
 * @Description :
 */
public class ScanEarTagAdapter extends BaseRecyclerViewAdapter<EarTag, BaseRecyclerViewHolder> {

    private Context mContext;

    public ScanEarTagAdapter(int layoutResId, Context mContext) {
        super(layoutResId, null);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder viewHolder,EarTag data, final int position) {
        TextView tv_scanname = viewHolder.getView(R.id.tv_no);
        tv_scanname.setText(data.getEartagno());

    }
}
