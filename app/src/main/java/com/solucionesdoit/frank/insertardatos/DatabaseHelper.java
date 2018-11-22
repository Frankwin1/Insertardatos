package com.solucionesdoit.frank.insertardatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Clientes.db";
    public static final String TABLE_NAME = "CLI_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "DISTRITO";
    public static final String COL_4 = "TELEFONO";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DISTRITO TEXT," + "TELEFONO INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String nombre,String distrito,String telefono){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,distrito);
        contentValues.put(COL_4,telefono);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from " + TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String nombre,String distrito,String telefono){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,distrito);
        contentValues.put(COL_4,telefono);
        db.update(TABLE_NAME, contentValues,"ID = ?",new String[]{id});
        return true;
    }
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[]{id});
    }

}
