package com.ningso.volleydemo.entity;

/**
 * Created by NingPingPing on 2015/6/9.
 */
public class TeachBean {

    /** http://www.imooc.com/api/teacher?type=4&num=10
     * picBig : http://img.mukewang.com/5518c3d7000175af06000338.jpg
     * learner : 15051
     * name : 细说Java多线程之内存可见性
     * description : 用两种方式实现内存可见性
     * id : 8
     * picSmall : http://img.mukewang.com/5518c3d7000175af06000338-300-170.jpg
     */
    private String picBig;
    private int learner;
    private String name;
    private String description;
    private int id;
    private String picSmall;

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public void setLearner(int learner) {
        this.learner = learner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public int getLearner() {
        return learner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getPicSmall() {
        return picSmall;
    }
}
