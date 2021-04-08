package com.example.newapp.activity.zcxbx.info;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.data.EarTag;
import com.example.newapp.activity.financial_services.Insurance.culling.info.GridImageAdapter;
import com.example.newapp.activity.zcxbx.selecteartag.ScanEarTagActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.SuccessInfo;
import com.example.newapp.utils.AppUtil;
import com.example.newapp.utils.BitmapUtils;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.ImageUtil;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.StrUtil;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.FullyGridLayoutManager;
import com.example.newapp.weight.bottomPopupDialog.BottomPopupDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.tencent.mm.opensdk.utils.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/6 16:35
 * @Description :
 */
public class InsuranceInfoCollectionActivity  extends BaseActivity implements View.OnClickListener {
    private EditText pig_name_et;
    private EditText business_license_no_et;
    private  EditText corporate_name_et;
    private EditText id_card_et;
    private EditText pig_address_et;
    private EditText  give_eartag_animal_day;
    private RadioButton  breeding_rb;
    private RadioButton fatten_rb;
    private ImageView mBackIv;
    private LinearLayout lay_age_in_days;
    private EditText phone_et;
    private LinearLayout give_eartag_select;
    private TextView give_eartag_select_tv;
    private static final int REQUEST_CODE_EAR_TAG_NUM = 101;
    private ArrayList<EarTag> list;

    private AppCompatImageView id_card_iv;//上传 身份证正面
    private Button id_card_iv_bt;

    private AppCompatImageView other_id_card_iv;//上传 身份证反面
    private Button other_id_card_iv_bt;

    private AppCompatImageView id_bank_card_iv;//上传 银行卡
    private Button id_bank_card_bt;


    private RecyclerView recyclerView;
    private int TYPE = 1;
    private File mCameraFileIdCard; // 身份证文件
    private File mCameraFileIdCardOther;//身份证反面
    private File mCameraFileBank;//银行卡

    public static final int REQUEST_CODE_FROM_GALLERY = 301;
    public static final int REQUEST_CODE_FROM_CAMERA = 302;
    private GridImageAdapter adapter;
    private int maxSelectNum = 4;
    private List<LocalMedia> selectList = new ArrayList<>();

