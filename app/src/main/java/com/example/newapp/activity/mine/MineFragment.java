package com.example.newapp.activity.mine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.newapp.R;
import com.example.newapp.activity.login.LoginActivity;
import com.example.newapp.activity.mine.bulletin.BulletinActivity;
import com.example.newapp.activity.mine.set.mysetting.MySettingActivity;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.fragment.main_fragment.HomeMainFragment;
import com.example.newapp.sp.FirstSpUtils;
import com.example.newapp.sp.UserSp;
import com.example.newapp.utils.AppManager;
import com.example.newapp.utils.AppUtil;
import com.example.newapp.utils.FileUtil;
import com.example.newapp.utils.GlideUtil;
import com.example.newapp.utils.LogUtil;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.ToastUtil;
import com.example.newapp.weight.bottomPopupDialog.BottomPopupDialog;
import com.soundcloud.android.crop.Crop;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;

/**
 * @ProjectName : NewApp
 * @Author : lzx
 * @Time : 2021/1/22 13:50
 * @Description : 我的页面
 */
public class MineFragment extends BaseFragment {

    public static final int CROP_IMAGE_SIZE = 512;
    public static final int REQUEST_CODE_FROM_GALLERY = 301;
    public static final int REQUEST_CODE_FROM_CAMERA = 302;

    private static final long FILE_MAX_SIZE = 10 * 1024 * 1024; // 10M

    // 图像保存路径
    private String imagePath = AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, getActivity()) + File.separator;
    private String imageName = System.currentTimeMillis() + "_head.jpg";

    private File mCameraFile; // 头像文件

    private LinearLayout mMySetting_ll;
    private LinearLayout mMyFeedback_ll;
    private ImageView head_iv;
    private RxPermissions rxPermissions;
    private TextView name_tv;
    private LinearLayout order_historical;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        String photoPath = UserSp.getInstance().getPhotoPath();
        if (!TextUtils.isEmpty(photoPath)){
            File fileByPath = FileUtil.getFileByPath(photoPath);
            LogUtil.d("lzx",fileByPath.getAbsolutePath() + "fileByPath");
            GlideUtil.loadCircleImage( fileByPath,getActivity(),head_iv,R.drawable.ic_user_face_default);//加载URL图片
            LogUtil.d("lzx",photoPath);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        rxPermissions = new RxPermissions(getActivity());
        name_tv = mRootView.findViewById(R.id.name_tv);
        mMySetting_ll = mRootView.findViewById(R.id.check_suggestions);
        mMySetting_ll.setOnClickListener(this);
        mMyFeedback_ll = mRootView.findViewById(R.id.bulletin_board_ll);
        mMyFeedback_ll.setOnClickListener(this);
        head_iv = mRootView.findViewById(R.id.head_iv);
        head_iv.setOnClickListener(this);
        order_historical = mRootView.findViewById(R.id.order_historical);
        order_historical.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return  null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.check_suggestions:
                MySettingActivity.start(getActivity());
                break;
            case R.id.bulletin_board_ll://公告栏
                if (!FirstSpUtils.getInstance().getNoticeShow()){
                    BulletinActivity.start(getActivity());
                }else {
                    showNoticeDialog();
                }

                break;
            case R.id.order_historical://历史订单

                break;
            case R.id.head_iv:
                showBottomSheet();
                break;
            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    String[] permission = {
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    String[] permission1 = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    /**
     * 显示底部弹出框
     */
    private void showBottomSheet() {
        List<String> bottomDialogContents = new ArrayList<>();
        bottomDialogContents.add(getResources().getString(R.string.select_from_album));
        bottomDialogContents.add(getResources().getString(R.string.take_pictures));
        final BottomPopupDialog bottomPopupDialog = new BottomPopupDialog(getContext(), bottomDialogContents);
        bottomPopupDialog.showCancelBtn(true);
        bottomPopupDialog.show();
        bottomPopupDialog.setCancelable(true);
        bottomPopupDialog.setOnItemClickListener(new BottomPopupDialog.OnItemClickListener() {


            @Override
            public void onItemClick(View v, int position) {
                bottomPopupDialog.dismiss();
                if (position == 0) {    // 相册选择头像
                    disposable = rxPermissions.request(permission1)
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {
                                    if (aBoolean) {//开启权限
                                        selectImageFromLocal();
                                    } else {
                                        ToastUtil.showToast(getActivity(), R.string.start_competence);
                                    }
                                }
                            });
                    compositeDisposable.add(disposable);
                } else if (position == 1) {//拍照设置头像
                    disposable = rxPermissions.request(permission)
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {
                                    if (aBoolean) {//开启权限
                                        selectImageFromCamera();
                                    } else {
                                        ToastUtil.showToast(getActivity(), R.string.start_permissions_cream);
                                    }
                                }
                            });
                    compositeDisposable.add(disposable);
                }
            }
        });

        bottomPopupDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 选择图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FROM_GALLERY: {
                    Uri uri = data.getData();
                    File file = FileUtil.uriToFile(getActivity(), uri);
                    long fileSize = file.length();
                    if (fileSize > FILE_MAX_SIZE) {
                        ToastUtil.showToast(getActivity(), R.string.file_size_cannot_exceed_10M);
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {    // Android 7.0 以上版本
                            uri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider", file);
                        }
                        this.cropImage(uri);
                    }
                    break;
                }
                case REQUEST_CODE_FROM_CAMERA: {
                    Uri imageUri = Uri.fromFile(this.mCameraFile);
                  //  this.cropImage(imageUri);
                    String imagePath = FileUtil.getPathFromUri(getActivity(), imageUri);
                    GlideUtil.loadCircleImage(imagePath,getActivity(),head_iv,R.drawable.ic_user_face_default);//加载URL图片
                    UserSp.getInstance().setPhotoPath(imagePath);
                    break;
                }
                case Crop.REQUEST_CROP: {
                    Uri imageUri = Crop.getOutput(data);
                    String imagePath = FileUtil.getPathFromUri(getActivity(), imageUri);
                    LogUtil.i("imageUri-->" + imageUri);
                    LogUtil.i("imagePath-->" + imagePath);
                    GlideUtil.loadCircleImage(imagePath,getActivity(),head_iv,R.drawable.ic_user_face_default);//加载URL图片
                    UserSp.getInstance().setPhotoPath(imagePath);
                    break;
                }
                default:
                    break;
            }
        }
    }

    /**
     * 裁剪图片
     *
     * @param inputUri
     */
    private void cropImage(Uri inputUri) {
        Uri outputUri = Uri.fromFile(new File(imagePath, imageName));
        Crop.of(inputUri, outputUri).asSquare().withMaxSize(CROP_IMAGE_SIZE, CROP_IMAGE_SIZE).start(getActivity(), MineFragment.this);
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
     * 拍照选择图片
     */
    public void selectImageFromCamera() {
        this.mCameraFile = new File(AppUtil.createAppStorageDir(AppUtil.PATH_APP_IMAGE, getActivity()),
                System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtil.getFileUri(getActivity(), this.mCameraFile));
        startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }


    /**
     * 提示通知栏
     */
    private void showNoticeDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_common_notice, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(getContext()).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView confirmTv = (TextView) view.findViewById(R.id.positive_tv);//确定按钮
        TextView contentTv = (TextView) view.findViewById(R.id.content_tv);
        contentTv.setText(R.string.notice_content);
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstSpUtils.getInstance().setNoticeShow(true);
                BulletinActivity.start(getActivity());
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(getContext()) * 0.85);
        exitDialog.getWindow().setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
    }
}
