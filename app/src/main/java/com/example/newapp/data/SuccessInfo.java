package com.example.newapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/8 9:40
 * @Description :
 */
public class SuccessInfo implements Serializable {
    public String PigName;
    public  String LinecsNum;
    public  String Address;
    public String PersonName;
    public String IDCard;
    public String Phone;
    public String DayAge;
    public String EarTags;
    public String IDCardPhotoPositive;
    public String IDCardPhotoReverse;
    public String  BankPhoto;
    public List<String> MeansPhotos;



    @Override
    public String toString() {
        return "SuccessInfo{" +
                "PigName='" + PigName + '\'' +
                ", LinecsNum='" + LinecsNum + '\'' +
                ", Address='" + Address + '\'' +
                ", PersonName='" + PersonName + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", Phone='" + Phone + '\'' +
                ", DayAge='" + DayAge + '\'' +
                ", EarTags='" + EarTags + '\'' +
                ", IDCardPhotoPositive='" + IDCardPhotoPositive + '\'' +
                ", IDCardPhotoReverse='" + IDCardPhotoReverse + '\'' +
                ", BankPhoto='" + BankPhoto + '\'' +
                ", MeansPhotos=" + MeansPhotos +
                '}';
    }


}
