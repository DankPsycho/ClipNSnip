package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservation.db";
    private static final int DATABASE_VERSION = 1;
    // Table name and column names
    private static final String TABLE_RESERVATION = "reservations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_SERVICES = "services";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the reservations table
        String createTableQuery = "CREATE TABLE " + TABLE_RESERVATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_SERVICES + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        onCreate(db);
    }

    public void addReservation(SQLiteDatabase db, String date, String time, String services) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_SERVICES, services);
        db.insert(TABLE_RESERVATION, null, values);
    }
}
