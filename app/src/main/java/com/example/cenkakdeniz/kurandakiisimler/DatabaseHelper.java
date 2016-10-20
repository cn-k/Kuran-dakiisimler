package com.example.cenkakdeniz.kurandakiisimler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by cenk.akdeniz on 06.04.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "isimler.db";
    public static final String TABLE_NAME_ISIM = "ISIMLER";
    public static final String COL1_SERT = "ID";
    public static final String COL2_ISIM = "AD";
    public static final String COL3_ISIM = "ANLAMI";

      SQLiteDatabase db;
      Cursor cursor;

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // NOTES Table - column nmaes
    private static final String KEY_TODO = "todo";
    private static final String KEY_STATUS = "status";

    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";
    public static final String CREATE_ISIMLER_TABLE = "CREATE TABLE ISIMLER (ID INTEGER PRIMARY KEY AUTOINCREMENT , AD TEXT ," +
            " ANLAMI TEXT)";


    private static DatabaseHelper instance = null;

    long result;

    public static DatabaseHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("OnCreate", "OnCreate invoked");
        db.execSQL(CREATE_ISIMLER_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_ISIM);
        onCreate(db);
        db.close();
    }

    public boolean insertData(String ad, String anlami) {
        try {
            db = this.getWritableDatabase();
        }
        catch (SQLException s){
            new Exception("Error with DB Open");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_ISIM, ad);
        contentValues.put(COL3_ISIM, anlami);
        cursor = db.rawQuery("SELECT * "  + " FROM " + TABLE_NAME_ISIM  + " WHERE " + COL2_ISIM + " = ? ", new String[]{ad});

        if(!cursor.moveToFirst()){
            result = db.insert(TABLE_NAME_ISIM, null, contentValues);
        }
        else if(!(cursor.getString(1).equals(ad))){
            result = db.insert(TABLE_NAME_ISIM, null, contentValues);
        }
        db.close();
        if (getResult() == -1) {
            return false;
        }
        else {
            return true;

        }

    }


    public void deleteDatabase(String name) {
        try {
            db = this.getWritableDatabase();
        }
        catch (SQLException s){
            new Exception("Error with DB Open");
        }
        db.execSQL("DROP TABLE " + name);
        db.close();

    }

    public Cursor getAllData(String query) {
        try {
            db = this.getWritableDatabase();
        }
        catch (SQLException s){
            new Exception("Error with DB Open");
        }
         cursor = db.rawQuery(query , null);
        return cursor;
    }

    public void deleteData(String id, String tableName) {
        try {
            db = this.getWritableDatabase();
        }
        catch (SQLException s){
            new Exception("Error with DB Open");
        }
        db.execSQL("delete * from " + tableName + " where language ='" + id + "'");
        db.close();
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }
}
