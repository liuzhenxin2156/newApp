package com.example.newapp.activity.financial_services.Insurance.culling.info;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.activity.financial_services.Insurance.culling.CullingInsuranceActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.AppUtil;
import com.example.newapp.utils.BitmapUtils;
import com.example.newapp.utils.DialogShowUtils;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.ImageUtil;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.TimerDelay;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.FullyGridLayoutManager;
import com.example.newapp.weight.SignatureView;
import com.example.newapp.weight.bottomPopupDialog.BottomPopupDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 扑杀险信息
 *
 * @author 56454
 */
public class CullingInsuranceInfoActivity extends BaseActivity {
    private static final String TAG = "CullingInsuranceInfoActivity----->";
    public static final int REQUEST_CODE_FROM_GALLERY = 301;
    public static final int REQUEST_CODE_FROM_CAMERA = 302;

    private SignatureView signatureView;
    private Button add_beneficiary_btn;//+受益人
    private boolean add_beneficiary_btnFlag = false;
    private LinearLayout beneficiary_ll;//收益人信息
    private Button be_insured_people_btn;//+添加被保险人
    private boolean be_insured_people_btnFlag = false;
    private LinearLayout be_insured_ll;//被保险人信息
    private ImageView back_iv;
    private EditText insured_name_et;//投保人名称
    private EditText insured_card_type_et;//投保人证件类型 *
    private EditText insured_id_card_et;//投保人证件号码 *
    private EditText insured_phone_et;//投保人电话号码 *
    private EditText insured_address_et;//投保人地址 *
    private EditText insured_underwriting_address_et;//保单承保标的地址 *
    private EditText insured_choose_address_et;//选择所在村落 *
    private EditText insured_location_et;//选择精准定位 *

    private EditText beneficiary_name_et;//受益人名称 *
    private EditText beneficiary_card_type_et;//受益人证件类型 *
    private EditText beneficiary_id_card_et;//受益人证件号码 *
    private EditText beneficiary_phone_et;//受益人电话号码 *
    private EditText beneficiary_address_et;//受益人地址（营业执照地址） *

    /**
     * 被保险人信息
     */
    private EditText be_insured_name_et;//被保险人名称 *
    private EditText be_insured_type_et;//被保险人类型（规模场） *
    private EditText be_insured_address_et;//被保险人地址（营业执照地址） *
    private EditText be_insured_card_type_et;//被保险人证件类型 *
    private EditText be_insured_id_card_et;//被保险人证件号码 *
    private EditText be_insured_phone_et;//被保险人电话号码 *

    /**
     * 农户养殖信息
     */
    private EditText farm_poor_type_et;//贫困户标识 *
    private EditText scale_name_et;//规模场名称 *
    private EditText bank_name_et;//银行名称 *
    private EditText bank_account_et;//银行账号/一卡通号码 *
    private EditText pig_num_et;//生猪数量 *
    private LinearLayout premium_short_ll;//短
    private LinearLayout premium_long_ll;//长

    private AppCompatImageView id_card_iv;//上传 身份证正面
    private Button id_card_iv_bt;

    private AppCompatImageView other_id_card_iv;//上传 身份证反面
    private Button other_id_card_iv_bt;

    private AppCompatImageView id_bank_card_iv;//上传 银行卡
    private Button id_bank_card_bt;

    private AppCompatImageView animal_epidemic_iv;//上传 动物防疫合格证
    private Button animal_epidemic_btn;
    private RecyclerView recyclerView;

    private Button clear_btn;//重置签名
    private Button notice_btn;//须知

    private TextView  terms_tv;
    private int TYPE = 1;
    private File mCameraFileIdCard; // 身份证文件
    private File mCameraFileIdCardOther;//身份证反面
    private File mCameraFileBank;//银行卡
    private File mCameraFileAnimalEpidemic;//车辆运营


