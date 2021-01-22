package com.example.newapp.activity.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.R;
import com.example.newapp.activity.main.MainActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.UserInfo;
import com.example.newapp.data.city.JsonBean;
import com.example.newapp.sp.UserSp;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.EditTextHintUtil;
import com.example.newapp.utils.GetJsonDataUtil;
import com.example.newapp.utils.GsonUtil;
import com.example.newapp.utils.IDCardInfoExtractor;
import com.example.newapp.utils.IDCardValidator;
import com.example.newapp.utils.IdentitySelectionUtils;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.StrUtil;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.bottomPopupDialog.BottomPopupDialog;
import com.example.newapp.weight.wheelview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * //姓名  性别   男女中性  身份证号   年龄    地区   身份 (14类 + 银行，保险，期货，信托，基金，金融租赁，农资，动保，运输，教育培训，公益组织
 * //养殖专家，科研机构，大学，缴费，其他)    手机号 + 验证码
 * //
 * //50  300  600  3年/1500
 * 注册  之前
 *
 * @author 56454
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText user_name_et;
    private EditText id_card_et;
    private EditText sex_et;
    private EditText age_et;
    private EditText phone_num_et;
    private EditText verification_code_et;
    private EditText address_et;
    private EditText identity_selection_type_et;
    private Button vip_register_btn;
    private TextView mGet_Verification_Code_Tv;
    private IDCardInfoExtractor idCardInfo;//身份证信息
    private ImageView back_iv;
    private IDCardValidator validator = null;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private MyCountDownTimer mCountDownTimer;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_before_vip_register;
    }

    @Override
    protected void initViews() {
        user_name_et = findViewById(R.id.user_name_et);
        id_card_et = findViewById(R.id.id_card_et);
        sex_et = findViewById(R.id.sex_et);
        age_et = findViewById(R.id.age_et);
        phone_num_et = findViewById(R.id.phone_num_et);
        verification_code_et = findViewById(R.id.verification_code_et);
        address_et = findViewById(R.id.address_et);
        identity_selection_type_et = findViewById(R.id.identity_selection_type_et);
        vip_register_btn = findViewById(R.id.vip_register_btn);
        mGet_Verification_Code_Tv = findViewById(R.id.verification_code_tv);
        back_iv = findViewById(R.id.back_iv);

        back_iv.setOnClickListener(this);
        sex_et.setOnClickListener(this);
        address_et.setOnClickListener(this);
        identity_selection_type_et.setOnClickListener(this);
        mGet_Verification_Code_Tv.setOnClickListener(this);
        vip_register_btn.setOnClickListener(this);
        identity_selection_type_et.setText(IdentitySelectionUtils.IdentitySelectionType[IdentitySelectionUtils.positionID]);

        this.mCountDownTimer = new MyCountDownTimer(60 * 1000, 1000, this.mGet_Verification_Code_Tv);
    }

    @Override
    protected void initDatas() {
        id_card_et.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 18) {
                    disposable = Observable.timer(1, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {
                                validator = new IDCardValidator();
                                if (validator.is18IdCard(s.toString())) {
                                    //拿到身份证号码文本
                                    idCardInfo = new IDCardInfoExtractor(s.toString().trim());
                                    LogUtil.d("lzx------》", "s" + idCardInfo.toString().trim());
                                    int age = idCardInfo.getAge();//获取年龄
                                    String gender = idCardInfo.getGender();
                                    age_et.setText(age + "");//设置年龄
                                    sex_et.setText(gender);//设置性别
                                    LogUtil.d("lzx------》", "gender" + gender);
                                    LogUtil.d("lzx------》", "age" + age);

                                } else {
                                    ToastUtil.showToast(RegisterActivity.this, "请输入正确的身份证件号码");
                                }
                            });
                    compositeDisposable.add(disposable);
                }
            }
        });
        EditTextHintUtil.setEditTextHintSize(address_et, getResources().getString(R.string.address_message), 14);
        initJsonData();

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
            case R.id.sex_et://性别
                setGender();
                break;
            case R.id.address_et://地区
                showAreaPickerView();
                break;
            case R.id.identity_selection_type_et://身份类型
                IdentitySelectionUtils.showIdentitySelectionDialog(this, identity_selection_type_et);
                break;
            case R.id.vip_register_btn://注册
                if (checkInfo()){


                    UserInfo userInfo = new UserInfo();
                    userInfo.name = user_name_et.getText().toString();
                    userInfo.idCard = id_card_et.getText().toString();
                    userInfo.sex = sex_et.getText().toString();
                    userInfo.age = age_et.getText().toString();
                    userInfo.phone = phone_num_et.getText().toString();
                    userInfo.address = address_et.getText().toString();
                    userInfo.identity = identity_selection_type_et.getText().toString();
                    UserSp.getInstance().setIsLogin(true);
                    UserSp.getInstance().setUserInfo(userInfo);
                    AppManager.getInstance().finishAllActivity();
                    MainActivity.start(this);
                    ToastUtil.showToast(this,"恭喜您，注册成功~");

                }
                break;
            case R.id.verification_code_tv://判断验证码
                if (TextUtils.isEmpty(phone_num_et.getText().toString().trim())) {
                    ToastUtil.showToast(this, R.string.hint_phone_num);
                } else if (!StrUtil.isMobileNumber(phone_num_et.getText().toString().trim())) {
                    ToastUtil.showToast(this, R.string.please_enter_sure_phone_num);
                } else {
                    if (this.mCountDownTimer != null) {
                        this.mCountDownTimer.start();
                    }
                }
                break;
            default:
        }
    }

    /**
     * 弹出对话框设置性别
     */
    private void setGender() {
        final BottomPopupDialog bottomPopupDialog;
        List<String> bottomDialogContents;//弹出列表的内容
        bottomDialogContents = new ArrayList<>();
        bottomDialogContents.add("男性");
        bottomDialogContents.add("女性");
        bottomDialogContents.add("中性");
        bottomPopupDialog = new BottomPopupDialog(this, bottomDialogContents);
        bottomPopupDialog.showCancelBtn(true);
        bottomPopupDialog.show();
        bottomPopupDialog.setCancelable(true);
        bottomPopupDialog.setOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {//男
                    bottomPopupDialog.dismiss();
                } else if (position == 1) {//女
                    bottomPopupDialog.dismiss();
                } else {//中性
                    bottomPopupDialog.dismiss();
                }
                sex_et.setText(bottomDialogContents.get(position));
            }

        });
    }

    /**
     * 显示地区选择器
     */
    private void showAreaPickerView() {
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(RegisterActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                String tx = opt1tx + opt2tx + opt3tx;
                if (opt1tx.equals(opt2tx)){
                    address_et.setText(opt1tx + ">"+ opt3tx);
                }else {
                    address_et.setText(opt1tx + ">" + opt2tx + ">" + opt3tx);
                }

            }
        }).setTitleSize(16) // 设置标题字体大小
                .setContentTextSize(18)   // 设置滚轮字体大小
                .setOutSideCancelable(true)   // 设置允许点击外面消失
                .setDividerColor(Color.parseColor("#cccccc")) // 设置分割线的颜色
                .setTextColorCenter(Color.parseColor("#333333")) // 设置分割线之间的文字的颜色
                .setTextColorOut(Color.parseColor("#cccccc"))    // 设置分割线以外文字的颜色
                .isCenterLabel(false) // 是否只显示中间选中项的label文字，false则每项item全部都带有label
                .setSubmitColor(Color.parseColor("#4393f9"))  // 设置“确定”的字体颜色
                .setCancelColor(Color.parseColor("#333333"))  // 设置“取消”的字体颜色
                .setSubCalSize(16)   // 设置“确定”和“取消”的字体大小
                .setTitleBgColor(Color.parseColor("#ffffff"));// 设置标题背景色
