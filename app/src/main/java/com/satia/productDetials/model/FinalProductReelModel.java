package com.satia.productDetials.model;

import java.io.Serializable;

public class FinalProductReelModel implements Serializable {
    int product_id;
    String user_name;
    String product_type;
    String gsm;
    String lot_no;
    String real_no;
    String size;
    String quality;
    String net_weight;
    private boolean isSelected;

    public FinalProductReelModel(int product_id, String user_name,String product_type, String gsm, String lot_no, String real_no,String size,String quality,String net_weight){

        this.product_id=product_id;this.user_name=user_name;this.product_type=product_type;
        this.gsm=gsm;this.lot_no=lot_no;this.size=size;this.quality=quality;this.real_no=real_no;this.net_weight=net_weight;
    }



    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public String getReal_no() {
        return real_no;
    }

    public void setReal_no(String real_no) {
        this.real_no = real_no;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }



    public void setUser_name(String type) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
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