    private String imagePathIDCard;
    private String imagePathIDCardOther;
    private String imagePathBank;
    private int mType;
private Button commit_btn;
    public static void start(Context context) {
        Intent intent = new Intent(context, InsuranceInfoCollectionActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_insuranceinfo_collection;
    }


    @Override
    protected void initViews() {
        pig_name_et = findViewById(R.id.pig_name_et);
        business_license_no_et = findViewById(R.id.business_license_no_et);
        corporate_name_et = findViewById(R.id.corporate_name_et);
        id_card_et = findViewById(R.id.id_card_et);
        pig_address_et = findViewById(R.id.pig_address_et);
        breeding_rb = findViewById(R.id.breeding_rb);
        fatten_rb = findViewById(R.id.fatten_rb);
        mBackIv =  findViewById(R.id.back_iv);
        lay_age_in_days = findViewById(R.id.lay_age_in_days);
        phone_et = findViewById(R.id.phone_et);
        give_eartag_animal_day = findViewById(R.id.give_eartag_animal_day);
        lay_age_in_days.setVisibility(View.GONE);
        give_eartag_select_tv = findViewById(R.id.give_eartag_select_tv);
        give_eartag_select = findViewById(R.id.give_eartag_select);
        commit_btn = findViewById(R.id.commit_btn);
        id_card_iv = findViewById(R.id.id_card_iv);
        id_card_iv_bt = findViewById(R.id.id_card_iv_bt);

        other_id_card_iv = findViewById(R.id.other_id_card_iv);
        other_id_card_iv_bt = findViewById(R.id.other_id_card_iv_bt);

        id_bank_card_iv = findViewById(R.id.id_bank_card_iv);
        id_bank_card_bt = findViewById(R.id.id_bank_card_bt);

        recyclerView = findViewById(R.id.recyclerview);
        commit_btn.setOnClickListener(this);

        id_card_iv_bt.setOnClickListener(this);
        other_id_card_iv_bt.setOnClickListener(this);
        id_bank_card_bt.setOnClickListener(this);

        breeding_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    fatten_rb.setChecked(false);
                    lay_age_in_days.setVisibility(View.GONE);
                    give_eartag_select_tv.setText("");
                    list =null;
                }
            }
        });
        fatten_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    breeding_rb.setChecked(false);
                    lay_age_in_days.setVisibility(View.VISIBLE);
                    give_eartag_select_tv.setText("");
                    list =null;
                    mType = 2;
                }
            }
        });
        mBackIv.setOnClickListener(v -> finish());
        give_eartag_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ScanEarTagActivity.start(InsuranceInfoCollectionActivity.this,list);
            }
        });
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
                            PictureSelector.create(InsuranceInfoCollectionActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(InsuranceInfoCollectionActivity.this).externalPictureVideo(media.getPath());
                        case 3:
                            // 预览音频
                            PictureSelector.create(InsuranceInfoCollectionActivity.this).externalPictureAudio(media.getPath());
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
                                    PictureSelector.create(InsuranceInfoCollectionActivity.this)
                                            .openCamera(PictureMimeType.ofImage())
                                            .maxSelectNum(maxSelectNum-selectList.size())//最大选择数量,默认4张
                                            .minSelectNum(1)//// 最小选择数量
                                            .imageSpanCount(4)// 列表每行显示个数
                                            .enableCrop(false)// 是否裁剪 true or false
                                            .compress(true)// 是否压缩
                                            .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                            .selectionMode(PictureConfig.MULTIPLE)//单选or多选 PictureConfig.SINGLE PictureConfig.MULTIPLE
                                            .forResult(PictureConfig.CHOOSE_REQUEST);
                                    LogUtil.d("lzx-----》", maxSelectNum-selectList.size() +"选取几张");
                                } else {
                                    ToastUtil.showToast(InsuranceInfoCollectionActivity.this, "请开启相机权限！");
                                }
                            });
                } else {//照片图库
                    //相册
                    PictureSelector.create(InsuranceInfoCollectionActivity.this)
                            .openGallery(PictureMimeType.ofImage())//相册 媒体类型 PictureMimeType.ofAll()、ofImage()、ofVideo()、ofAudio()
                            .maxSelectNum(maxSelectNum-selectList.size())//最大选择数量,默认9张
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


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
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

            case R.id.commit_btn:
                if (checkInfo()) {
                    List<String> paths = new ArrayList<>();
                    List<String> EarTagList = new ArrayList<>();
                    paths.clear();
                    EarTagList.clear();
                    SuccessInfo successInfo = new SuccessInfo();
                    successInfo.PigName = pig_name_et.getText().toString();
                    successInfo.LinecsNum = business_license_no_et.getText().toString();

                    successInfo.Address = pig_address_et.getText().toString();
                    successInfo.PersonName = corporate_name_et.getText().toString();
                    successInfo.IDCard = id_card_et.getText().toString();
                    successInfo.Phone = phone_et.getText().toString();
                    successInfo.DayAge = give_eartag_animal_day.getText().toString();

                    if (list.size() > 0 && list != null) {
                        for (EarTag earTag : list) {
                            EarTagList.add(earTag.getEartagno());
                        }
                    }
                    successInfo.EarTags = listToString(EarTagList);
                    successInfo.IDCardPhotoPositive = imagePathIDCard;
                    successInfo.IDCardPhotoReverse = imagePathIDCardOther;
                    successInfo.BankPhoto = imagePathBank;
                    for (int i = 0; i < selectList.size(); i++) {
                        if (selectList.get(i).isCompressed()) {
                            String compressPath = selectList.get(i).getCompressPath();
                            paths.add(compressPath);
                        }
                    }
                    successInfo.MeansPhotos = paths;
                    InfoCollectionSuccessActivity.start(InsuranceInfoCollectionActivity.this, successInfo);
                }
                break;
            default:
                break;
        }
    }
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
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
                default:
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
                                default:
                                    break;
                            }
                        } else {
                            Toast.makeText(InsuranceInfoCollectionActivity.this, "请开启照相机权限！", Toast.LENGTH_SHORT).show();
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
                                    break;

                                case 3:
                                    id_bank_card_iv.setImageBitmap(bitmapFormUri);
                                    imagePathBank = FileUtil.getPathFromUri(this, uri);
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
                            Log.d("lzx----》",imageUri.toString());
                            Log.d("lzx----》",imagePathIDCard);
                            break;
                        case 2:
                            Uri imageUriTransport = Uri.fromFile(this.mCameraFileIdCardOther);
                            imagePathIDCardOther = FileUtil.getPathFromUri(this, imageUriTransport);
                            imagePathIDCardOther = ImageUtil.setPhoto(this, this.mCameraFileIdCardOther.getPath(), other_id_card_iv);

                            break;
                        case 3:
                            Uri imageUriLin = Uri.fromFile(this.mCameraFileBank);
                            imagePathBank = FileUtil.getPathFromUri(this, imageUriLin);
                            imagePathBank = ImageUtil.setPhoto(this, this.mCameraFileBank.getPath(), id_bank_card_iv);

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
                    break;
                case REQUEST_CODE_EAR_TAG_NUM://耳标界面返回
                    list = (ArrayList<EarTag>) data.getSerializableExtra("TES");
                    if (list.size() > 0) {
                        give_eartag_select_tv.setText("已选择" + list.size() + "个耳标");
                    } else {
                        give_eartag_select_tv.setText("没选中的耳标");
                    }
                    break;
                default:
                    break;
            }

        }
    }
    private boolean checkInfo() {
        if (StrUtil.isEmpty(pig_name_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入猪场名称");
            return false;
        }
        if (StrUtil.isEmpty(business_license_no_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入营业执照号");
            return false;
        }
        if (StrUtil.isEmpty(corporate_name_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入法人姓名");
            return false;
        }
        if (StrUtil.isEmpty(id_card_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入身份证号");
            return false;
        }

        if (StrUtil.isEmpty(phone_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入电话");
            return false;
        }

        if (StrUtil.isEmpty(pig_address_et.getText().toString())) {
            ToastUtil.showToast(this, "请输入猪场地址");
            return false;
        }
        if (mType==2 && StrUtil.isEmpty(give_eartag_animal_day.getText().toString())){
            ToastUtil.showToast(this, "请输入日龄");
            return false;
        }
        if (list == null){
            ToastUtil.showToast(this, "请选择耳标号");
            return false;
        }
            if (StrUtil.isEmpty(imagePathIDCard)) {
                ToastUtil.showToast(this, "请上传身份证正面");
                return false;
            }
            if (StrUtil.isEmpty(imagePathIDCardOther)) {
                ToastUtil.showToast(this, "请上传身份证反面");
                return false;
            }
            if (StrUtil.isEmpty(imagePathBank)) {
                ToastUtil.showToast(this, "请上传机银行卡照片");
                return false;
            }
            if (selectList.size()==0) {
                ToastUtil.showToast(this, "请上传验标资料照片");
                return false;
            }
        return true;
    }
}
