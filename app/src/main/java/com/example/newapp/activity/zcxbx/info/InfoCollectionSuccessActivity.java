package com.example.newapp.activity.zcxbx.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.SuccessInfo;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.GlideUtil;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.StrUtil;
import com.example.newapp.utils.recyclerview.DividerItemDecoration;

import java.io.File;
import java.util.List;

/**
 * @ProjectName : NewApp
 * @Author : 投保档案
 * @Time : 2021/4/7 18:04
 * @Description :
 */
public class InfoCollectionSuccessActivity extends BaseActivity {

    private ImageView mBackIv;
    private TextView pig_name_tv;
    private TextView business_license_no_tv;
    private TextView address_tv;
    private TextView legal_person_name_tv;
    private TextView id_card_name_tv;
    private TextView phone_num_tv;
    private TextView ear_tag_tv;
    private TextView  day_age_tv;
    private AppCompatImageView positive_photo_id_card_iv;
    private  AppCompatImageView  opposite_photo_id_card_iv;
    private  AppCompatImageView bank_iv;


    private SuccessInfo successInfo;
    private RecyclerView recyclerView;
    private MeansPhotoAdapter meansPhotoAdapter;


    public static void start(Context context, SuccessInfo successInfo) {
        Intent intent = new Intent(context, InfoCollectionSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("successInfo", successInfo);
        intent.putExtra("data", bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_info_collection_success;
    }

    /**
     * 获取值
     */
    private void getArgument() {
        Bundle bundle = getIntent().getBundleExtra("data");
        this.successInfo = (SuccessInfo) bundle.getSerializable("successInfo");
        LogUtil.d("lzx------>",successInfo.toString());
    }

    @Override
    protected void initViews() {
        getArgument();
        mBackIv = findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(v -> finish());
        pig_name_tv  =findViewById(R.id.pig_name_tv);


        business_license_no_tv  =findViewById(R.id.business_license_no_tv);
        address_tv  =findViewById(R.id.address_tv);
        legal_person_name_tv  =findViewById(R.id.legal_person_name_tv);
        id_card_name_tv  =findViewById(R.id.id_card_name_tv);
        phone_num_tv  =findViewById(R.id.phone_num_tv);
        ear_tag_tv  =findViewById(R.id.ear_tag_tv);
        day_age_tv  =findViewById(R.id.day_age_tv);
        positive_photo_id_card_iv  =findViewById(R.id.positive_photo_id_card_iv);
        opposite_photo_id_card_iv  =findViewById(R.id.opposite_photo_id_card_iv);
        recyclerView =findViewById(R.id.recyclerView);
        bank_iv  =findViewById(R.id.bank_iv);
         GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
         meansPhotoAdapter = new MeansPhotoAdapter(R.layout.means_photo_item, this);
        recyclerView.setAdapter(meansPhotoAdapter);

    }

    @Override
    protected void initDatas() {
            if (successInfo!=null){
                pig_name_tv.setText(successInfo.PigName);
                business_license_no_tv.setText(successInfo.LinecsNum);
                address_tv.setText(successInfo.Address);
                legal_person_name_tv.setText(successInfo.PersonName);
                id_card_name_tv.setText(successInfo.IDCard);
                phone_num_tv.setText(successInfo.Phone);
                ear_tag_tv.setText(successInfo.EarTags);
                day_age_tv.setText(StrUtil.isEmpty(successInfo.DayAge) ? "" : successInfo.DayAge);

                File fileByPath = FileUtil.getFileByPath(successInfo.IDCardPhotoPositive);
                LogUtil.d("lzx",fileByPath.getAbsolutePath() + "fileByPath");
                GlideUtil.loadImage( fileByPath,this,positive_photo_id_card_iv,R.drawable.ic_default);//加载URL图片


                File fileByPath1 = FileUtil.getFileByPath(successInfo.IDCardPhotoReverse);
                LogUtil.d("lzx",fileByPath1.getAbsolutePath() + "fileByPath");
                GlideUtil.loadImage( fileByPath1,this,opposite_photo_id_card_iv,R.drawable.ic_default);//加载URL图片


                File fileByPath2 = FileUtil.getFileByPath(successInfo.BankPhoto);
                LogUtil.d("lzx",fileByPath2.getAbsolutePath() + "fileByPath");
                GlideUtil.loadImage( fileByPath2,this,bank_iv,R.drawable.ic_default);//加载URL图片

                List<String> meansPhotos = successInfo.MeansPhotos;
                meansPhotoAdapter.refreshDataList(meansPhotos);

            }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
