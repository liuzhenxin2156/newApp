package com.example.newapp.data;

public class GangTieData {

    private String name;
    private String goNum;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoNum() {
        return goNum;
    }

    public void setGoNum(String goNum) {
        this.goNum = goNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "GangTieData{" +
                "name='" + name + '\'' +
                ", goNum='" + goNum + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
