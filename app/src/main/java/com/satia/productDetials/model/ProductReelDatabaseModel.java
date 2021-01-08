package com.satia.productDetials.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_reel")
public class ProductReelDatabaseModel {

    @PrimaryKey(autoGenerate = true)
    private int auto_id;

    @ColumnInfo(name = "product_id") // column name will be "note_content" instead of "content" in table
    private int product_id;

    @ColumnInfo(name = "user_name") // column name will be "note_content" instead of "content" in table
    private String user_name;

    @ColumnInfo(name = "machine") // column name will be "note_content" instead of "content" in table
    private String machine;

    @ColumnInfo(name = "product") // column name will be "note_content" instead of "content" in table
    private String product;

    @ColumnInfo(name = "Quality") // column name will be "note_content" instead of "content" in table
    private String quailty;


    @ColumnInfo(name = "lot_number") // column name will be "note_content" instead of "content" in table
    private String lot_number;

    @ColumnInfo(name = "GSM") // column name will be "note_content" instead of "content" in table
    private String GSM;

    @ColumnInfo(name = "Size") // column name will be "note_content" instead of "content" in table
    private String Size;

    @ColumnInfo(name = "real_num") // column name will be "note_content" instead of "content" in table
    private String real_num;

    @ColumnInfo(name = "net_weight") // column name will be "note_content" instead of "content" in table
    private String net_weight;

    @ColumnInfo(name = "manufacture_date") // column name will be "note_content" instead of "content" in table
    private String manufacture_date;

    @ColumnInfo(name = "product_type") // column name will be "note_content" instead of "content" in table
    private String product_type;

   public ProductReelDatabaseModel(int auto_id, int product_id, String user_name, String machine, String product, String quailty, String lot_number, String GSM,
                                    String Size, String real_num, String net_weight, String manufacture_date,String product_type){
        this.auto_id=auto_id;this.product_id=product_id;this.user_name=user_name;this.machine=machine;this.product=product;this.quailty=quailty;
        this.lot_number=lot_number;this.GSM=GSM;this.Size=Size;this.real_num=real_num;this.net_weight=net_weight;
        this.manufacture_date=manufacture_date;this.product_type=product_type;
    }
    @Ignore
    public ProductReelDatabaseModel(int product_id, String user_name, String machine, String product, String quailty, String lot_number, String GSM,
                                    String Size, String real_num, String net_weight, String manufacture_date,String product_type){
        this.product_id=product_id;this.user_name=user_name;this.machine=machine;this.product=product;this.quailty=quailty;
        this.lot_number=lot_number;this.GSM=GSM;this.Size=Size;this.real_num=real_num;this.net_weight=net_weight;this.product_type=product_type;
        this.manufacture_date=manufacture_date;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getGSM() {
        return GSM;
    }

    public String getLot_number() {
        return lot_number;
    }

    public String getMachine() {
        return machine;
    }

    public String getManufacture_date() {
        return manufacture_date;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public String getProduct() {
        return product;
    }

    public String getQuailty() {
        return quailty;
    }

    public String getReal_num() {
        return real_num;
    }

    public String getSize() {
        return Size;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public void setLot_number(String lot_number) {
        this.lot_number = lot_number;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public void setManufacture_date(String manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public void setReal_num(String real_num) {
        this.real_num = real_num;
    }

    public void setQuailty(String quailty) {
        this.quailty = quailty;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }



}
