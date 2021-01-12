package com.example.newapp.utils;

import android.content.DialogInterface;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class TimerDelay {
    /**
     * 延迟消失300毫秒
     * @param dialog
     */
    public static  void  setDelay(DialogInterface dialog){
        Observable.timer(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    dialog.dismiss();
                });
    }
}
