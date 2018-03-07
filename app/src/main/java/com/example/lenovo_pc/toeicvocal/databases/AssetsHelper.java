package com.example.lenovo_pc.toeicvocal.databases;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Lenovo-PC on 3/1/2018.
 */

public class AssetsHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "toeic_600.db";
    private static final int DATABASE_VERSION = 1;

    public AssetsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
