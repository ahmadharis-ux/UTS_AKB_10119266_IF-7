package net.android.ahmadharis10119266;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "catatan.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Catatan(tanggal date, judul TEXT primary key, kategori TEXT, catatan TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Catatan");
    }
    public Boolean insertuserdata(String tanggal, String judul, String kategori, String catatan){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tanggal", tanggal);
        contentValues.put("judul", judul);
        contentValues.put("kategori", kategori);
        contentValues.put("catatan", catatan);
        long result=DB.insert("catatan", null,contentValues);
        if (result==1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Catatan",null);
        return cursor;
    }
}
