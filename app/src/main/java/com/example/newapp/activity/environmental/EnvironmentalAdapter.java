package com.example.newapp.activity.environmental;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.newapp.R;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

public class EnvironmentalAdapter extends BaseRecyclerViewAdapter<RecordData, BaseRecyclerViewHolder> {
    private List<RecordData> mDates;
    private Context mContext;
    private GridLayoutManager mGridLayoutManager;

    public EnvironmentalAdapter(int layoutResId, List<RecordData> dataList, Context context) {
        super(layoutResId, dataList);
        mDates = dataList;
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(BaseRecyclerViewHolder viewHolder, RecordData data, final int position) {
        TextView title = viewHolder.getView(R.id.tv_record);
        ImageView imageView = viewHolder.getView(R.id.iv_record);
        title.setText(data.getTitle());
//        View convertView = viewHolder.getConvertView();
//        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
//        layoutParams.height = mGridLayoutManager.getWidth()/mGridLayoutManager.getSpanCount();
        switch (position) {
            case 0:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 1:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt_2));
                break;
            case 2:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt3));
                break;

            case 3:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt4));
                break;
            case 4:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt5));
                break;
            case 5:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt6));
                break;
            case 6:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt7));
                break;
            case 7:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 8:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 9:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 10:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 11:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 12:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 13:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 14:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 15:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 16:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
            case 17:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt1));
                break;
        }
    }
}
