package com.satia.productDetials.model;



import java.io.Serializable;

public class OnlyshowModel {
   // int product_id;
   // String type;
   // String product_type;
    String type;
    String quality;


    String gsm;
    String lot_no;
    String size;

    String weight;String pallet_weight;

    public OnlyshowModel( String type,String gsm, String lot_no, String size,String weight,String pallet_weight,String quality){


        this.gsm=gsm;this.lot_no=lot_no;this.weight=weight;this.type=type;this.size=size;this.pallet_weight=pallet_weight;
        this.quality=quality;

    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }

    public String getPallet_weight() {
        return pallet_weight;
    }

    public void setPallet_weight(String pallet_weight) {
        this.pallet_weight = pallet_weight;
    }



    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
