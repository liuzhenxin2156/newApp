package com.example.newapp.fragment.main_sow_pig;

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

public class HomePigSowAdapter extends BaseRecyclerViewAdapter<RecordData, BaseRecyclerViewHolder> {
    private List<RecordData> mDates;
    private Context mContext;
    private GridLayoutManager mGridLayoutManager;

    public HomePigSowAdapter(int layoutResId, List<RecordData> dataList, Context context) {
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
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt8));
                break;
            case 8:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt9));
                break;
            case 9:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt10));
                break;
            case 10:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt11));
                break;
            case 11:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt12));
                break;
            case 12:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt30));
                break;
            case 13:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt13));
                break;
            case 14:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt14));
                break;
            case 15:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt15));
                break;
            case 16:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt16));
                break;
            case 17:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt17));
                break;
            case 18:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt18));
                break;
            case 19:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt19));
                break;
            case 20:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt20));
                break;
            case 21:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt21));
                break;
            case 22:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt22));
                break;
            case 23:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt23));
                break;
            case 24:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt24));
                break;
            case 25:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt25));
                break;
            case 26:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt26));
                break;
            case 27:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt27));
                break;
            case 28:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt28));
                break;
            case 29:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt29));
                break;
            case 30:
                imageView.setImageDrawable(mContext.getDrawable(R.drawable.gt29));
                break;
            default:

        }
    }
}
