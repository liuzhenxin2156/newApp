package com.example.newapp.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.weight.wheelview.listener.lib.CustomListener;
import com.example.newapp.weight.wheelview.view.TimePickerView;

import java.util.Calendar;
import java.util.Date;

public class TimeChooseUtils {

    public static TimePickerView mCardTimeEnd,mBeCardTimeEnd;
    public  static  TimePickerView  mComPanyTime,mBeComPanyTime;
    public static long mEndDataTime = 0;
    public static  long mBeEndDataTime = 0;
    public static  long mComPanyDataTime = 0;
    public static  long mBeComPanyDataTime = 0;

    public static  void endDataPicker (Context context, EditText editText) {
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
        endDate.set(2100, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(mEndDataTime);
        //时间选择器 ，自定义布局
        mCardTimeEnd = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mEndDataTime = date.getTime();
                editText.setText(DateUtil.dateToString(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCardTimeEnd.returnData();
                        mCardTimeEnd.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCardTimeEnd.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(context.getResources().getColor(R.color.primary_divider)).build();
    }

    public static  void endIdCardDataPicker (Context context, EditText editText) {
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
        endDate.set(2100, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(mBeEndDataTime);
        //时间选择器 ，自定义布局
        mBeCardTimeEnd = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mBeEndDataTime = date.getTime();
                editText.setText(DateUtil.dateToString(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBeCardTimeEnd.returnData();
                        mBeCardTimeEnd.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBeCardTimeEnd.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(context.getResources().getColor(R.color.primary_divider)).build();
    }

    /**
     * 企业创办时间
     * @param context
     * @param editText
     */
    public static  void comPanyDataPicker (Context context, EditText editText) {
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
        startDate.set(year-10, 0, 1);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2100, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(mComPanyDataTime);
        //时间选择器 ，自定义布局
        mComPanyTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mComPanyDataTime = date.getTime();
                editText.setText(DateUtil.dateToString(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mComPanyTime.returnData();
                        mComPanyTime.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mComPanyTime.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(context.getResources().getColor(R.color.primary_divider)).build();
    }


    /**
     * 被报人企业创办时间
     * @param context
     * @param editText
     */
    public static  void beComPanyDataPicker (Context context, EditText editText) {
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
        startDate.set(year-10, 0, 1);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2100, 11, 31);
        Calendar selectedDate = Calendar.getInstance();
        //设置选中的默认时间
        selectedDate.setTimeInMillis(mBeComPanyDataTime);
        //时间选择器 ，自定义布局
        mBeComPanyTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mBeComPanyDataTime = date.getTime();
                editText.setText(DateUtil.dateToString(date));
            }
        }).setDate(selectedDate).setRangDate(startDate, endDate).setLayoutRes(R.layout.custom_time, new CustomListener() {

            @Override
            public void customLayout(View v) {
                TextView tvSubmit = (TextView) v.findViewById(R.id.submit_tv);
                TextView ivCancel = (TextView) v.findViewById(R.id.cancel_tv);

                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBeComPanyTime.returnData();
                        mBeComPanyTime.dismiss();

                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBeComPanyTime.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setLabel("年", "月", "日", "", "", "").isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(context.getResources().getColor(R.color.primary_divider)).build();
    }

}
