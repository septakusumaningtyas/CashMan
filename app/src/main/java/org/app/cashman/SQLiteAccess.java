package org.app.cashman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static SQLiteAccess instance;
    Cursor c = null;

    private SQLiteAccess(Context context){
        this.openHelper = new SQLiteHelper(context);
    }

    public static SQLiteAccess getInstance(Context context){
        if(instance == null){
            instance = new SQLiteAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    public Integer Delete(String table, String where, String value){
        return db.delete(table, where, new String[]{value});
    }

    public Cursor DeleteAll(String table){
        return db.rawQuery("DELETE FROM " + table, null);
    }

    public boolean DeleteWhere(String table, String where){
        return db.delete(table, where, null) > 0;
    }

    public Cursor DistinctWhere(String table, String where){
        return db.rawQuery("SELECT DISTINCT orderedDate FROM " + table + " WHERE " + where, null);
    }

    public Cursor Get(String table){
        return db.rawQuery("SELECT * FROM " + table, null);
    }

    public Cursor Sum(String field, String table, String where){
        return db.rawQuery("SELECT SUM(" + field +") AS result FROM " + table + " WHERE " + where, null);
    }

    public Cursor SumGroup(String field, String table){
        return db.rawQuery("SELECT SUM(" + field +") AS result, createddate FROM " + table + " GROUP BY createddate", null);
    }

    public Cursor Where(String table, String where){
        return db.rawQuery("SELECT * FROM " + table + " WHERE " + where, null);
    }

    public boolean insertUser(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("user", null, contentValues);
        return result != -1;
    }

    public boolean updateUser(String password, String username){
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = db.update("user",  contentValues, "username=?", new String[]{username});
        return result != -1;
    }

    public boolean insertCash(Integer jumlah, String keterangan, String tgl, String arrow){
        ContentValues contentValues = new ContentValues();
        contentValues.put("jumlah", jumlah);
        contentValues.put("keterangan", keterangan);
        contentValues.put("tgl", tgl);
        contentValues.put("arrow", arrow);
        long result = db.insert("cash", null, contentValues);
        return result != -1;
    }
}
