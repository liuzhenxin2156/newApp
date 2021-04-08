package com.example.newapp.data;

import java.io.Serializable;

/**
 * @ProjectName : NewApp
 * @Author :
 * @Time : 2021/4/7 9:31
 * @Description :
 */
public class ScanData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String Name;

    private int Order;

    private String Id;

    private int GroupId;

    private int ImageViewID;

    public int getImageViewID() {
        return ImageViewID;
    }

    public void setImageViewID(int imageViewID) {
        ImageViewID = imageViewID;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return this.Name;
    }

    public void setOrder(int Order) {
        this.Order = Order;
    }

    public int getOrder() {
        return this.Order;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Scanncer [Name=" + Name + ", Order=" + Order + ", Id=" + Id
                + ", GroupId=" + GroupId + "]";
    }
}
