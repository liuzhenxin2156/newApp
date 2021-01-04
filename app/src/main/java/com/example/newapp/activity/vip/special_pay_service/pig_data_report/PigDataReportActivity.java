package com.example.newapp.activity.vip.special_pay_service.pig_data_report;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.activity.vip.special_pay_service.SpecialPayActivity;
import com.example.newapp.activity.vip.special_pay_service.enddocking.EndDockingAdapter;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.PhoneUtil;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 生猪数据报告
 */
public class PigDataReportActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBackTv;
    private RecyclerView recyclerView;
    private TextView back_level_tv;
    private TextView back_level_tv_one;
    private PigDataReportAdapter pigDataReportAdapter;
    private ArrayList<RecordData> recordDataList;
    private PopupWindow popupWindow;
    private RelativeLayout toolbar_ll;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, PigDataReportActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pig_data_report;
    }

    @Override
    protected void initViews() {
        addData();

        mBackTv = findViewById(R.id.back_tv);
        toolbar_ll = findViewById(R.id.toolbar_ll);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        back_level_tv_one = findViewById(R.id.back_level_tv_one);
        back_level_tv_one.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        pigDataReportAdapter = new PigDataReportAdapter(R.layout.gangtie_item, recordDataList, this);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION, 10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION, 10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION, 10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION, 10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(pigDataReportAdapter);
        pigDataReportAdapter.refreshDataList(recordDataList);
        pigDataReportAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position) {
                    case 0://
                        break;
                    case 1://
                        break;
                    case 2://
                        break;
                    default:

                }
            }

            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back_level_tv://会员服务
                finish();
                AppManager.getInstance().finishActivity(SpecialPayActivity.class);
                break;
            case R.id.back_tv://主页
                showMorePopWindow();
                break;
            case R.id.back_level_tv_one://上级
                finish();
                break;
            default:
        }
    }

    /**
     *地区选择
     * 存栏数
     * 出栏数
     * 屠宰量
     * 母猪存栏量
     * 在途运输数量
     * 一月龄存栏数
     * 二月龄存栏数
     * 三月
     * 四月
     * 五月
     * 六月
     */
    private void addData() {
        recordDataList = new ArrayList<>();
        String[] strings = {"地区选择", "存栏数", "出栏数","屠宰量", "母猪存栏量", "在途运输数量","一月龄存栏数","二月龄存栏数","三月龄存栏数"
        ,"四月龄存栏数","五月龄存栏数","六月龄存栏数"};
        for (String string : strings) {
            RecordData recordData = new RecordData(-1, null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }
    }

    /**
     * 弹出popWindow
     */
    private void showMorePopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        View popView = LayoutInflater.from(this).inflate(R.layout.back_popupwindow, null);
        TextView main_tv = (TextView) popView.findViewById(R.id.main_tv);
        TextView return_tv = (TextView) popView.findViewById(R.id.return_tv);
        TextView return_one_tv = (TextView) popView.findViewById(R.id.return_one_tv);
        return_one_tv.setText("特别服务");
        popupWindow = new PopupWindow(popView, PhoneUtil.dip2px(134), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);// 设置弹出窗体可触摸
        popupWindow.setOutsideTouchable(true); // 设置点击弹出框之外的区域后，弹出框消失
        popupWindow.setAnimationStyle(R.style.TitleMorePopAnimationStyle); // 设置动画
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 设置背景透明
        PhoneUtil.setBackgroundAlpha(this, 0.9f);
        popupWindow.getContentView().measure(0, 0);
        int popWidth = popupWindow.getContentView().getMeasuredWidth();
        int windowWidth = PhoneUtil.getDisplayWidth();
        int xOff = windowWidth - popWidth - PhoneUtil.dip2px(12);    // x轴的偏移量
        popupWindow.showAsDropDown(toolbar_ll, PhoneUtil.dip2px(8), PhoneUtil.dip2px(4));  // 设置弹出框显示的位置
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PhoneUtil.setBackgroundAlpha(PigDataReportActivity.this, 1.0f);
            }
        });

        main_tv.setOnClickListener(new View.OnClickListener() {  // 返回主页
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
                AppManager.getInstance().finishActivity(SpecialPayActivity.class);
                AppManager.getInstance().finishActivity(VipServiceActivity.class);

            }
        });
        return_tv.setOnClickListener(new View.OnClickListener() {    // 返回上上级
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
                AppManager.getInstance().finishActivity(SpecialPayActivity.class);
            }
        });

        return_one_tv.setOnClickListener(new View.OnClickListener() {   // 返回上级
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
            }
        });
    }
}