    private String imagePathIDCard;
    private String imagePathIDCardOther;
    private String imagePathBank;
    private String imagePathAnimalEpidemic;

    private GridImageAdapter adapter;
    private int maxSelectNum = 4;
    private List<LocalMedia> selectList = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, CullingInsuranceInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_culling_insurance_info;
    }

    @Override
    protected void initViews() {
        back_iv = findViewById(R.id.back_iv);
        signatureView = findViewById(R.id.signature_view);
        add_beneficiary_btn = findViewById(R.id.add_beneficiary_btn);
        be_insured_people_btn = findViewById(R.id.be_insured_people_btn);
        beneficiary_ll = findViewById(R.id.beneficiary_ll);
        be_insured_ll = findViewById(R.id.be_insured_ll);


        insured_name_et = findViewById(R.id.insured_name_et);
        insured_card_type_et = findViewById(R.id.insured_card_type_et);
        insured_id_card_et = findViewById(R.id.insured_id_card_et);
        insured_phone_et = findViewById(R.id.insured_phone_et);
        insured_address_et = findViewById(R.id.insured_address_et);
        insured_underwriting_address_et = findViewById(R.id.insured_underwriting_address_et);
        insured_choose_address_et = findViewById(R.id.insured_choose_address_et);
        insured_location_et = findViewById(R.id.insured_location_et);

        beneficiary_name_et = findViewById(R.id.beneficiary_name_et);
        beneficiary_card_type_et = findViewById(R.id.beneficiary_card_type_et);
        beneficiary_id_card_et = findViewById(R.id.beneficiary_id_card_et);
        beneficiary_phone_et = findViewById(R.id.beneficiary_phone_et);
        beneficiary_address_et = findViewById(R.id.beneficiary_address_et);

        be_insured_name_et = findViewById(R.id.be_insured_name_et);
        be_insured_type_et = findViewById(R.id.be_insured_type_et);
        be_insured_address_et = findViewById(R.id.be_insured_address_et);
        be_insured_card_type_et = findViewById(R.id.be_insured_card_type_et);
        be_insured_id_card_et = findViewById(R.id.be_insured_id_card_et);
        be_insured_phone_et = findViewById(R.id.be_insured_phone_et);

        farm_poor_type_et = findViewById(R.id.farm_poor_type_et);
        scale_name_et = findViewById(R.id.scale_name_et);
        bank_name_et = findViewById(R.id.bank_name_et);
        bank_account_et = findViewById(R.id.bank_account_et);
        pig_num_et = findViewById(R.id.pig_num_et);
        premium_short_ll = findViewById(R.id.premium_short_ll);
        premium_long_ll = findViewById(R.id.premium_long_ll);

        id_card_iv = findViewById(R.id.id_card_iv);
        id_card_iv_bt = findViewById(R.id.id_card_iv_bt);

        other_id_card_iv = findViewById(R.id.other_id_card_iv);
        other_id_card_iv_bt = findViewById(R.id.other_id_card_iv_bt);

        id_bank_card_iv = findViewById(R.id.id_bank_card_iv);
        id_bank_card_bt = findViewById(R.id.id_bank_card_bt);

        animal_epidemic_iv = findViewById(R.id.animal_epidemic_iv);
        animal_epidemic_btn = findViewById(R.id.animal_epidemic_btn);

        recyclerView = findViewById(R.id.recyclerview);

        clear_btn = findViewById(R.id.clear_btn);
        notice_btn = findViewById(R.id.notice_btn);

        terms_tv = findViewById(R.id.terms_tv);

        add_beneficiary_btn.setOnClickListener(this);
        be_insured_people_btn.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        premium_short_ll.setOnClickListener(this);
        premium_long_ll.setOnClickListener(this);
        insured_choose_address_et.setOnClickListener(this);
        farm_poor_type_et.setOnClickListener(this);
        beneficiary_card_type_et.setOnClickListener(this);
        be_insured_card_type_et.setOnClickListener(this);
        id_card_iv_bt.setOnClickListener(this);
        other_id_card_iv_bt.setOnClickListener(this);
        id_bank_card_bt.setOnClickListener(this);
        animal_epidemic_btn.setOnClickListener(this);
        clear_btn.setOnClickListener(this);
        notice_btn.setOnClickListener(this);
        insured_card_type_et.setOnClickListener(this);


        pig_num_et.setText("0");
        insured_card_type_et.setText(DialogShowUtils.stringsInsuredCardType[DialogShowUtils.positionInsuredCardType]);
        farm_poor_type_et.setText(DialogShowUtils.stringsPoorType[DialogShowUtils.positionPoor]);
        beneficiary_card_type_et.setText(DialogShowUtils.stringsBeneficiaryCardType[DialogShowUtils.positionBeneficiaryCardType]);
        be_insured_card_type_et.setText(DialogShowUtils.stringsBeInsuredCardType[DialogShowUtils.positionBeInsuredCardType]);
        insured_choose_address_et.setText(stringsVillageType[positionVillage]);//默认村落

        setHigthOnClick();
    }

    @Override
    protected void initDatas() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(this, 8), false));
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum - selectList.size());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(CullingInsuranceInfoActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(CullingInsuranceInfoActivity.this).externalPictureVideo(media.getPath());
                        case 3:
                            // 预览音频
                            PictureSelector.create(CullingInsuranceInfoActivity.this).externalPictureAudio(media.getPath());
                            break;
                        default:
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {
            showChoices();
        }
    };

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
            case R.id.add_beneficiary_btn://点击受益人
                if (!add_beneficiary_btnFlag) {
                    add_beneficiary_btn.setText("受益人同投保人");
                    add_beneficiary_btnFlag = true;
                    beneficiary_ll.setVisibility(View.VISIBLE);
                } else {
                    add_beneficiary_btn.setText("+添加受益人");
                    beneficiary_ll.setVisibility(View.GONE);
                    add_beneficiary_btnFlag = false;
                }

                break;
            case R.id.be_insured_people_btn://+添加被保险人
                if (!be_insured_people_btnFlag) {
                    be_insured_people_btn.setText("被保险人同投保人");
                    be_insured_people_btnFlag = true;
                    be_insured_ll.setVisibility(View.VISIBLE);
                } else {
                    be_insured_people_btn.setText("+添加被保险人");
                    be_insured_ll.setVisibility(View.GONE);
                    be_insured_people_btnFlag = false;
                }

                break;
            case R.id.premium_short_ll:
                premium_short_ll.setBackground(ContextCompat.getDrawable(this, R.drawable.choose_sum_assured_focused));
                premium_long_ll.setBackground(ContextCompat.getDrawable(this, R.drawable.choose_sum_assured_bg));
                break;
            case R.id.premium_long_ll:
                premium_short_ll.setBackground(ContextCompat.getDrawable(this, R.drawable.choose_sum_assured_bg));
                premium_long_ll.setBackground(ContextCompat.getDrawable(this, R.drawable.choose_sum_assured_focused));
                break;
            case R.id.insured_choose_address_et://选择所在村落
                showChooseVillageDialog();
                break;
            case R.id.farm_poor_type_et://贫困户标识
                DialogShowUtils.showChoosePoorDialog(this, farm_poor_type_et);
                break;
            case R.id.beneficiary_card_type_et://受益人证件类型
                DialogShowUtils.showChooseBeneficiaryCardTypeDialog(this, beneficiary_card_type_et);
                break;
            case R.id.be_insured_card_type_et://被保险人证件类型
                DialogShowUtils.showChooseBeInsuredCardTypeCardTypeDialog(this, be_insured_card_type_et);
                break;
            case R.id.id_card_iv_bt:
                TYPE = 1;
                upImageViewBottomDialog();
                break;
            case R.id.other_id_card_iv_bt:
                TYPE = 2;
                upImageViewBottomDialog();
                break;
            case R.id.id_bank_card_bt:
                TYPE = 3;
                upImageViewBottomDialog();
                break;
            case R.id.animal_epidemic_btn:
                TYPE = 4;
                upImageViewBottomDialog();
                break;
            case R.id.clear_btn://清楚签名
                signatureView.clear();
                break;
            case R.id.notice_btn://用户须知
                showNotify();
                break;
            case R.id.insured_card_type_et://投保人证件类型
                DialogShowUtils.showChooseInsuredCardTypeDialog(this, insured_card_type_et);
                break;
            default:
        }
    }

    /**
     * 弹出对话框设置选择图片方式
     */
    private void showChoices() {
        final BottomPopupDialog bottomPopupDialog;
        List<String> bottomDialogContents;//弹出列表的内容
        bottomDialogContents = new ArrayList<>();
        bottomDialogContents.add(getString(R.string.take_photo));
        bottomDialogContents.add(getString(R.string.select_from_gallery));
        bottomPopupDialog = new BottomPopupDialog(this, bottomDialogContents);
        bottomPopupDialog.show();
        bottomPopupDialog.showCancelBtn(true);
        bottomPopupDialog.setOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {//拍照
                    rxPermissions.request(Manifest.permission.CAMERA)
                            .subscribe(aBoolean -> {
                                if (aBoolean) {//开启权限
                                    //拍照
                                    PictureSelector.create(CullingInsuranceInfoActivity.this)
                                            .openCamera(PictureMimeType.ofImage())
                                            .maxSelectNum(maxSelectNum - selectList.size())//最大选择数量,默认4张
                                            .minSelectNum(1)//// 最小选择数量
                                            .imageSpanCount(4)// 列表每行显示个数
                                            .enableCrop(false)// 是否裁剪 true or false
                                            .compress(true)// 是否压缩
                                            .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                            .selectionMode(PictureConfig.MULTIPLE)//单选or多选 PictureConfig.SINGLE PictureConfig.MULTIPLE
                                            .forResult(PictureConfig.CHOOSE_REQUEST);
                                    LogUtil.d("lzx-----》", maxSelectNum - selectList.size() + "选取几张");
                                } else {
                                    ToastUtil.showToast(CullingInsuranceInfoActivity.this, "请开启相机权限！");
                                }
                            });
                } else {//照片图库
                    //相册
                    PictureSelector.create(CullingInsuranceInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())//相册 媒体类型 PictureMimeType.ofAll()、ofImage()、ofVideo()、ofAudio()
                            .maxSelectNum(maxSelectNum - selectList.size())//最大选择数量,默认9张
                            .minSelectNum(1)//// 最小选择数量
                            .imageSpanCount(4)// 列表每行显示个数
                            .enableCrop(false)// 是否裁剪 true or false
                            .compress(true)// 是否压缩
                            .selectionMode(PictureConfig.MULTIPLE)//单选or多选 PictureConfig.SINGLE PictureConfig.MULTIPLE
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
            }
        });
    }

    /**
     * 底部弹出去拍照还是去选择相册
     */
    private void upImageViewBottomDialog() {
        List<String> bottomDialogContents = new ArrayList<>();
        bottomDialogContents.add("拍照");
        bottomDialogContents.add("相册选取");
        final BottomPopupDialog bottomPopupDialog = new BottomPopupDialog(this, bottomDialogContents);
        bottomPopupDialog.showCancelBtn(true);
        bottomPopupDialog.show();
        bottomPopupDialog.setCancelable(true);
        bottomPopupDialog.setOnItemClickListener((v, position) -> {
            bottomPopupDialog.dismiss();
            switch (TYPE) {
                case 1:
                    if (position == 0) {   //拍照
                        checkPermission(1);
                    } else { //相册选取
                        selectImageFromLocal();
                    }
                    break;

                case 2:
                    if (position == 0) {   //拍照
                        checkPermission(2);
                    } else { //相册选取
                        selectImageFromLocal();
                    }
                    break;
                case 3:
                    if (position == 0) {   //拍照
                        checkPermission(3);
                    } else { //相册选取
                        selectImageFromLocal();
                    }
                    break;
                case 4:
                    if (position == 0) {   //拍照
                        checkPermission(4);
                    } else { //相册选取
                        selectImageFromLocal();
                    }
                    break;
            }
        });
        bottomPopupDialog.setOnDismissListener(dialog -> dialog.dismiss());
    }


    /**
     * 检查权限 6个拍照。
     */
    @SuppressLint("CheckResult")
    private void checkPermission(int TYPE) {
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {//开启权限
                            switch (TYPE) {
                                case 1:
                                    selectImageFromCamera();//去照相
                                    break;
                                case 2:
                                    selectImageFromCameraTr();//去照相
                                    break;
                                case 3:
                                    selectImageFromCameraLin();//去照相
                                    break;
                                case 4:
                                    selectImageFromCameraRun();//去照相
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            Toast.makeText(CullingInsuranceInfoActivity.this, "请开启照相机权限！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 从本地选择图片
     */
    public void selectImageFromLocal() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);//这里加入flag
        this.startActivityForResult(intent, REQUEST_CODE_FROM_GALLERY);
    }

    /**
     * 省份证正面
     */
    public void selectImageFromCamera() {
        mCameraFileIdCard = new File(AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, this), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtil.getFileUri(this, mCameraFileIdCard));
        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }

    /**
     * 省份证反面
     */
    public void selectImageFromCameraTr() {
        mCameraFileIdCardOther = new File(AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, this), System.currentTimeMillis() + "Transport" + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtil.getFileUri(this, mCameraFileIdCardOther));
        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }


    /**
     * 银行卡
     */
    public void selectImageFromCameraLin() {
        mCameraFileBank = new File(AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, this), System.currentTimeMillis() + "Lin" + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtil.getFileUri(this, mCameraFileBank));
        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }


    /**
     * 动物防疫合格证
     */
    public void selectImageFromCameraRun() {
        mCameraFileAnimalEpidemic = new File(AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, this), System.currentTimeMillis() + "Run" + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtil.getFileUri(this, mCameraFileAnimalEpidemic));
        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }


    /**
     * 回调 图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case REQUEST_CODE_FROM_GALLERY: {
                    if (data != null) {
                        Uri uri = data.getData();
                        Bitmap bitmapFormUri = null;
                        try {
                            bitmapFormUri = BitmapUtils.getBitmapFormUri(uri);
                            switch (TYPE) {
                                case 1:
                                    id_card_iv.setImageBitmap(bitmapFormUri);
                                    imagePathIDCard = FileUtil.getPathFromUri(this, uri);
                                    break;
                                case 2:
                                    other_id_card_iv.setImageBitmap(bitmapFormUri);
                                    imagePathIDCardOther = FileUtil.getPathFromUri(this, uri);
                                    LogUtil.d(TAG, imagePathIDCardOther.toString());
                                    break;

                                case 3:
                                    id_bank_card_iv.setImageBitmap(bitmapFormUri);
                                    imagePathBank = FileUtil.getPathFromUri(this, uri);
                                    LogUtil.d(TAG, imagePathBank.toString());
                                    break;
                                case 4:
                                    animal_epidemic_iv.setImageBitmap(bitmapFormUri);
                                    imagePathAnimalEpidemic = FileUtil.getPathFromUri(this, uri);
                                    LogUtil.d(TAG, imagePathAnimalEpidemic.toString());
                                    break;
                                default:
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case REQUEST_CODE_FROM_CAMERA:
                    switch (TYPE) {
                        case 1:
                            Uri imageUri = Uri.fromFile(this.mCameraFileIdCard);
                            imagePathIDCard = FileUtil.getPathFromUri(this, imageUri);
                            imagePathIDCard = ImageUtil.setPhoto(this, this.mCameraFileIdCard.getPath(), id_card_iv);

                            LogUtil.d(TAG, mCameraFileIdCard.toString());
                            break;
                        case 2:
                            Uri imageUriTransport = Uri.fromFile(this.mCameraFileIdCardOther);
                            imagePathIDCardOther = FileUtil.getPathFromUri(this, imageUriTransport);
                            imagePathIDCardOther = ImageUtil.setPhoto(this, this.mCameraFileIdCardOther.getPath(), other_id_card_iv);
                            LogUtil.d(TAG, mCameraFileIdCardOther.toString());
                            break;
                        case 3:
                            Uri imageUriLin = Uri.fromFile(this.mCameraFileBank);
                            imagePathBank = FileUtil.getPathFromUri(this, imageUriLin);
                            imagePathBank = ImageUtil.setPhoto(this, this.mCameraFileBank.getPath(), id_bank_card_iv);
                            LogUtil.d(TAG, mCameraFileBank.toString());
                            break;
                        case 4:
                            Uri imageUriRun = Uri.fromFile(this.mCameraFileAnimalEpidemic);
                            imagePathAnimalEpidemic = FileUtil.getPathFromUri(this, imageUriRun);
                            imagePathAnimalEpidemic = ImageUtil.setPhoto(this, this.mCameraFileAnimalEpidemic.getPath(), animal_epidemic_iv);
                            LogUtil.d(TAG, mCameraFileAnimalEpidemic.toString());
                            break;
                        default:
                    }
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
                    LogUtil.d("lzx----->", selectList.size() + "");
                    //selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 4.如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                default:
                    break;
            }

        }
    }

    private String[] stringsVillageType = {"兴顺社区", "桐远社区", "陈沱村", "亚峰岩村", "雷达村", "古桥村"};
    private int positionVillage = 0;

    /**
     * 选择所在村落
     */
    public void showChooseVillageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CullingInsuranceInfoActivity.this);
        builder.setTitle("选择所在村落 ");
        builder.setSingleChoiceItems(stringsVillageType, positionVillage, (dialog, which) -> {
            insured_choose_address_et.setText(stringsVillageType[which]);
            TimerDelay.setDelay(dialog);
            positionVillage = which;
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_border_white));
        alertDialog.show();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
