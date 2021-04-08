package com.example.newapp.activity.claim_check;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.newapp.R;
import com.example.newapp.activity.zcxbx.info.InsuranceInfoCollectionActivity;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.AppUtil;
import com.example.newapp.utils.BitmapUtils;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.ImageUtil;
import com.example.newapp.weight.bottomPopupDialog.BottomPopupDialog;
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
 * @Time : 2021/4/7 14:54
 * @Description :  理赔信息采集
 */
public class ClaimCheckActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;

    private AppCompatImageView dead_pig_photos_iv;//上传死亡猪照片
    private Button dead_pig_photos_bt;

    private AppCompatImageView disposal_certificate_site_iv;//上传无害化处理现场照片
    private Button disposal_certificate_site_bt;

    private AppCompatImageView disposal_certificate_photos_iv;//上传无害化处理证明照片
    private Button disposal_certificate_photos_bt;
    private File mCameraFileIdCard; // 身份证文件
    private File mCameraFileIdCardOther;//身份证反面
    private File mCameraFileBank;//银行卡

    public static final int REQUEST_CODE_FROM_GALLERY = 301;
    public static final int REQUEST_CODE_FROM_CAMERA = 302;
    

    private String imagePathPigPhotos;
    private String imagePathSite;
    private String imagePathDisposalCertificate;


    private int TYPE = 1;
    public static void start(Context context) {
        Intent intent = new Intent(context, ClaimCheckActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_claim_check;
    }

    @Override
    protected void initViews() {
        mBackIv = findViewById(R.id.back_iv);
        mBackIv.setOnClickListener(this);

        dead_pig_photos_iv = findViewById(R.id.dead_pig_photos_iv);
        dead_pig_photos_bt = findViewById(R.id.dead_pig_photos_bt);

        disposal_certificate_site_iv = findViewById(R.id.disposal_certificate_site_iv);
        disposal_certificate_site_bt = findViewById(R.id.disposal_certificate_site_bt);

        disposal_certificate_photos_iv = findViewById(R.id.disposal_certificate_photos_iv);
        disposal_certificate_photos_bt = findViewById(R.id.disposal_certificate_photos_bt);

        dead_pig_photos_bt.setOnClickListener(this);
        disposal_certificate_site_bt.setOnClickListener(this);
        disposal_certificate_photos_bt.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.dead_pig_photos_bt:
                TYPE = 1;
                upImageViewBottomDialog();
                break;
            case R.id.disposal_certificate_site_bt:
                TYPE = 2;
                upImageViewBottomDialog();
                break;
            case R.id.disposal_certificate_photos_bt:
                TYPE = 3;
                upImageViewBottomDialog();
                break;
            default:
                break;
        }
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
                            Toast.makeText(ClaimCheckActivity.this, "请开启照相机权限！", Toast.LENGTH_SHORT).show();
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
                                    dead_pig_photos_iv.setImageBitmap(bitmapFormUri);
                                    imagePathPigPhotos = FileUtil.getPathFromUri(this, uri);
                                    break;
                                case 2:
                                    disposal_certificate_site_iv.setImageBitmap(bitmapFormUri);
                                    imagePathSite = FileUtil.getPathFromUri(this, uri);
                                    break;

                                case 3:
                                    disposal_certificate_photos_iv.setImageBitmap(bitmapFormUri);
                                    imagePathDisposalCertificate = FileUtil.getPathFromUri(this, uri);
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
                            imagePathPigPhotos = FileUtil.getPathFromUri(this, imageUri);
                            imagePathPigPhotos = ImageUtil.setPhoto(this, this.mCameraFileIdCard.getPath(), dead_pig_photos_iv);
                            Log.d("lzx----》",imageUri.toString());
                            Log.d("lzx----》",imagePathPigPhotos);
                            break;
                        case 2:
                            Uri imageUriTransport = Uri.fromFile(this.mCameraFileIdCardOther);
                            imagePathSite = FileUtil.getPathFromUri(this, imageUriTransport);
                            imagePathSite = ImageUtil.setPhoto(this, this.mCameraFileIdCardOther.getPath(), disposal_certificate_site_iv);

                            break;
                        case 3:
                            Uri imageUriLin = Uri.fromFile(this.mCameraFileBank);
                            imagePathDisposalCertificate = FileUtil.getPathFromUri(this, imageUriLin);
                            imagePathDisposalCertificate = ImageUtil.setPhoto(this, this.mCameraFileBank.getPath(), disposal_certificate_photos_iv);
                            break;
                        default:
                    }
                    break;
                default:
                    break;
            }

        }
    }
}
