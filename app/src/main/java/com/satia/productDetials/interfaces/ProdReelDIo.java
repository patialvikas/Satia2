package com.satia.productDetials.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.satia.productDetials.model.ProductReelDatabaseModel;

import java.util.List;

@Dao
public interface ProdReelDIo {
    @Query("Select * from product_reel")
    List<ProductReelDatabaseModel> getAll();
    @Insert
    void insertReelData(ProductReelDatabaseModel productReelDatabaseModel);
    @Delete
    void deleteReelData(ProductReelDatabaseModel productReelDatabaseModel);
    @Update
    void updateReelData(ProductReelDatabaseModel productReelDatabaseModel);

    @Query("DELETE FROM product_reel")
    public void nukeTable();


}
