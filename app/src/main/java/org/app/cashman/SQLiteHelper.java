package org.app.cashman;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SQLiteHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "cashman.db";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_LOCATION = "/data/data/" + G.context.getPackageName() + "/databases";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
