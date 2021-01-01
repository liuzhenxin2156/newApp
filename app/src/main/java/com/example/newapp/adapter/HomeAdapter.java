package com.example.newapp.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.newapp.R;
import com.example.newapp.data.RecordData;
import com.example.newapp.data.RootNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author 56454
 */
public class HomeAdapter extends BaseNodeAdapter {

    public HomeAdapter() {
        super();
        // 注册Provider，总共有如下三种方式

        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new RootNodeProvider());
        // 普通的item provider
        addNodeProvider(new SecondNodeProvider());


        addChildClickViewIds(R.id.card_view_all);

    }
    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof RootNode) {
            return 0;
        } else if (node instanceof RecordData) {
            return 1;
        }
        return -1;
    }
    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
}
