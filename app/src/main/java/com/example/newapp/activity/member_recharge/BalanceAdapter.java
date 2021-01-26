package com.example.newapp.activity.member_recharge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.data.RechargeTypeBean;
import com.example.newapp.utils.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/1/25 15:35
 * @Description :
 */
public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> implements View.OnClickListener {


    private Context mContext;
    private int mSelectedItem = 0;
    private List<RechargeTypeBean> dataList;
    private MyItemClickListener    mListener;

    public BalanceAdapter(Context mContext, MyItemClickListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
        this.dataList = new ArrayList<>();
    }

    @Override
    public BalanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_balance, parent, false);
        return new BalanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BalanceViewHolder holder, int position) {
        if (null != dataList && dataList.size() > 0) {
            RechargeTypeBean bean = dataList.get(position);
            holder.money.setText(bean.money);

                holder.times.setText(bean.times);

            if (position == mSelectedItem) {
                holder.money.setTextColor(mContext.getResources().getColor(R.color.C4));
                holder.times.setTextColor(mContext.getResources().getColor(R.color.C4));
                holder.balance_item_rl.setBackgroundResource(R.drawable.shape_bg_gray_true);
                holder.checkIv.setVisibility(View.VISIBLE);
            }
            else {
                holder.money.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                holder.times.setTextColor(mContext.getResources().getColor(R.color.color_3));
                holder.balance_item_rl.setBackgroundResource(R.drawable.shape_bg_gray);
                holder.checkIv.setVisibility(View.GONE);
            }
            holder.balance_item_rl.setOnClickListener(this);
            holder.balance_item_rl.setTag(holder);
        }
    }

    @Override
    public int getItemCount() {
        if (null != dataList && dataList.size() > 0) {
            return dataList.size();
        }
        return 0;
    }

    public void updateDatas(List<RechargeTypeBean> list) {
        if (null != list && list.size() > 0) {
            dataList.clear();
            dataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getSelectItem() {
        return mSelectedItem;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.balance_item_rl:
                if (mListener != null) {
                    BalanceViewHolder holder = (BalanceViewHolder) v.getTag();
                    mSelectedItem = holder.getPosition();
                    mListener.onItemClick(v, holder.getPosition());
                    notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {


        TextView money;

        TextView       times;

        RelativeLayout balance_item_rl;

        ImageView checkIv;

        public BalanceViewHolder(View itemView) {
            super(itemView);
            money = itemView.findViewById(R.id.money);
            times = itemView.findViewById(R.id.times);
            balance_item_rl = itemView.findViewById(R.id.balance_item_rl);
            checkIv = itemView.findViewById(R.id.check_iv);
        }
    }
}
