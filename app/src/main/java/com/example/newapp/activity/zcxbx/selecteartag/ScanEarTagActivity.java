package com.example.newapp.activity.zcxbx.selecteartag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapp.R;
import com.example.newapp.data.EarTag;
import com.example.newapp.data.ScanData;
import com.example.newapp.activity.zcxbx.handadd.GiveBatchEartagActivity;
import com.example.newapp.activity.zcxbx.info.ScanAdapter;
import com.example.newapp.base.BaseActivity;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 9:17
 * @Description :
 */
public class ScanEarTagActivity extends BaseActivity implements View.OnClickListener {
    private TextView into_ear_tag_tv;
    private ScanEarTagAdapter scanEarTagAdapter;
    private RecyclerView mEarTagRecyclerView;
    private LinearLayout no_data_ll;
    private ImageView mBack;

    private ArrayList<ScanData> mScanDataList;
    int[] rids = {
            R.drawable.bluetooth_scan,
            R.drawable.hand_add,
    };
    private static final int REQUEST_CODE_SCAN = 22;
    private static final int REQUEST_CODE_EAR_TAG_NUM = 101;
    private static final int HAND_ADD = 88;
    private ArrayList<EarTag> list;

    public static void start(Activity context,ArrayList<EarTag> list) {
        Intent intent = new Intent(context, ScanEarTagActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST",list);
        intent.putExtra("data", bundle);
        context.startActivityForResult(intent,REQUEST_CODE_EAR_TAG_NUM);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_scan_ear_tag;
    }

    @Override
    protected void initViews() {
        Bundle bundle = getIntent().getBundleExtra("data");
        list = (ArrayList<EarTag>) bundle.getSerializable("LIST");

        into_ear_tag_tv = findViewById(R.id.into_ear_tag_tv);
        into_ear_tag_tv.setOnClickListener(this);
        mEarTagRecyclerView = findViewById(R.id.recyclerView);
        no_data_ll = findViewById(R.id.no_data_ll);
        mEarTagRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (list==null){
            Log.d("lzx----》","哈哈哈");
            no_data_ll.setVisibility(View.VISIBLE);
            mEarTagRecyclerView.setVisibility(View.GONE);
            list = new ArrayList<>();
        }else {
            no_data_ll.setVisibility(View.GONE);
            mEarTagRecyclerView.setVisibility(View.VISIBLE);
            Log.d("lzx----》","哈哈哈1111");
        }
        scanEarTagAdapter = new ScanEarTagAdapter(R.layout.ear_tag_item, this);
        mEarTagRecyclerView.setAdapter(scanEarTagAdapter);
        scanEarTagAdapter.refreshDataList(list);
        mBack = findViewById(R.id.back_iv);
        mBack.setOnClickListener(this);
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
            case R.id.into_ear_tag_tv:
                showScanDialog();
                break;
            case R.id.back_iv://返回
                if (scanEarTagAdapter.getDataList()!=null && scanEarTagAdapter.getDataList().size()>0){
                    finishPage(scanEarTagAdapter.getDataList().size());
                }else {
                    finish();
                }
                break;
            default:
                break;
        }
    }
    private void finishPage(int temp) {
        Intent in = new Intent();
        in.putExtra("ImmuneNumber", temp);
        in.putExtra("TES", list);
        setResult(RESULT_OK, in);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishPage(scanEarTagAdapter.getDataList().size());
            return true;
        }
        return true;
    }
    /**
     * add 数据
     */
    private void addData() {
        mScanDataList = new ArrayList<>();
        mScanDataList.clear();
        String[] strings = {"智能耳标钳录入", "手工批量录入"};
        int id = 0;
        int GroupID = 0;
        for (String string : strings) {
            id++;
            GroupID++;
            ScanData scanData = new ScanData();
            scanData.setName(string);
            scanData.setGroupId(GroupID);
            mScanDataList.add(scanData);
        }
        addMenuList(mScanDataList);
    }

    /**
     * add  图片
     *
     * @param tsms
     */
    private void addMenuList(List<ScanData> tsms) {
        for (int i = 0; i < tsms.size(); i++) {
            ScanData ts = tsms.get(i);
            ts.setImageViewID(rids[i % rids.length]);
        }
    }

    /**
     * 显示list 列表
     */
    private void showScanDialog() {
        addData();
        View view = getLayoutInflater().inflate(R.layout.farm_trace_camera_list, null);
        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.setView(view);
        exitDialog.setCanceledOnTouchOutside(true);
        exitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        RecyclerView recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);//取消按钮
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, 1));
        ScanAdapter farmTraceQueryScanAdapter = new ScanAdapter(R.layout.item_farm_trace_camera, this);
        recyclerview.setAdapter(farmTraceQueryScanAdapter);
        farmTraceQueryScanAdapter.refreshDataList(mScanDataList);
        farmTraceQueryScanAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {

            private String eartagno;

            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position) {
                    case 0:// 蓝牙

                        List<EarTag> dataList = new ArrayList<>();
                        dataList.clear();
                        EarTag earTag = new EarTag();
                        earTag.setEartagno("111111111111111");
                        dataList.add(earTag);

                        EarTag earTag1 = new EarTag();
                        earTag1.setEartagno("111111111111112");
                        dataList.add(earTag1);

                        EarTag earTag2 = new EarTag();
                        earTag2.setEartagno("111111111111113");
                        dataList.add(earTag2);

                        EarTag earTag3 = new EarTag();
                        earTag3.setEartagno("111111111111114");
                        dataList.add(earTag3);

                        EarTag earTag4 = new EarTag();
                        earTag4.setEartagno("111111111111115");
                        dataList.add(earTag4);

                        boolean flag = false;
                        for (EarTag tag : dataList) {
                            eartagno = tag.getEartagno();
                            for (EarTag t : list) {
                                if (eartagno.equals(t.getEartagno())) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                EarTag eb = new EarTag();
                                eb.setEartagId(0);
                                eb.setEartagno(eartagno);
                                list.add(0, eb);
                            }
                        }
                        no_data_ll.setVisibility(View.GONE);
                        mEarTagRecyclerView.setVisibility(View.VISIBLE);
                        scanEarTagAdapter.refreshDataList(list);
                        exitDialog.dismiss();
                        break;
                    case 1:// 手动
                        GiveBatchEartagActivity.start(ScanEarTagActivity.this);
                        exitDialog.dismiss();
                        break;
                    default:
                }
            }

            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
        exitDialog.show();
        WindowManager.LayoutParams params = exitDialog.getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(this) * 0.85);
        exitDialog.getWindow().setAttributes(params);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN) {//手动添加耳标
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int n = data.getIntExtra("num", 0);
                    int s = data.getIntExtra("start", 0);
                    int e = data.getIntExtra("end", 0);
                    for (int i = 0; i < e - s + 1; i++) {
                        boolean flag = false;
                        String eartagNo = String.format("%d%08d", n, s + i);
                        for (EarTag t : list) {
                            if (eartagNo.equals(t.getEartagno())) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            EarTag eb = new EarTag();
                            eb.setEartagId(0);
                            eb.setEartagno(eartagNo);
                            list.add(0, eb);
                        }
                    }
                    no_data_ll.setVisibility(View.GONE);
                    mEarTagRecyclerView.setVisibility(View.VISIBLE);
                    scanEarTagAdapter.refreshDataList(list);
                }
            }
        }
    }
}
