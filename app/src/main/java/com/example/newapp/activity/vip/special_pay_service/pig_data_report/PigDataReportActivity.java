package com.example.newapp.activity.vip.special_pay_service.pig_data_report;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.activity.vip.special_pay_service.SpecialPayActivity;
import com.example.newapp.activity.vip.special_pay_service.enddocking.EndDockingAdapter;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.AppConstants;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.PhoneUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Spinner province_spinner;
    private Spinner city_spinner;
    private TextView report_spinner;
    private CardView all_report_ll;
    private TextView city_tv;
    private TextView column_volume_tv;
    private TextView slaughter_tv;
    private TextView sow_slaughter_tv;
    private TextView transit_tv;
    private TextView one_month_stocks_tv;
    private TextView two_month_stocks_tv;
    private TextView three_month_stocks_tv;
    private TextView four_month_stocks_tv;
    private TextView five_month_stocks_tv;
    private TextView six_month_stocks_tv;
    private AlertDialog alertDialog;
    private LinearLayout report_ll;
    private String province;
    private String city;
    private Button add_btn;
    private TextView inventory_tv;
    private List<String> list;


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
        mBackTv = findViewById(R.id.back_tv);
        toolbar_ll = findViewById(R.id.toolbar_ll);
        recyclerView = findViewById(R.id.recyclerview);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        back_level_tv_one = findViewById(R.id.back_level_tv_one);
        back_level_tv_one.setOnClickListener(this);
        province_spinner = findViewById(R.id.province_spinner);
        city_spinner = findViewById(R.id.city_spinner);
        report_spinner = findViewById(R.id.report_spinner);
        setProvince();
        setCity();
        report_spinner.setOnClickListener(this);
        all_report_ll = findViewById(R.id.all_report_ll);

        city_tv = findViewById(R.id.city_tv);
        column_volume_tv = findViewById(R.id.column_volume_tv);
        slaughter_tv = findViewById(R.id.slaughter_tv);
        sow_slaughter_tv = findViewById(R.id.sow_slaughter_tv);
        transit_tv = findViewById(R.id.transit_tv);
        one_month_stocks_tv = findViewById(R.id.one_month_stocks_tv);
        two_month_stocks_tv = findViewById(R.id.two_month_stocks_tv);
        three_month_stocks_tv = findViewById(R.id.three_month_stocks_tv);
        four_month_stocks_tv = findViewById(R.id.four_month_stocks_tv);
        five_month_stocks_tv = findViewById(R.id.four_month_stocks_tv);
        six_month_stocks_tv = findViewById(R.id.six_month_stocks_tv);
        report_ll = findViewById(R.id.report_ll);
        report_ll.setOnClickListener(this);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        inventory_tv = findViewById(R.id.inventory_tv);



    }

    @Override
    protected void initDatas() {
        list = new ArrayList<>();
        list.clear();
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
            case R.id.report_spinner:
                setReport();
                break;
            case R.id.add_btn:
                all_report_ll.setVisibility(View.VISIBLE);
                setShow();
                if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city)) {
                    city_tv.setText("您选择得城市:" + province +"->" +  city);
                } else if (!TextUtils.isEmpty(province) && TextUtils.isEmpty(city)) {
                    city_tv.setText("您选择得城市:" + province);
                }
                break;
            default:
        }
    }

    private void setProvince() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_textview, AppConstants.TYPE.stringsAnimal);
        adapter.setDropDownViewResource(R.layout.spinner_checkedtextview);
        province_spinner.setAdapter(adapter);
        province_spinner.setPrompt("请选择省/市");
        province_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                province = AppConstants.TYPE.stringsAnimal[arg2];
                Log.d("lzx----》",province + "province");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void setCity() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_textview, AppConstants.TYPE.city);
        adapter.setDropDownViewResource(R.layout.spinner_checkedtextview);
        city_spinner.setAdapter(adapter);
        city_spinner.setPrompt("请选择市");
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                city = AppConstants.TYPE.city[arg2];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    boolean[] isCheck = new boolean[AppConstants.TYPE.Report.length];
    Map<Integer, String> map = new HashMap<>();

    private void setReport() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择数据报告类型（多选）");
        /**
         *第一个参数:弹出框的消息集合，一般为字符串集合
         * 第二个参数：默认被选中的，布尔类数组
         * 第三个参数：勾选事件监听
         */
        alertBuilder.setMultiChoiceItems(AppConstants.TYPE.Report, isCheck, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                if (isChecked) {
                    map.put(i, AppConstants.TYPE.Report[i].toString());
                    isCheck[i] = true;
                } else {
                    map.remove(i);
                    isCheck[i] = false;
                }
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String content = "";
                if (!map.isEmpty()) {
                    for (Map.Entry<Integer, String> entry : map.entrySet()) {
                        content += entry.getValue() + ",";
                        list.clear();
                        list.add(entry.getValue().toString());

                        Log.d("lzx-----》", "entry.getValue()" + entry.getValue().toString());
                        if (!TextUtils.isEmpty(content)) {
                            String substring = content.substring(0, content.length() - 1);
                            report_spinner.setText(substring + "");
                        }
                    }
                } else {
                    report_spinner.setText("");
                }
                alertDialog.dismiss();
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (map.isEmpty()) {

                }
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_border_white));
        alertDialog.show();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        alertDialog.getWindow().setAttributes(params);
    }

    private void setShow() {
        if (report_spinner.getText().toString().contains("存栏数")){
            inventory_tv.setVisibility(View.VISIBLE);
        }else {
            inventory_tv.setVisibility(View.GONE);
        }

        if (report_spinner.getText().toString().contains("出栏数")){
            column_volume_tv.setVisibility(View.VISIBLE);
        }else {
            column_volume_tv.setVisibility(View.GONE);
        }
   if (report_spinner.getText().toString().contains("屠宰量")){
       slaughter_tv.setVisibility(View.VISIBLE);
        }else {
       slaughter_tv.setVisibility(View.GONE);
        }
        if (report_spinner.getText().toString().contains("母猪存栏量")){
            sow_slaughter_tv.setVisibility(View.VISIBLE);
        }else {
            sow_slaughter_tv.setVisibility(View.GONE);
        }

        if (report_spinner.getText().toString().contains("在途运输数量")){
            transit_tv.setVisibility(View.VISIBLE);
        }else {
            transit_tv.setVisibility(View.GONE);
        }

        if (report_spinner.getText().toString().contains("一月龄存栏数")){
            one_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            one_month_stocks_tv.setVisibility(View.GONE);
        }
        if (report_spinner.getText().toString().contains("二月龄存栏数")){
            two_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            two_month_stocks_tv.setVisibility(View.GONE);
        }

        if (report_spinner.getText().toString().contains("三月龄存栏数")){
            three_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            three_month_stocks_tv.setVisibility(View.GONE);
        }
        if (report_spinner.getText().toString().contains("四月龄存栏数")){
            four_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            four_month_stocks_tv.setVisibility(View.GONE);
        }
        if (report_spinner.getText().toString().contains("五月龄存栏数")){
            five_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            five_month_stocks_tv.setVisibility(View.GONE);
        }
        if (report_spinner.getText().toString().contains("六月龄存栏数")){
            six_month_stocks_tv.setVisibility(View.VISIBLE);
        }else {
            six_month_stocks_tv.setVisibility(View.GONE);
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