//                .setSelectOptions(selectedOptionArray[0], selectedOptionArray[1]);  // 设置默认选中的item
        OptionsPickerView areaPickerView = new OptionsPickerView(builder);
        areaPickerView.setPicker(options1Items, options2Items, options3Items);    // 三级级选择器
        areaPickerView.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<JsonBean>>() {
        }.getType();
        ArrayList<JsonBean> jsonBean = gson.fromJson(JsonData, type);
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
    }
    private boolean checkInfo() {
        if (StrUtil.isEmpty(user_name_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入姓名");
            return false;
        }
        if (StrUtil.isEmpty(id_card_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入身份证件号码");
            return false;
        }
        if (StrUtil.isEmpty(sex_et.getText().toString())) {
            ToastUtil.showToast(this, "请选择性别");
            return false;
        }
        if (StrUtil.isEmpty(phone_num_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入手机号");
            return false;
        }
        if (StrUtil.isEmpty(verification_code_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入验证码");
            return false;
        }
        if (StrUtil.isEmpty(address_et.getText().toString())) {
            ToastUtil.showToast(this, "请选择地址");
            return false;
        }
        if (StrUtil.isEmpty(identity_selection_type_et.getText().toString())) {
            ToastUtil.showToast(this, "请进行身份选择");
            return false;
        }
        return true;
    }
    /**
     * 倒计时器
     */
    private class MyCountDownTimer extends CountDownTimer {

        private TextView mTextView;

        /**
         * 构造方法
         *
         * @param millisInFuture    倒计时的总时间, 单位ms
         * @param countDownInterval 倒计时的间隔时间, 单位ms
         * @param textView          倒计时的view
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView textView) {
            super(millisInFuture, countDownInterval);
            this.mTextView = textView;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            @SuppressLint("StringFormatMatches")
            String content = String.format(getString(R.string.sms_time), (millisUntilFinished / 1000));
            this.mTextView.setEnabled(false);
            this.mTextView.setText(content);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onFinish() {
            this.mTextView.setEnabled(true);
            this.mTextView.setText(getString(R.string.retry_for_valid_code));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
