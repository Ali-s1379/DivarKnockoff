package com.example.divarknockoff;

import android.content.Context;

import com.example.divarknockoff.model.DaoMaster;


public class AdOpenHelper extends DaoMaster.OpenHelper {
    private static final String NAME = "advert.db";

    public AdOpenHelper(Context context) {
        super(context, NAME);
    }
}
