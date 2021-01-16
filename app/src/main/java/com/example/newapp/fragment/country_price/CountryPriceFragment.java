package com.example.newapp.fragment.country_price;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.base.BaseFragment;
import com.example.newapp.base.BasePresenter;
import com.example.newapp.fragment.main_breed_pig.HomeBreedPigFragment;
import com.example.newapp.utils.AppConstants;
import com.example.newapp.utils.LogUtil;

public class CountryPriceFragment extends BaseFragment {
    private WebView webView;
    private ProgressBar progressBar;

    public static CountryPriceFragment newInstance() {
        CountryPriceFragment fragment = new CountryPriceFragment();
        return fragment;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.frgment_country_price;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        webView = mRootView.findViewById(R.id.webview);
        progressBar = mRootView.findViewById(R.id.progressBar);
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(true); //垂直不显示
    }

    @Override
    protected void initData() {
        super.initData();
        this.webView.setWebViewClient(new WebViewClient() {
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

        this.webView.loadUrl(AppConstants.COUNTY_PRICE);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                LogUtil.d("lzx----》", "title" + title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
    }
}
