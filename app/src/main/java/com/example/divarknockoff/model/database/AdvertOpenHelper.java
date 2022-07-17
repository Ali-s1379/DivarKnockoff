package com.example.divarknockoff.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import static com.example.divarknockoff.model.database.AdvertDBSchema.NAME;

public class AdvertOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public AdvertOpenHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + AdvertDBSchema.Advert.NAME + "(" +
                AdvertDBSchema.Advert.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AdvertDBSchema.Advert.Cols.UUID + ", " +
                AdvertDBSchema.Advert.Cols.TITLE + ", " +
                AdvertDBSchema.Advert.Cols.DESCRIPTION + ", " +
                AdvertDBSchema.Advert.Cols.CITY + ", " +
                AdvertDBSchema.Advert.Cols.PHONE + ", " +
                AdvertDBSchema.Advert.Cols.VIP +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
