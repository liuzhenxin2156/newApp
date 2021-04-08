package com.example.newapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 10:29
 * @Description :
 */
public class EarTag implements Serializable {
    private String eartagno;


    private Integer eartagId;

    public String getEartagno() {
        return eartagno;
    }

    public void setEartagno(String eartagno) {
        this.eartagno = eartagno;
    }

    public Integer getEartagId() {
        return eartagId;
    }

    public void setEartagId(Integer eartagId) {
        this.eartagId = eartagId;
    }

    @Override
    public String toString() {
        return "EarTag{" +
                "eartagno='" + eartagno + '\'' +
                ", eartagId=" + eartagId +
                '}';
    }
}
