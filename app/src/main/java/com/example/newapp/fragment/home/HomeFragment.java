package com.example.newapp.fragment.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.newapp.R;
import com.example.newapp.activity.ai.AiActivity;
import com.example.newapp.activity.animal_protection_products.AnimalProtectionActivity;
import com.example.newapp.activity.breeding_consulting.BreeConsultActivity;
import com.example.newapp.activity.bulk_trade.BulkTradeActivity;
import com.example.newapp.activity.cooperation.CooperationActivity;
import com.example.newapp.activity.environmental.EnvironmentalActivity;
import com.example.newapp.activity.epidemic.EpidemicActivity;
import com.example.newapp.activity.financial_services.FinancialServicesActivity;
import com.example.newapp.activity.financial_services.Insurance.InsuranceActivity;
import com.example.newapp.activity.financial_services.credit.CreditActivity;
import com.example.newapp.activity.financial_services.fund.FundActivity;
import com.example.newapp.activity.financial_services.futures.FuturesActivity;
import com.example.newapp.activity.financial_services.trust.TrustActivity;
import com.example.newapp.activity.immunization_service.ImmunizationServiceActivity;
import com.example.newapp.activity.land_info.LandInfoActivity;
import com.example.newapp.activity.live_pig.LivePigActivity;
import com.example.newapp.activity.means_production.MeansProductionActivity;
import com.example.newapp.activity.old_fashioned.OldFashionedActivity;
import com.example.newapp.activity.pig_industry.PigIndustryActivity;
import com.example.newapp.activity.pig_olympics.PigOlympicsActivity;
import com.example.newapp.activity.pig_online.PigOnlineActivity;
import com.example.newapp.activity.pig_farm.PigFarmActivity;
import com.example.newapp.activity.pig_food.PigFoodActivity;
import com.example.newapp.activity.pig_pet.PigPetActivity;
import com.example.newapp.activity.pig_teach.PigTeachActivity;
import com.example.newapp.activity.pig_trade.PigTradeActivity;
import com.example.newapp.activity.pork_fragrant.PorkFragrantActivity;
import com.example.newapp.activity.pork_trade.PorkTradeActivity;
import com.example.newapp.activity.quarantine.QuarantineActivity;
import com.example.newapp.activity.recruitment.RecruitmentActivity;
import com.example.newapp.activity.traceability.TraceabilityActivity;
import com.example.newapp.activity.transport_capacity.TransportCapacityActivity;
import com.example.newapp.activity.software.SoftWareActivity;
import com.example.newapp.activity.video.VideoActivity;
import com.example.newapp.activity.vip.VipServiceActivity;
import com.example.newapp.adapter.GangTieTypeAdapter;
import com.example.newapp.adapter.GangTieXianHuoAdapter;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.data.BannerData;
import com.example.newapp.data.GangTieData;
import com.example.newapp.data.RecordData;
import com.example.newapp.utils.ScreenUtils;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewAdapter;
import com.example.newapp.utils.recyclerview.BaseRecyclerViewHolder;
import com.example.newapp.utils.recyclerview.GridSpacingItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment implements OnBannerListener , View.OnClickListener {

    private List<Integer> listPic;
    private Banner banner;
    private RecyclerView recyclerView;
    private GangTieTypeAdapter gangTieTypeAdapter;
    private List<RecordData> recordDataList;
    private List<GangTieData> gangTieDataList;

    private GangTieXianHuoAdapter gangTieXianHuoAdapter;
    private RecyclerView recyclerview_shop;
    private RecyclerView recyclerview_futures;
    private List<BaseNode> list;
    private LinearLayout loan_ll;
    private LinearLayout  insurance_ll;
    private LinearLayout  futures_ll;
    private LinearLayout  trust_ll;
    private LinearLayout  fund_ll;



    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.home_fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        loan_ll = mRootView.findViewById(R.id.loan_ll);
        insurance_ll = mRootView.findViewById(R.id.insurance_ll);
        futures_ll = mRootView.findViewById(R.id.futures_ll);
        trust_ll = mRootView.findViewById(R.id.trust_ll);
        fund_ll = mRootView.findViewById(R.id.fund_ll);

        loan_ll.setOnClickListener(this);
        insurance_ll.setOnClickListener(this);
        futures_ll.setOnClickListener(this);
        trust_ll.setOnClickListener(this);
        fund_ll.setOnClickListener(this);

        listPic = new ArrayList<>();
        listPic.clear();
        addData();
        recyclerView  =mRootView.findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),5);
        recyclerView.setLayoutManager(layoutManager);
        gangTieTypeAdapter = new GangTieTypeAdapter(R.layout.gangtie_item, recordDataList,getActivity(),layoutManager);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(GridSpacingItemDecoration.TOP_DECORATION,10);//top间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.BOTTOM_DECORATION,10);//底部间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.LEFT_DECORATION,10);//左间距
        stringIntegerHashMap.put(GridSpacingItemDecoration.RIGHT_DECORATION,10);//右间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMap));
        recyclerView.setAdapter(gangTieTypeAdapter);
        gangTieTypeAdapter.refreshDataList(recordDataList);
        banner =    mRootView.findViewById(R.id.banner);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.height = (int) (ScreenUtils.getScreenHeight(getActivity()) * 0.18);//设置当前控件布局的高度
        banner.setLayoutParams(layoutParams);
        gangTieTypeAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                switch (position){
                    case 0://著会员
                        VipServiceActivity.start(getActivity());
                        break;
                    case 1://金融服务
                        FinancialServicesActivity.start(getActivity());
                        break;
                    case 2://活体交易
                        LivePigActivity.start(getActivity());
                        break;
                    case 3://产品交易
                        PorkTradeActivity.start(getActivity());
                        break;
                    case 4://生猪饲喂
                        PigFoodActivity.start(getActivity());
                        break;
                    case 5://大宗贸易
                        BulkTradeActivity.start(getActivity());
                        break;
                    case 6://著工具
                        MeansProductionActivity.start(getActivity());
                        break;
                    case 7://动保产品
                        AnimalProtectionActivity.start(getActivity());
                        break;
                    case 8://免疫服务
                        ImmunizationServiceActivity.start(getActivity());
                        break;
                    case 9://防疫消毒
                        EpidemicActivity.start(getActivity());
                        break;
                    case 10://检疫申报
                        QuarantineActivity.start(getActivity());
                        break;
                    case 11://著咨询
                        BreeConsultActivity.start(getActivity());
                        break;
                    case 12://猪场建设
                        PigFarmActivity.start(getActivity());
                        break;
                    case 13://人员招聘
                        RecruitmentActivity.start(getActivity());
                        break;
                    case 14://合作招募
                        CooperationActivity.start(getActivity());
                        break;
                    case 15://土地信息
                        LandInfoActivity.start(getActivity());
                        break;
                    case 16://运输运力
                        TransportCapacityActivity.start(getActivity());
                        break;
                    case 17://环保测评
                        EnvironmentalActivity.start(getActivity());
                        break;
                    case 18://人工智能
                        AiActivity.start(getActivity());
                        break;
                    case 19://软件定制
                        SoftWareActivity.start(getActivity());
                        break;
                    case 20://视频监管
                        VideoActivity.start(getActivity());
                        break;
                    case 21://互联互通
                        PigOnlineActivity.start(getActivity());
                        break;
                    case 22://逢年过节
                        PorkFragrantActivity.start(getActivity());
                        break;
                    case 23://特别品种
                        OldFashionedActivity.start(getActivity());
                        break;
                    case 24://溯源中国
                        TraceabilityActivity.start(getActivity());
                        break;
                    case 25://国际贸易
                        PigTradeActivity.start(getActivity());
                        break;
                    case 26://教育培训
                        PigTeachActivity.start(getActivity());
                        break;
                    case 27://养猪大赛
                        PigOlympicsActivity.start(getActivity());
                        break;
                    case 28://供销总社
                        PigIndustryActivity.start(getActivity());
                        break;
                    case 29://特别嗜好
                        PigPetActivity.start(getActivity());
                        break;
                    default:
                }
            }
            @Override
            public boolean onItemLongClick(View view, BaseRecyclerViewHolder viewHolder, int position) {
                return false;
            }
        });
        recyclerview_shop = mRootView.findViewById(R.id.recyclerview_shop);
        GridLayoutManager layoutManagerShop = new GridLayoutManager(getActivity(),2);
        recyclerview_shop.setLayoutManager(layoutManagerShop);
        gangTieXianHuoAdapter = new GangTieXianHuoAdapter(R.layout.dianpu_item, gangTieDataList,getActivity(),layoutManagerShop);
        HashMap<String, Integer> stringIntegerHashMapShop = new HashMap<>();
        stringIntegerHashMapShop.put(GridSpacingItemDecoration.TOP_DECORATION,20);//top间距
        stringIntegerHashMapShop.put(GridSpacingItemDecoration.BOTTOM_DECORATION,20);//底部间距
        stringIntegerHashMapShop.put(GridSpacingItemDecoration.LEFT_DECORATION,20);//左间距
        stringIntegerHashMapShop.put(GridSpacingItemDecoration.RIGHT_DECORATION,20);//右间距
        recyclerview_shop.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMapShop));
        recyclerview_shop.setAdapter(gangTieXianHuoAdapter);
        gangTieXianHuoAdapter.refreshDataList(gangTieDataList);


        recyclerview_futures = mRootView.findViewById(R.id.recyclerview_futures);

        GridLayoutManager layoutManagerFutures = new GridLayoutManager(getActivity(),2);
        recyclerview_futures.setLayoutManager(layoutManagerFutures);
        gangTieXianHuoAdapter = new GangTieXianHuoAdapter(R.layout.dianpu_item, gangTieDataList,getActivity(),layoutManagerFutures);
        HashMap<String, Integer> stringIntegerHashMapFutures = new HashMap<>();
        stringIntegerHashMapFutures.put(GridSpacingItemDecoration.TOP_DECORATION,20);//top间距
        stringIntegerHashMapFutures.put(GridSpacingItemDecoration.BOTTOM_DECORATION,20);//底部间距
        stringIntegerHashMapFutures.put(GridSpacingItemDecoration.LEFT_DECORATION,20);//左间距
        stringIntegerHashMapFutures.put(GridSpacingItemDecoration.RIGHT_DECORATION,20);//右间距
        recyclerview_futures.addItemDecoration(new GridSpacingItemDecoration(stringIntegerHashMapFutures));
        recyclerview_futures.setAdapter(gangTieXianHuoAdapter);
        gangTieXianHuoAdapter.refreshDataList(gangTieDataList);
    }

    @Override
    protected void initData() {
        super.initData();
        initBanner();
    }

    /**
     * 保险服务
     * 期货服务
     * 信托服务
     * 基金服务
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.loan_ll://贷款服务
                CreditActivity.start(getActivity(),1);
                break;
            case R.id.insurance_ll://保险服务
                InsuranceActivity.start(getActivity(),1);
                break;
            case R.id.futures_ll://期货服务
                FuturesActivity.start(getActivity(),1);
                break;
            case R.id.trust_ll://信托服务
                TrustActivity.start(getActivity(),1);
                break;
            case R.id.fund_ll://基金服务
                FundActivity.start(getActivity(),1);
                break;
            default:
        }
    }

    private void addData(){
        list = new ArrayList<>();
        list.clear();
        recordDataList = new ArrayList<>();

        String [] strings = {"会员服务","金融服务"
               ,"活体交易","产品交易","生猪饲喂","大宗贸易","生产资料","动保产品","免疫服务","防疫消毒","检疫申报","养殖咨询","猪场建设",
                "人员招聘","合作招募","土地信息","运输运力","环保测评","人工智能","软件定制","视频监管","互联互通","逢年过节",
                "特别品种","溯源中国","国际贸易","教育培训","养猪大赛","供销总社","特别嗜好"
        };
        for (String string : strings) {
            RecordData recordData = new RecordData(-1,null);
            recordData.setTitle(string);
            recordDataList.add(recordData);
        }

        gangTieDataList = new ArrayList<>();
        gangTieDataList.clear();

        GangTieData gangTieData = new GangTieData();
        gangTieData.setName("宁夏养猪场");
        gangTieData.setGoNum("本周出栏800头");
        gangTieData.setMoney("19.7/斤");
        gangTieDataList.add(gangTieData);

        GangTieData gangTieData1 = new GangTieData();
        gangTieData1.setName("陕西养牛场");
        gangTieData1.setGoNum("本周出栏800头");
        gangTieData1.setMoney("19.7/斤");
        gangTieDataList.add(gangTieData1);

        GangTieData gangTieData2 = new GangTieData();
        gangTieData2.setName("四川养羊场");
        gangTieData2.setGoNum("本周出栏800头");
        gangTieData2.setMoney("18.7/斤");
        gangTieDataList.add(gangTieData2);

        GangTieData gangTieData3 = new GangTieData();
        gangTieData3.setName("北京养猪场");
        gangTieData3.setGoNum("本周出栏800头");
        gangTieData3.setMoney("19.7/斤");
        gangTieDataList.add(gangTieData3);

        GangTieData gangTieData4 = new GangTieData();
        gangTieData4.setName("北京养猪场");
        gangTieData4.setGoNum("本周出栏800头");
        gangTieData4.setMoney("19.7/斤");
        gangTieDataList.add(gangTieData4);

    }
    private void initBanner() {
        banner.setAdapter(new BannerImageAdapter<BannerData>(BannerData.getData()) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerData data, int position, int size) {
                //图片加载自己实现
                RoundedCorners roundedCorners = new RoundedCorners(40);
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(holder.itemView).load(data.imageRes).apply(options).into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()))
                .setBannerRound(20);

        //更多使用方法仔细阅读文档，或者查看demo
    }


    /**
     * Banner 点击
     * @param data
     * @param position
     */
    @Override
    public void OnBannerClick(Object data, int position) {

    }
}
