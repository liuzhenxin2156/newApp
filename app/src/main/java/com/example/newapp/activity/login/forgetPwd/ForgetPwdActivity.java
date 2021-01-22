package com.example.newapp.activity.login.forgetPwd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.activity.login.phone_verification_code.PhoneVerificationCodeActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.EditTextHintUtil;
import com.example.newapp.utils.InputUtil;
import com.example.newapp.utils.StrUtil;
import com.example.newapp.utils.ToastUtil;

/**
 * @author 56454
 * 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {
    private ImageView mBackIv;
    private EditText mPhoneNumEt;
    private Button mNextBtn;



    private String realMobile;
    private String authCode = "";



    public static void start(Context context) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initViews() {
        mBackIv = findViewById(R.id.back_iv);
        mPhoneNumEt = findViewById(R.id.account_cet);
        mNextBtn = findViewById(R.id.next_btn);
        EditTextHintUtil.setEditTextHintSize(mPhoneNumEt, getResources().getString(R.string.please_enter_phone_num), 16);

        mBackIv.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

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
            case R.id.next_btn:
                if (checkInfo()){
                    PhoneVerificationCodeActivity.start(this,mPhoneNumEt.getText().toString().trim(),2);
                }
                break;
            default:
        }
    }
    /**
     * 进行账号检测
     */
    private boolean checkInfo() {
        realMobile = mPhoneNumEt.getText().toString().trim();
        if (StrUtil.isEmpty(this.realMobile)) {
            ToastUtil.showToast(this, 0, "请输入手机号");
            return false;
        }
        if (!InputUtil.isMobileLegal(this.realMobile)) {
            ToastUtil.showToast(this, 0, "请输入正确的手机号码");
            return false;
        }

        return true;
    }
}
