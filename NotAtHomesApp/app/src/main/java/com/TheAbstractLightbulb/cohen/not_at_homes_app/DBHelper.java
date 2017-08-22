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
    public static final String TABLE_NAME = "App_data_table";
    public static final String REF_ID = "Ref_id";
    public static final String MAP_NO = "Map_no";
    public static final String LOCATION = "Location";
    public static final String DATE = "Date";
    public static final String NOTATHOMES = "Not_at_homes";
    public static final String _id = "_id";
    public static final String TAG = "DBHelper";
    private static DBHelper instance = null;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String createTable = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +REF_ID + " TEXT, " + MAP_NO + " INTEGER, " + LOCATION + " TEXT, " + DATE + " INTEGER, " + NOTATHOMES + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String Ref_id, String Map_no, String Location, String Date, String Not_at_homes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REF_ID, Ref_id);
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

//getting data
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public void updateData(int id, String newMapNo, String newLocation, String newDate, String newNotAtHomes){
        SQLiteDatabase db = this.getWritableDatabase();
        String id2 = String.valueOf(id);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAP_NO, newMapNo);
        contentValues.put(LOCATION, newLocation);
        contentValues.put(DATE, newDate);
        contentValues.put(NOTATHOMES, newNotAtHomes);
        db.update(TABLE_NAME, contentValues,"'WHERE _id = " + id2 +"'", null);

    }

    public int deleteData(int id){
        String ID = String.valueOf(id);
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, _id + " = ?",new String[]{ID});

    }

}