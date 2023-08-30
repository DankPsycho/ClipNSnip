package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservation.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RESERVATION = "reservations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_SERVICES = "services";
    private static final String COLUMN_TOTAL = "total";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_RESERVATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FULL_NAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_MOBILE + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_SERVICES + " TEXT, "
                + COLUMN_TOTAL + " REAL)"
                ;
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        onCreate(db);
    }

    public void addReservation(SQLiteDatabase db,String fullName, String email, String mobile, String date, String time, String services, Double total) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, fullName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_SERVICES, services);
        values.put(COLUMN_TOTAL, total);
        db.insert(TABLE_RESERVATION, null, values);
    }

    public List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESERVATION, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int fullNameIndex = cursor.getColumnIndex(COLUMN_FULL_NAME);
            int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
            int mobileIndex = cursor.getColumnIndex(COLUMN_MOBILE);
            int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
            int timeIndex = cursor.getColumnIndex(COLUMN_TIME);
            int servicesIndex = cursor.getColumnIndex(COLUMN_SERVICES);
            int totalIndex = cursor.getColumnIndex(COLUMN_TOTAL);

            do {
                int id = cursor.getInt(idIndex);
                String fullName = cursor.getString(fullNameIndex);
                String email = cursor.getString(emailIndex);
                String mobile = cursor.getString(mobileIndex);
                String date = cursor.getString(dateIndex);
                String time = cursor.getString(timeIndex);
                String services = cursor.getString(servicesIndex);
                double total = cursor.getDouble(totalIndex);

                Reservation reservation = new Reservation(id, fullName, email, mobile, date, time, services, total);
                reservations.add(reservation);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();
        return reservations;
    }
}
