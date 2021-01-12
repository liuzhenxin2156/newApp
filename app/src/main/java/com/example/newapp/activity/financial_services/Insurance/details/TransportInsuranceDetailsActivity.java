package com.example.newapp.activity.financial_services.Insurance.details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.culling.info.CullingInsuranceInfoActivity;
import com.example.newapp.activity.financial_services.Insurance.transport.TransportInsuranceActivity;
import com.example.newapp.activity.financial_services.Insurance.transport.transport_insured.TransportInsuredActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.KeyBoardUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.ToastUtil;

/**
 * 运输险保险介绍
 *
 * @author 56454
 */
public class TransportInsuranceDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;
    private int typeID;
    private Button insured_now_btn;


    /**
     * 启动activity
     *
     * @param context
     */
    public static void start(Context context, int type) {
        Intent intent = new Intent(context, TransportInsuranceDetailsActivity.class);
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
        return R.layout.activity_transport_insurance_details;
    }


    @Override
    protected void initViews() {
        getArguments();
        mBackIv = findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(this);
        insured_now_btn  = findViewById(R.id.insured_now_btn);
        insured_now_btn.setOnClickListener(this);
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
            case R.id.back_iv:
                finish();
                break;
            case R.id.insured_now_btn:
                if (typeID==1){
                    showInsuredDialog();
                }else {
                    ToastUtil.showToast(this,"暂无养殖险");
                }
                break;
            default:
        }
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
                KeyBoardUtil.closeKeyboard(TransportInsuranceDetailsActivity.this);
                TransportInsuredActivity.start(TransportInsuranceDetailsActivity.this);
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        exitDialog.getWindow().setAttributes(params);
    }
}
