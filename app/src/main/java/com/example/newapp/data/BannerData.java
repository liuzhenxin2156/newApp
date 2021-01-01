package com.example.newapp.data;

import com.example.newapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 56454
 */
public class BannerData {

    public BannerData(int imageRes) {
        this.imageRes = imageRes;
    }

    public int imageRes;

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }


    public static List<BannerData> getData() {
        List<BannerData> list = new ArrayList<>();
        list.add(new BannerData(R.drawable.pig_1));
        list.add(new BannerData(R.drawable.pig_2));
        list.add(new BannerData(R.drawable.pig_3));
        list.add(new BannerData(R.drawable.pig_4));
        list.add(new BannerData(R.drawable.pig_5));
        return list;
    }

}
