package com.satia.productDetials.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//import com.satia.productDetials.model.ProductReelDatabaseModel;

import com.satia.productDetials.model.ProductSheetDatabaseModel;

import java.util.List;

@Dao
public interface ProductSheetDio {
    @Query("Select * from product_sheet")
    List<ProductSheetDatabaseModel> getAll();
    @Insert
    void insertSheetData(ProductSheetDatabaseModel productReelDatabaseModel);
    @Delete
    void deleteSheetData(ProductSheetDatabaseModel productReelDatabaseModel);
    @Update
    void updateSheetData(ProductSheetDatabaseModel productReelDatabaseModel);


    @Query("DELETE FROM product_sheet")
    public void nukeTable();
}
