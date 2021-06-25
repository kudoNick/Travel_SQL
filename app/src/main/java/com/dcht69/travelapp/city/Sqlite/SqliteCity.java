package com.dcht69.travelapp.city.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dcht69.travelapp.city.Moder.City;

import java.util.ArrayList;
import java.util.List;

public class SqliteCity extends SQLiteOpenHelper {


    public SqliteCity(Context context){
        super(context,"city.db",null,1);
    }


    public static final String TABLE_City = "City";
    public static final String Col_ID = "ID";
    public static final String Col_NameCity = "name";
    public static final String Col_Image = "imgae";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " +TABLE_City +"("
                                              +Col_ID +" VARCHAR PRIMARY KEY ,"
                                              +Col_NameCity+" VARCHAR ,"
                                              +Col_Image+" VARCHAR )";
        Log.e("Tao bang",create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertCity(City city){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_NameCity,city.getNameCity());
        contentValues.put(Col_ID,city.getId());
        contentValues.put(Col_Image,city.getImgCity());


        long result = sqLiteDatabase.insert(TABLE_City,null,contentValues);
        return  result;
    }

    public long delCity(City city){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        long resutl = sqLiteDatabase.delete(TABLE_City, Col_ID+"=?",new String[]{city.getId()});

        sqLiteDatabase.close();
        return resutl;
    }

//    public long updateCity(City city){
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(Col_ID,city.getId());
////        contentValues.put(Col_NameCity,city.getName());
////        contentValues.put(Col_Image,city.getImage());
//
//
//        long resutl = sqLiteDatabase.update(TABLE_City,contentValues,Col_NameCity + "=?",new String[]{city.getName()});
//
//        sqLiteDatabase.close();
//        return resutl;
//
//    }

    public List<City> getAllCity(){
        List<City> cityList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String truyVan = "SELECT * FROM " + TABLE_City;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan,null);

        Log.d("Size", cursor.getCount() +"");
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                City city = new City();

                city.setId(cursor.getString(cursor.getColumnIndex(Col_ID)));
                city.setNameCity(cursor.getString(cursor.getColumnIndex(Col_NameCity)));
                city.setImgCity(cursor.getString(cursor.getColumnIndex(Col_Image)));

                cityList.add(city);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return  cityList;
    }
}
