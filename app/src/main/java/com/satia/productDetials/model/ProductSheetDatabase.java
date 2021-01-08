package com.satia.productDetials.model;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.satia.productDetials.interfaces.ProdReelDIo;
import com.satia.productDetials.interfaces.ProductSheetDio;

@Database(entities = { ProductSheetDatabaseModel.class }, version = 1,exportSchema = false)
public abstract class ProductSheetDatabase extends RoomDatabase {
    private static final String DB_NAME="productsSheetdetail_db";
    private static ProductSheetDatabase instance;

    public static synchronized ProductSheetDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ProductSheetDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract ProductSheetDio prodSheetDIo();

}

