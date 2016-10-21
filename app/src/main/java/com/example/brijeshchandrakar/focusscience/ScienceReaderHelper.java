package com.example.brijeshchandrakar.focusscience;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by brijesh.chandrakar on 18/10/16.
 */

public class ScienceReaderHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Science";
    public static final String SATUS_TABLE_NAME = "Articles";

    public ScienceReaderHelper(MainActivity context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Articles " +
                        "(Category text, Title text, Abstract text, Url text, ID integer, Date text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getDataCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor res = db.rawQuery("select Category from Articles where ID=?", selectionArgs, null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getDataTitle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor res = db.rawQuery("select Title from Articles where ID=?", selectionArgs, null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getDataDate(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor res = db.rawQuery("select Date from Articles where ID=?", selectionArgs, null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getDataAbstract(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor res = db.rawQuery("select Abstract from Articles where ID=?", selectionArgs, null);
        return res;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getDataUrl(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(id)};
        Cursor res = db.rawQuery("select Url from Articles where ID=?", selectionArgs, null);
        return res;
    }
}
