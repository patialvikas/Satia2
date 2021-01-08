package com.satia.productDetials.model;

public class OnlyshowReelModel {
    String user_name;
    String quality;


    String gsm;
    String lot_no;
    String size;

    String real_no;String net_weigth;

    public OnlyshowReelModel( String user_name,String gsm, String lot_no, String size,String real_no,String net_weigth,String quality){


        this.gsm=gsm;this.lot_no=lot_no;this.real_no=real_no;this.user_name=user_name;this.size=size;this.net_weigth=net_weigth;
        this.quality=quality;

    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }

    public String getNet_weigth() {
        return net_weigth;
    }

    public void setNet_weigth(String net_weigth) {
        this.net_weigth = net_weigth;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setReal_no(String real_no) {
        this.real_no = real_no;
    }

    public String getReal_no() {
        return real_no;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



    public String getGsm() {
        return gsm;
    }

    public String getLot_no() {
        return lot_no;
    }



    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setLot_no(String lot_no) {
        this.lot_no = lot_no;
    }



}

