package com.satia.productDetials.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.satia.productDetials.interfaces.ProdReelDIo;

@Database(entities = { ProductReelDatabaseModel.class }, version = 1,exportSchema = false)
public abstract class ProductReelDatabase extends RoomDatabase {
    private static final String DB_NAME="productsReel_db";
    private static ProductReelDatabase instance;

    public static synchronized ProductReelDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ProductReelDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
public abstract ProdReelDIo prodReelDIo();

}
