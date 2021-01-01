package com.example.newapp.adapter;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.newapp.R;
import com.example.newapp.data.RootNode;
import com.example.newapp.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RootNodeProvider extends BaseNodeProvider {

    private List<BaseNode> childNode;

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.def_section_head;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        RootNode entity = (RootNode) data;
        helper.setText(R.id.header, entity.getTitle());
        helper.setImageResource(R.id.iv, R.drawable.arrow_r);
        setArrowSpin(helper, data, false);
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode item, @NotNull List<?> payloads) {
        super.convert(helper, item, payloads);
        for (Object payload : payloads) {
            if (payload instanceof Integer && (int) payload == HomeAdapter.EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, item, true);
            }
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
       getAdapter().expandOrCollapse(position, true, true, HomeAdapter.EXPAND_COLLAPSE_PAYLOAD);
        RootNode entity = (RootNode) data;
            if (entity.getChildNode()==null){
              ToastUtil.showToast(getContext(),"暂无数据,无法展开");
            }
    }

    private void setArrowSpin(BaseViewHolder helper, BaseNode data, boolean isAnimate) {
        RootNode entity = (RootNode) data;

        ImageView imageView = helper.getView(R.id.iv);
        childNode = entity.getChildNode();
            if (childNode!=null){
                if (entity.isExpanded()) {
                    if (isAnimate) {
                        ViewCompat.animate(imageView).setDuration(200)
                                .setInterpolator(new DecelerateInterpolator())
                                .rotation(0f)
                                .start();
                    } else {
                        imageView.setRotation(0f);
                    }
                } else {
                    if (isAnimate) {
                        ViewCompat.animate(imageView).setDuration(200)
                                .setInterpolator(new DecelerateInterpolator())
                                .rotation(90f)
                                .start();
                    } else {
                        imageView.setRotation(90f);
                    }
                }
            }else {
                imageView.setImageDrawable(getContext().getDrawable(R.drawable.arrow_b));
            }

        }

}
