package com.example.newapp.data;

import androidx.annotation.DrawableRes;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

public class RecordData extends BaseNode {


    public RecordData( @DrawableRes int img,String title) {
        this.title = title;
        this.img = img;
    }

    private String  title;
    private int img;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DrawableRes
    public int getImg() {
        return img;
    }

    public void setImg(@DrawableRes int img) {
        this.img = img;
    }
    @Override
    public String toString() {
        return "RecordData{" +
                "title='" + title + '\'' +
                '}';
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
