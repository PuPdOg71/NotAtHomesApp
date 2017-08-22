package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cohen on 31/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "App_database.db";
    public static final String TABLE_NAME = "App_date_table";
    public static final String MAP_NO = "Map_no";
    public static final String LOCATION = "Location";
    public static final String DATE = "Date";
    public static final String NOTATHOMES = "Not_at_homes";
    public static final String ID = "ID";
    public static final String TAG = "DBHelper";

    private static DBHelper instance = null;
    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance =  new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + MAP_NO + " INTEGER, " + LOCATION + " TEXT, " + DATE + " INTEGER, " + NOTATHOMES + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String Map_no, String Location, String Date, String Not_at_homes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAP_NO, Map_no);
        contentValues.put(LOCATION, Location);
        contentValues.put(DATE, Date);
        contentValues.put(NOTATHOMES, Not_at_homes);

        Log.d(TAG, "addData: Adding " + Location + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String map, String location, String date, String notAtHomes) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ID + " FROM " + TABLE_NAME +
                " WHERE " + MAP_NO + " = '" + map + "' AND " + LOCATION + " = '" + location +
                "' AND " + DATE + " = '" + date + "' AND "
                + NOTATHOMES + " = '" + notAtHomes + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateData(int id,String newMapNo, String oldMapNo, String newLocation, String oldLocation, String newDate, String oldDate, String newNotAtHomes, String oldNotAtHomes){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + MAP_NO +
                " = '" + newMapNo + " WHERE " + ID + " = '" + id + "'" +
                " AND " + MAP_NO + " = '" + oldMapNo + "'"+ LOCATION +
                " = '" + newLocation + " WHERE " + ID + " = '" + id + "'" +
                " AND " + LOCATION + " = '" + oldLocation + "'"+ DATE +
                " = '" + newDate + " WHERE " + ID + " = '" + id + "'" +
                " AND " + DATE + " = '" + oldDate + "'"+ NOTATHOMES +
                " = '" + newNotAtHomes + " WHERE " + ID + " = '" + id + "'" +
                " AND " + NOTATHOMES + " = '" + oldNotAtHomes + "'";
        Log.d(TAG, "updating: query: " + query);
        Log.d(TAG, "updating: Setting map number, Location, Date and Not at homes to new values: Map number: " +
                newMapNo + " Location: " + newLocation + " Date: " +
                newDate + " Not at homes: " + newNotAtHomes);
        db.execSQL(query);
    }

    public void deleteData(int id, String mapNo, String location, String date, String notAtHomes){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + ID + " + '" + id + "'" + " AND " + MAP_NO + " = '" + mapNo + "'" +
                LOCATION + " = '" + location + "'" + DATE + " = '" + date + "'" + NOTATHOMES +
                " = '" + notAtHomes + "'";
        Log.d(TAG, "deleting: query: " + query);
        Log.d(TAG, "deleting: Deleting note with values: Map number: " + mapNo + " Location: " + location + " Created on: " + date + " With notes: " + notAtHomes + ".");
        database.execSQL(query);

    }
}







