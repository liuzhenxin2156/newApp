package com.example.newapp.activity.financial_services.Insurance.transport.transport_insured;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslError;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;


import com.example.newapp.R;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.DateUtil;
import com.example.newapp.utils.DialogShowUtils;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.TimeChooseUtils;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.wheelview.listener.lib.CustomListener;
import com.example.newapp.weight.wheelview.view.TimePickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * 生猪短途运输险保险
 *
 * @author 56454
 */
public class TransportInsuredActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_iv;
    private EditText quarantine_num_et;
    private EditText order_num_et;
    private EditText start_date_et;//起保日期
    private EditText start_time_et;//起保时间
    private EditText insured_name_et;
    private EditText insured_card_type_et;//投保人证件类型
    private EditText insured_id_card_et;
    private EditText insured_id_card_effective_et;
    private EditText insured_address_et;
    private EditText insured_contact_person_et;
    private EditText insured_phone_et;
    private EditText company_phone_et;
    private EditText company_founded_time_et;
    private EditText be_insured_name_et;
    private EditText be_insured_card_type_et;
    private EditText be_insured_id_card_et;
    private EditText be_insured_id_card_effective_et;
    private EditText be_insured_address_et;
    private EditText be_insured_contact_person_et;
    private EditText be_insured_phone_et;
    private EditText carrier_et;
    private EditText insured_animals_et;
    private EditText insured_amount_et;
    private EditText departure_place_et;
    private EditText destination_et;
    private EditText license_plate_number_et;
    private EditText ear_tags_list_et;
    private EditText be_company_phone_et;
    private EditText be_company_founded_time_et;
    private LinearLayout company_phone_ll;
    private LinearLayout company_founded_time_ll;
    private LinearLayout be_company_phone_ll;
    private LinearLayout be_company_founded_time_ll;

    private CheckBox personal_cb, legal_person_cb;
    private CheckBox be_personal_cb, be_legal_person_cb;
    private Button commit_bt;
    private Button insurance_notice_btn;
    private Button insurance_terms_btn;
    private Button customer_service_btn;
    private Button claim_service_btn;
    private TextView terms_tv;//同意须知


    private TimePickerView mCustomTimeEnd;
    private TimePickerView mCustomTime;

    private long mStartTime = 0;                 // 开始时间戳
    private long mTime = 0;

    public static void start(Context context) {
        Intent intent = new Intent(context, TransportInsuredActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transport_insured;
    }

    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        quarantine_num_et = findViewById(R.id.quarantine_num_et);

        order_num_et = findViewById(R.id.order_num_et);
        start_date_et = findViewById(R.id.start_date_et);

        start_time_et = findViewById(R.id.start_time_et);
        insured_name_et = findViewById(R.id.insured_name_et);
        insured_card_type_et = findViewById(R.id.insured_card_type_et);
        insured_id_card_et = findViewById(R.id.insured_id_card_et);
        insured_id_card_effective_et = findViewById(R.id.insured_id_card_effective_et);
        insured_address_et = findViewById(R.id.insured_address_et);
        insured_contact_person_et = findViewById(R.id.insured_contact_person_et);
        insured_phone_et = findViewById(R.id.insured_phone_et);
        company_phone_et = findViewById(R.id.company_phone_et);
        company_founded_time_et = findViewById(R.id.company_founded_time_et);
        be_insured_name_et = findViewById(R.id.be_insured_name_et);
        be_insured_card_type_et = findViewById(R.id.be_insured_card_type_et);
        be_insured_id_card_et = findViewById(R.id.be_insured_id_card_et);
        be_insured_id_card_effective_et = findViewById(R.id.be_insured_id_card_effective_et);
        be_insured_address_et = findViewById(R.id.be_insured_address_et);
        be_insured_contact_person_et = findViewById(R.id.be_insured_address_et);
        be_insured_phone_et = findViewById(R.id.be_insured_phone_et);
        carrier_et = findViewById(R.id.carrier_et);
        insured_animals_et = findViewById(R.id.insured_animals_et);
        insured_amount_et = findViewById(R.id.insured_amount_et);
        be_company_phone_et = findViewById(R.id.be_company_phone_et);
        be_company_founded_time_et = findViewById(R.id.be_company_founded_time_et);
        company_phone_ll = findViewById(R.id.company_phone_ll);
        company_founded_time_ll = findViewById(R.id.company_founded_time_ll);
        be_company_phone_ll = findViewById(R.id.be_company_phone_ll);
        be_company_founded_time_ll = findViewById(R.id.be_company_founded_time_ll);

        departure_place_et = findViewById(R.id.departure_place_et);
        destination_et = findViewById(R.id.destination_et);
        license_plate_number_et = findViewById(R.id.license_plate_number_et);
        ear_tags_list_et = findViewById(R.id.ear_tags_list_et);

        personal_cb = findViewById(R.id.personal_cb);
        legal_person_cb = findViewById(R.id.legal_person_cb);
        be_personal_cb = findViewById(R.id.be_personal_cb);
        be_legal_person_cb = findViewById(R.id.be_legal_person_cb);

        insurance_notice_btn = findViewById(R.id.insurance_notice_btn);
        insurance_terms_btn = findViewById(R.id.insurance_terms_btn);
        customer_service_btn = findViewById(R.id.customer_service_btn);
        claim_service_btn = findViewById(R.id.claim_service_btn);
        commit_bt = findViewById(R.id.commit_btn);
        terms_tv = findViewById(R.id.terms_tv);

        insurance_notice_btn.setOnClickListener(this);
        insurance_terms_btn.setOnClickListener(this);
        customer_service_btn.setOnClickListener(this);
        claim_service_btn.setOnClickListener(this);
        commit_bt.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        insured_card_type_et.setOnClickListener(this);
        start_date_et.setOnClickListener(this);
        start_time_et.setOnClickListener(this);
        insured_id_card_effective_et.setOnClickListener(this);
        be_insured_id_card_effective_et.setOnClickListener(this);
        company_founded_time_et.setOnClickListener(this);
        be_company_founded_time_et.setOnClickListener(this);
        setCheckListener();//监听


        insured_card_type_et.setText(DialogShowUtils.stringsInsuredCardType[DialogShowUtils.positionInsuredCardType]);
        initStartDataPicker();//起保日期
        TimeChooseUtils.endDataPicker(this, insured_id_card_effective_et);
        TimeChooseUtils.endIdCardDataPicker(this, be_insured_id_card_effective_et);
        TimeChooseUtils.comPanyDataPicker(this, company_founded_time_et);
        TimeChooseUtils.beComPanyDataPicker(this, be_company_founded_time_et);
        setHigthOnClick();

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

            case R.id.start_date_et://起保日期
                Calendar startSelectedDate = Calendar.getInstance();
                startSelectedDate.setTimeInMillis(mStartTime);//设置选中的默认时间
                mCustomTimeEnd.setDate(startSelectedDate);
                mCustomTimeEnd.show();
                break;

            case R.id.start_time_et://起保时间
                initStartTimePicker();
                mCustomTime.show();
                break;

            case R.id.insured_card_type_et://投保人证件类型
                DialogShowUtils.showChooseInsuredCardTypeDialog(this, insured_card_type_et);
                break;

            case R.id.insured_id_card_effective_et://证件有效期
                Calendar startSelectedDate1 = Calendar.getInstance();
                startSelectedDate1.setTimeInMillis(TimeChooseUtils.mEndDataTime);//设置选中的默认时间
                TimeChooseUtils.mCardTimeEnd.setDate(startSelectedDate1);
                TimeChooseUtils.mCardTimeEnd.show();
                break;

            case R.id.be_insured_id_card_effective_et://被保险人证件有效期

                Calendar startSelectedDate2 = Calendar.getInstance();
                startSelectedDate2.setTimeInMillis(TimeChooseUtils.mBeEndDataTime);//设置选中的默认时间
                TimeChooseUtils.mBeCardTimeEnd.setDate(startSelectedDate2);
                TimeChooseUtils.mBeCardTimeEnd.show();
                break;

            case R.id.company_founded_time_et://企业创办时间

                Calendar startSelectedDate3 = Calendar.getInstance();
                startSelectedDate3.setTimeInMillis(TimeChooseUtils.mComPanyDataTime);//设置选中的默认时间
                TimeChooseUtils.mComPanyTime.setDate(startSelectedDate3);
                TimeChooseUtils.mComPanyTime.show();
                break;

            case R.id.be_company_founded_time_et://被保险企业创办时间
                Calendar startSelectedDate4 = Calendar.getInstance();
                startSelectedDate4.setTimeInMillis(TimeChooseUtils.mBeComPanyDataTime);//设置选中的默认时间
                TimeChooseUtils.mBeComPanyTime.setDate(startSelectedDate4);
                TimeChooseUtils.mBeComPanyTime.show();
                break;


            case R.id.insurance_notice_btn://投保须知
                showWebViewDialog(1);
                break;
            case R.id.insurance_terms_btn:
                showWebViewDialog(2);
                break;
            case R.id.customer_service_btn:
                showWebViewDialog(3);
                break;
            case R.id.claim_service_btn:
                showWebViewDialog(4);
                break;
            case R.id.commit_btn:

                break;
            default:
        }
    }


    /**
     * 投保须知
     */
    private void showWebViewDialog(int type) {
        View view = getLayoutInflater().inflate(R.layout.webview_layout, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(true);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WebView webView = view.findViewById(R.id.webview);
        TextView title = (TextView) view.findViewById(R.id.title_tv);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(true); //垂直不显示

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 忽略SSL验证AS
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);


            }
        });
        switch (type) {
            case 1:
                title.setText("投保须知");
                webView.loadUrl("file:///android_asset/html/toubaoxuzhi.html");
                break;
            case 2:
                title.setText("保险条款");
                webView.loadUrl("file:///android_asset/html/tiaokuan.html");
                break;
            case 3:
                title.setText("客服服务");
                webView.loadUrl("file:///android_asset/html/kehufuwu.html");
                break;
            case 4:
                title.setText("理赔服务");
                webView.loadUrl("file:///android_asset/html/lipeifuwu.html");
                break;
            case 5:
                title.setText("特别约定");
                webView.loadUrl("file:///android_asset/html/tebieyueding.html");
                break;
            case 6:
                title.setText("投保声明");
                webView.loadUrl("file:///android_asset/html/toubaoshengming.html");
                break;
            default:
        }
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        if (type == 3 || type == 4 || type==5) {
            params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
            exitDialog.getWindow().setAttributes(params);
        } else {
            params.height = this.getWindowManager().getDefaultDisplay().getHeight();
            params.height = (int) (ScreenUtils.getScreenHeight(this) * 0.75);
            params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
            exitDialog.getWindow().setAttributes(params);
        }
    }


    /**
     * 设置高亮并点击
     */
    private void setHigthOnClick() {
        //拼接字符串
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder("本人已阅读并同意");
        //《保险条款》
        SpannableString span = new SpannableString("《保险条款》");
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showWebViewDialog(2);
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.C4)),
                0,
                span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spanBuilder.append(span);
        spanBuilder.append("、");

        //《投保须知》
        SpannableString notice = new SpannableString("《投保须知》");
        notice.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showWebViewDialog(1);
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        notice.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.C4)),
                0,
                span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spanBuilder.append(notice);
        spanBuilder.append("、");
        //《特别约定》
        SpannableString agreement = new SpannableString("《特别约定》");
        agreement.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showWebViewDialog(5);
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        agreement.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.C4)),
                0,
                span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spanBuilder.append(agreement);
        spanBuilder.append("、");
        //《投保声明》
        SpannableString statement = new SpannableString("《投保声明》");
        statement.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showWebViewDialog(6);
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        statement.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.C4)),
                0,
                span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spanBuilder.append(statement);
        spanBuilder.append(",并承诺实际缴费人为本人操作。");
        // 赋值给TextView
        terms_tv.setMovementMethod( LinkMovementMethod.getInstance());
        terms_tv.setText(spanBuilder);
        //设置高亮颜色透明，因为点击会变色
        terms_tv.setHighlightColor( ContextCompat.getColor(this, R.color.transparent));
    }

    /**
     * 开始日期
     */
    private void initStartDataPicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        //系统当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        Calendar startDate = Calendar.getInstance();
        startDate.set(year, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year + 10, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(mStartTime);
        //时间选择器 ，自定义布局
        mCustomTimeEnd = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mStartTime = date.getTime();
                start_date_et.setText(DateUtil.dateToString(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCustomTimeEnd.returnData();
                        mCustomTimeEnd.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCustomTimeEnd.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(getResources().getColor(R.color.primary_divider)).build();
    }

    /**
     * 开始时间
     */
    private void initStartTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        //系统当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        Calendar startDate = Calendar.getInstance();
        startDate.set(year - 10, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year + 10, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(0);
        //时间选择器 ，自定义布局
        mCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String s = date.toString();
                LogUtil.d("lzx----》", s);
                start_time_et.setText(DateUtil.getTimes(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCustomTime.returnData();
                        mCustomTime.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCustomTime.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{false, false, false, true, true, true}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(getResources().getColor(R.color.primary_divider)).build();
    }

    /**
     * checbox 监听
     */
    private void setCheckListener() {
        if (personal_cb.isChecked()) {
            personal_cb.setClickable(false);
        }
        if (be_personal_cb.isChecked()) {
            be_personal_cb.setClickable(false);
        }
        personal_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (b == personal_cb.isChecked()) {
                        personal_cb.setClickable(false);
                    }
                    personal_cb.setClickable(false);
                    legal_person_cb.setChecked(false);
                    company_phone_ll.setVisibility(View.GONE);
                    company_founded_time_ll.setVisibility(View.GONE);
                    legal_person_cb.setClickable(true);
                }
                personal_cb.setChecked(b);
            }
        });

        legal_person_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    legal_person_cb.setClickable(false);
                    personal_cb.setChecked(false);
                    company_phone_ll.setVisibility(View.VISIBLE);
                    company_founded_time_ll.setVisibility(View.VISIBLE);
                    personal_cb.setClickable(true);
                }
            }
        });

        be_personal_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    be_personal_cb.setClickable(false);
                    be_legal_person_cb.setChecked(false);
                    be_company_phone_ll.setVisibility(View.GONE);
                    be_company_founded_time_ll.setVisibility(View.GONE);
                    be_legal_person_cb.setClickable(true);
                }
                be_personal_cb.setChecked(b);
            }
        });

        be_legal_person_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    be_legal_person_cb.setClickable(false);
                    be_personal_cb.setChecked(false);
                    be_company_phone_ll.setVisibility(View.VISIBLE);
                    be_company_founded_time_ll.setVisibility(View.VISIBLE);
                    be_personal_cb.setClickable(true);
                }
            }
        });
    }
}
