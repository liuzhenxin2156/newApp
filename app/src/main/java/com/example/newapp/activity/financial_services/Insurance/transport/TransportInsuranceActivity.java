package com.example.newapp.activity.financial_services.Insurance.transport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.Insurance.details.FarmingInsuranceDetailsActivity;
import com.example.newapp.activity.financial_services.Insurance.details.TransportInsuranceDetailsActivity;
import com.example.newapp.activity.financial_services.Insurance.transport.transport_insured.TransportInsuredActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.KeyBoardUtil;
import com.example.newapp.utils.PhoneUtil;
import com.example.newapp.utils.ScreenUtils;

/**
 * 运输险
 *
 * @author 56454
 */
public class TransportInsuranceActivity extends BaseActivity implements View.OnClickListener {
    private int typeID;//1 为 主页点击，2为金融服务进入
    private TextView back_level_tv;
    private ImageView arrow_kill;
    private TextView mBackTv;
    private RelativeLayout toolbar_ll;
    private PopupWindow popupWindow;
    private LinearLayout sun_insurance_ll;
    private Button sun_insurance_btn;
    private LinearLayout quanlian_insurance_ll;
    private Button quanlian_insurance_btn;
    private Button  detalis_btn;

    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context, int type) {
        Intent intent = new Intent(context, TransportInsuranceActivity.class);
        intent.putExtra("id", type);
        context.startActivity(intent);
    }

    /**
     * 获取参数
     */
    private void getArguments() {
        this.typeID = this.getIntent().getIntExtra("id", -1);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transport_insurance;
    }

    @Override
    protected void initViews() {
        getArguments();
        arrow_kill = findViewById(R.id.arrow_kill);
        mBackTv = findViewById(R.id.back_tv);
        mBackTv.setOnClickListener(this);
        back_level_tv = findViewById(R.id.back_level_tv);
        back_level_tv.setOnClickListener(this);
        toolbar_ll = findViewById(R.id.toolbar_ll);
        if (typeID != 1) {
            arrow_kill.setVisibility(View.GONE);
            back_level_tv.setVisibility(View.GONE);
        }
        sun_insurance_ll = findViewById(R.id.sun_insurance_ll);
        sun_insurance_btn = findViewById(R.id.sun_insurance_btn);
        quanlian_insurance_ll = findViewById(R.id.quanlian_insurance_ll);
        quanlian_insurance_btn = findViewById(R.id.quanlian_insurance_btn);
        detalis_btn = findViewById(R.id.detalis_btn);

        sun_insurance_ll.setOnClickListener(this);
        sun_insurance_btn.setOnClickListener(this);
        quanlian_insurance_ll.setOnClickListener(this);
        quanlian_insurance_btn.setOnClickListener(this);
        detalis_btn.setOnClickListener(this);
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
            case R.id.back_level_tv:
                this.finish();
                break;
            case R.id.back_tv:
                if (typeID == 1) {
                    finish();
                    AppManager.getInstance().finishActivity(InsuranceActivity.class);
                } else {
                    showMorePopWindow();
                }
                break;
            case R.id.sun_insurance_ll://运输险简介
                TransportInsuranceDetailsActivity.start(this, 1);
                break;
            case R.id.detalis_btn://运输险简介
                TransportInsuranceDetailsActivity.start(this, 1);
                break;
            case R.id.sun_insurance_btn://运输险投保
                showInsuredDialog();
                break;
            default:
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
        return_tv.setText("金融服务");
        return_one_tv.setText("保险服务");
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
                PhoneUtil.setBackgroundAlpha(TransportInsuranceActivity.this, 1.0f);
            }
        });

        main_tv.setOnClickListener(new View.OnClickListener() {  // 返回主页
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
                AppManager.getInstance().finishActivity(FinancialServicesActivity.class);
                AppManager.getInstance().finishActivity(InsuranceActivity.class);

            }
        });
        return_tv.setOnClickListener(new View.OnClickListener() {    // 返回上上级
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                finish();
                AppManager.getInstance().finishActivity(InsuranceActivity.class);
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

    private void showInsuredDialog() {
        View view = getLayoutInflater().inflate(R.layout.quarantine_enter_bg, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(this,R.style.customerDialog).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(true);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button search_btn = view.findViewById(R.id.search_btn);
        EditText quarantine_num_et = view.findViewById(R.id.quarantine_num_et);
        quarantine_num_et.setFocusable(true);
        quarantine_num_et.setFocusableInTouchMode(true);
        quarantine_num_et.requestFocus();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBoardUtil.closeKeyboard(TransportInsuranceActivity.this);
                exitDialog.dismiss();
                TransportInsuredActivity.start(TransportInsuranceActivity.this);
            }
        });
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        exitDialog.getWindow().setAttributes(params);
    }

}