//        params.height = this.getWindowManager().getDefaultDisplay().getHeight();
//          params.height = (int) (ScreenUtils.getScreenHeight(this) * 0.65);
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        alertDialog.getWindow().setAttributes(params);
    }

    private void showNotify() {
        View view = getLayoutInflater().inflate(R.layout.show_tips_xml, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(true);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView contentTv = (TextView) view.findViewById(R.id.content_tv);
        TextView title = (TextView) view.findViewById(R.id.title_tv);
        title.setText("生猪疫病强制扑杀补偿保险投保须知");
        contentTv.setText(this.getString(R.string.culling_notice));
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        params.height = this.getWindowManager().getDefaultDisplay().getHeight();
        params.height = (int) (ScreenUtils.getScreenHeight(this) * 0.65);
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        exitDialog.getWindow().setAttributes(params);
    }

    /**
     * 设置高亮并点击
     */
    private void setHigthOnClick() {
        //拼接字符串
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder("本人已阅读并同意");
        //《保险条款》
        SpannableString span = new SpannableString("《生猪疫病强制扑杀补偿保险投保须知》");
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showNotify();
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.C4)),
                0,
                span.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        spanBuilder.append(span);
        spanBuilder.append(",并承诺实际为本人操作。");
        // 赋值给TextView
        terms_tv.setMovementMethod( LinkMovementMethod.getInstance());
        terms_tv.setText(spanBuilder);
        //设置高亮颜色透明，因为点击会变色
        terms_tv.setHighlightColor( ContextCompat.getColor(this, R.color.transparent));
    }

}
