package com.dcht69.travelapp.DetailLocation.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;

import java.util.ArrayList;
import java.util.List;

public class SqliteDetailLocation extends SQLiteOpenHelper {

    public SqliteDetailLocation(Context context){
        super(context,"location.db",null,1);
    }

    public static final String TABLE_Location = "location";

    public static final String Col_ID = "ID";
    public static final String Col_IDCity = "IDCity";
    public static final String Col_NamePlace = "namePlace";
    public static final String Col_ImgPlace = "imgPlace";
    public static final String Col_Img1 = "img1";
    public static final String Col_TextImg1 = "textImg1";
    public static final String Col_Img2 = "img2";
    public static final String Col_TextImg2 = "textImg2";
    public static final String Col_Comment1 = "comment1";
    public static final String Col_Comment2 = "comment2";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " +TABLE_Location +"("
                +Col_ID +" VARCHAR PRIMARY KEY ,"
                +Col_IDCity+" VARCHAR ,"
                +Col_NamePlace+" VARCHAR ,"
                +Col_ImgPlace+" VARCHAR ,"
                +Col_Img1+" VARCHAR ,"
                +Col_TextImg1+" VARCHAR ,"
                +Col_Img2+" VARCHAR ,"
                +Col_TextImg2+" VARCHAR ,"
                +Col_Comment1+" VARCHAR ,"
                +Col_Comment2+" VARCHAR )";

        Log.e("Tao bang",create_table);
        db.execSQL(create_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertLocation(LocationPlace location){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_ID,location.getId());
        contentValues.put(Col_IDCity,location.getIdCity());
        contentValues.put(Col_NamePlace,location.getNamePlace());
        contentValues.put(Col_ImgPlace,location.getImgPlace());
        contentValues.put(Col_Img1,location.getImg1());
        contentValues.put(Col_TextImg1,location.getTextImg1());
        contentValues.put(Col_Img2,location.getImg2());
        contentValues.put(Col_TextImg2,location.getTextImg2());
        contentValues.put(Col_Comment1,location.getComment1());
        contentValues.put(Col_Comment2,location.getComment2());


        long result = sqLiteDatabase.insert(TABLE_Location,null,contentValues);
        sqLiteDatabase.close();
        return  result;
    }

    public long updateLocation(LocationPlace location){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(Col_ID,location.getId());
        contentValues.put(Col_IDCity,location.getIdCity());
        contentValues.put(Col_NamePlace,location.getNamePlace());
        contentValues.put(Col_ImgPlace,location.getImgPlace());
        contentValues.put(Col_Img1,location.getImg1());
        contentValues.put(Col_TextImg1,location.getTextImg1());
        contentValues.put(Col_Img2,location.getImg2());
        contentValues.put(Col_TextImg2,location.getTextImg2());
        contentValues.put(Col_Comment1,location.getComment1());
        contentValues.put(Col_Comment2,location.getComment2());


        long resutl = sqLiteDatabase.update(TABLE_Location,contentValues,Col_ID + "=?",new String[]{location.getId()});

        sqLiteDatabase.close();
        return resutl;

    }
    public long delLocation(LocationPlace location){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        long resutl = sqLiteDatabase.delete(TABLE_Location, Col_ID+"=?",new String[]{location.getId()});

        sqLiteDatabase.close();
        return resutl;
    }

    public List<LocationPlace> getAllLocationByIdCity(String idCity){
        List<LocationPlace> locationList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String truyVan = "SELECT * FROM " + TABLE_Location + " Where " +Col_IDCity +" LIKE " + idCity ;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan,null);

        Log.d("Size", cursor.getCount() +"");
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                LocationPlace location = new LocationPlace();

                location.setId(cursor.getString(cursor.getColumnIndex(Col_ID)));
                location.setIdCity(cursor.getString(cursor.getColumnIndex(Col_IDCity)));
                location.setNamePlace(cursor.getString(cursor.getColumnIndex(Col_NamePlace)));
                location.setImgPlace(cursor.getString(cursor.getColumnIndex(Col_ImgPlace)));
                location.setImg1(cursor.getString(cursor.getColumnIndex(Col_Img1)));
                location.setTextImg1(cursor.getString(cursor.getColumnIndex(Col_TextImg1)));
                location.setImg2(cursor.getString(cursor.getColumnIndex(Col_Img2)));
                location.setTextImg2(cursor.getString(cursor.getColumnIndex(Col_TextImg2)));
                location.setComment1(cursor.getString(cursor.getColumnIndex(Col_Comment1)));
                location.setComment2(cursor.getString(cursor.getColumnIndex(Col_Comment2)));

                locationList.add(location);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return  locationList;
    }

    public List<LocationPlace> getAllLocation(){
        List<LocationPlace> locationList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String truyVan = "SELECT * FROM " + TABLE_Location;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan,null);

        Log.d("Size", cursor.getCount() +"");
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                LocationPlace location = new LocationPlace();

                location.setImgPlace(cursor.getString(cursor.getColumnIndex(Col_ImgPlace)));
                location.setNamePlace(cursor.getString(cursor.getColumnIndex(Col_NamePlace)));

                locationList.add(location);
                cursor.moveToNext();
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return  locationList;
    }
}
