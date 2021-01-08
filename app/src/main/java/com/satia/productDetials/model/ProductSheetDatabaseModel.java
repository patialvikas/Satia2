package com.satia.productDetials.model;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_sheet")
public class ProductSheetDatabaseModel {

    @PrimaryKey(autoGenerate = true)
    private int auto_id;

    @ColumnInfo(name = "product_id") // column name will be "note_content" instead of "content" in table
    private int product_id;

    @ColumnInfo(name = "Type") // column name will be "note_content" instead of "content" in table
    private String type;

    @ColumnInfo(name = "Quality") // column name will be "note_content" instead of "content" in table
    private String quailty;

    @ColumnInfo(name = "lot_number") // column name will be "note_content" instead of "content" in table
    private String lot_number;

    @ColumnInfo(name = "GSM") // column name will be "note_content" instead of "content" in table
    private String GSM;

    @ColumnInfo(name = "Size") // column name will be "note_content" instead of "content" in table
    private String Size;

    @ColumnInfo(name = "noofsheet") // column name will be "note_content" instead of "content" in table
    private String no_of_sheet;

    @ColumnInfo(name = "reem_unit") // column name will be "note_content" instead of "content" in table
    private String reem_unit;

    @ColumnInfo(name = "pallet_unit") // column name will be "note_content" instead of "content" in table
    private String pallet_unit;

    @ColumnInfo(name = "reem_weight") // column name will be "note_content" instead of "content" in table
    private String reem_weight;

    @ColumnInfo(name = "pallet_weight") // column name will be "note_content" instead of "content" in table
    private String pallet_weight;

    @ColumnInfo(name = "status") // column name will be "note_content" instead of "content" in table
    private String status;

    @ColumnInfo(name = "product_type") // column name will be "note_content" instead of "content" in table
    private String product_type;

    public ProductSheetDatabaseModel(int auto_id,int product_id,String type,String quailty, String lot_number, String GSM,
                                     String Size, String no_of_sheet, String reem_weight,String reem_unit,
                                     String pallet_unit,String pallet_weight, String status,String product_type){
        this.product_id=product_id;this.type=type;this.quailty=quailty;this.reem_unit=reem_unit;this.pallet_weight=pallet_weight;
        this.lot_number=lot_number;this.GSM=GSM;this.Size=Size;this.no_of_sheet=no_of_sheet;this.reem_unit=reem_unit;this.reem_weight=reem_weight;
        this.status=status;this.pallet_unit=pallet_unit;this.product_type=product_type;
    }
    @Ignore
    public ProductSheetDatabaseModel(int product_id,String type,String quailty, String lot_number, String GSM,
                                    String Size, String no_of_sheet, String reem_weight,String reem_unit,
                                     String pallet_unit,String pallet_weight, String status,String product_type){
        this.product_id=product_id;this.type=type;this.quailty=quailty;this.reem_unit=reem_unit;this.pallet_weight=pallet_weight;
        this.lot_number=lot_number;this.GSM=GSM;this.Size=Size;this.no_of_sheet=no_of_sheet;this.reem_unit=reem_unit;this.reem_weight=reem_weight;
        this.status=status;this.pallet_unit=pallet_unit;this.product_type=product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getPallet_unit() {
        return pallet_unit;
    }

    public String getPallet_weight() {
        return pallet_weight;
    }

    public String getStatus() {
        return status;
    }

    public void setPallet_weight(String pallet_weight) {
        this.pallet_weight = pallet_weight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoOfUnitPallets() {
        return pallet_unit;
    }

    public String getNo_of_sheet() {
        return no_of_sheet;
    }

    public String getType() {
        return type;
    }

    public void setNo_of_sheet(String no_of_sheet) {
        this.no_of_sheet = no_of_sheet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPallet_unit(String pallet_unit) {
        this.pallet_unit = pallet_unit;
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



    public String getReem_weight() {
        return reem_weight;
    }



    public String getQuailty() {
        return quailty;
    }

    public String getReem_unit() {
        return reem_unit;
    }

    public String getSize() {
        return Size;
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





    public void setReem_weight(String net_weight) {
        this.reem_weight = reem_weight;
    }



    public void setSize(String size) {
        Size = size;
    }




    public void setReem_unit(String reem_unit) {
        this.reem_unit = reem_unit;
    }

    public void setQuailty(String quailty) {
        this.quailty = quailty;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }



}
