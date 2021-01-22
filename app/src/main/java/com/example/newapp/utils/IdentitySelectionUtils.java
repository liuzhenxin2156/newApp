package com.example.newapp.utils;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.newapp.R;
//银行，保险，期货，信托，基金，金融租赁，农资，动保，运输，教育培训，公益组织
// * //养殖专家，科研机构，大学，缴费，其他
public class IdentitySelectionUtils {
    public static String[] IdentitySelectionType = {"生猪运输车辆", "规模场", "屠宰场","贩运户","无害化处理场","散养户","兽医实验室","动物隔离场","动物诊疗机构"
    ,"孵化场","动物交易市场","兽药生产企业","产品经营户","兽药经营企业","银行","保险","期货","信托","基金","金融租赁","农资",
            "动保","运输","教育培训","公益组织","养殖专家","科研机构","大学","其他"};
    public static int positionID = 0;

    /**
     * 贫困户标识
     */
    public static void showIdentitySelectionDialog(Activity context, EditText editText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("身份选择");
        builder.setSingleChoiceItems(IdentitySelectionType, positionID, (dialog, which) -> {
            editText.setText(IdentitySelectionType[which]);
            TimerDelay.setDelay(dialog);
            positionID = which;
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_border_white));
        alertDialog.show();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.height = context.getWindowManager().getDefaultDisplay().getHeight();
        params.height = (int) (ScreenUtils.getScreenHeight(context) * 0.65);
        params.width = (int) (ScreenUtils.getScreenWidth(context) * 0.85);
        alertDialog.getWindow().setAttributes(params);
    }
}
