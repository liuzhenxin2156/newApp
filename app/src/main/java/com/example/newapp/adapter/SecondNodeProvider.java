package com.example.newapp.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.newapp.R;
import com.example.newapp.data.RecordData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SecondNodeProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.gangtie_item;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        if (data == null) {
            return;
        }

        RecordData entity = (RecordData) data;
        helper.setImageResource(R.id.iv_record, entity.getImg());
        helper.setText(R.id.tv_record, entity.getTitle());
        addChildClickViewIds(R.id.card_view_all);
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {

     RecordData recordData = (RecordData) data;
     Log.d("lzx-----》","recordData"+ recordData.getTitle());
        addChildClickViewIds(R.id.card_view_all);
    }
}
