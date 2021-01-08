package com.satia.productDetials.model;

import java.io.Serializable;

public class FinalProductModel implements Serializable {
    int product_id;
    String type;
    String product_type;
    String gsm;
    String lot_no;
    String weight;
    String size;
    String quality;
    String pallet_weight;

    public FinalProductModel(int product_id, String type,String product_type, String gsm, String lot_no, String weight,String size,String quality,String pallet_weight){

        this.product_id=product_id;this.type=type;this.product_type=product_type;
        this.gsm=gsm;this.lot_no=lot_no;this.weight=weight;this.size=size;this.quality=quality;this.weight=weight;this.pallet_weight=pallet_weight;
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

    public void setPallet_weight(String pallet_weight) {
        this.pallet_weight = pallet_weight;
    }

    public String getPallet_weight() {
        return pallet_weight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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

    public String getWeight() {
        return weight;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setLot_no(String lot_no) {
        this.lot_no = lot_no;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
