package com.example.newapp.activity.zcxbx.info;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.data.ScanData;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.GlideUtil;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;

import java.io.File;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/8 11:03
 * @Description :
 */
public class MeansPhotoAdapter extends BaseRecyclerViewAdapter<String, BaseRecyclerViewHolder> {

    private Context mContext;

    public MeansPhotoAdapter(int layoutResId, Context mContext) {
        super(layoutResId, null);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder viewHolder,String data, final int position) {

        ImageView fiv = viewHolder.getView(R.id.iv);
        File fileByPath = FileUtil.getFileByPath(data);
        GlideUtil.loadImage( fileByPath,mContext,fiv,R.drawable.ic_default);//加载URL图片

    }
}
