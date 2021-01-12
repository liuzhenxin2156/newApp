package com.example.newapp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import androidx.multidex.MultiDex;


import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.example.newapp.BuildConfig;
import com.example.newapp.sp.ConfigSP;
import com.example.newapp.utils.AppUtil;
import com.example.newapp.utils.CrashHandler;
import com.example.newapp.utils.LogUtil;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 *
 * 尽量压缩图片，转为webp格式。删除无用资源，减少包的体积
 * on: 2020/4/21
 * Created By Lzx
 * describe:
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks{

    public static Context mContext;
    public IWXAPI api;      //这个对象是专门用来向微信发送数据的一个重要接口,使用强引用持有,所有的信息发送都是基于这个对象的
    private int  mActivityCount; // activity计数器
    //获取消息推送代理示例

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AppUtil.syncIsDebug(this);//是否debug
        initLogger();//初始化日志
        ConfigSP.getInstance().setResourcePath(AppUtil.createAppStorageDir(AppUtil.PATH_APP, this)); //设置App资源路径
        initThirdService();
        // 注册App所有Activity的生命周期回调监听器
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }

    private void initLogger() {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                        : LogLevel.NONE)
                .tag("XLOG")                                         // 指定 TAG，默认为 "X-LOG"
//                .t()                                                   // 允许打印线程信息，默认禁止
//                .st(1)                                                 // 允许打印深度为2的调用栈信息，默认禁止
//                .b()                                                   // 允许打印日志边框，默认禁止
                .build();

        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder(Environment.getExternalStorageDirectory().getPath() + "/ZhuGeLiang/xlog")    // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator() {
                    @Override
                    public String generateFileName(int logLevel, long timestamp) {
                        String time = super.generateFileName(logLevel, timestamp);
                        return time + ".txt";
                    }
                })        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                .build();
        XLog.init(                                                 // 初始化 XLog
                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
                filePrinter);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 开启异步线程去初始化第三仿
     */
    private void initThirdService() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                // 启动CubeService
                //WxShareAndLoginUtils.getWXAPI(mContext);//初始化wx
                CrashHandler.getInstance().init(mContext);//初始化崩溃日志
                /**
                 * 初始化common库
                 * 参数1:上下文，必须的参数，不能为空
                 * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
                 * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
                 * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
                 * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
                 */
//                UMConfigure.init(mContext,UMConfigure.DEVICE_TYPE_PHONE, "");
//                UMConfigure.setLogEnabled(true);//当打包正式时 必须设置为false
            }
        }.start();
    }




    /**
     * 获取上下文
     */
    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        this.mActivityCount++;
        if (0 == this.mActivityCount - 1) {
            LogUtil.i("App 从后台回到前台了" + activity.getLocalClassName());
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        this.mActivityCount--;
        if (0 == this.mActivityCount) {
            LogUtil.i("App 从前台切换到后台了" + activity.getLocalClassName());
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
