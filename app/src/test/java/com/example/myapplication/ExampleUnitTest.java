package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        // Use the ApplicationProvider to get the context for the test
        Context context = ApplicationProvider.getApplicationContext();

        // Create an instance of the DBHelper
        dbHelper = new DBHelper(context);

        // Open the database for testing
        database = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        // Close the database after each test
        dbHelper.close();
    }

    @Test
    public void testCreateReservationTable() {
        // Check if the reservations table exists
        boolean tableExists = isTableExists(database, "reservations");
        assertTrue(tableExists);
    }

    @Test
    public void testAddReservation() {
        // Define test data
        String date = "2023-05-24";
        String time = "10:00 AM";
        String service = "Massage";

        // Add a reservation to the database
        dbHelper.addReservation(database, date, time, service);

        // Query the database to retrieve the added reservation
        Cursor cursor = database.rawQuery("SELECT * FROM reservations", null);

        // Check if the cursor is not null
        assertNotNull(cursor);

        // Check if the cursor has a single row
        assertEquals(1, cursor.getCount());

        // Move the cursor to the first row
        cursor.moveToFirst();

        // Check the values of the retrieved reservation
        assertEquals(date, cursor.getString(cursor.getColumnIndex("date")));
        assertEquals(time, cursor.getString(cursor.getColumnIndex("time")));
        assertEquals(service, cursor.getString(cursor.getColumnIndex("services")));

        // Close the cursor
        cursor.close();
    }

    @Test
    public void testUpdateReservation() {
        // Add a reservation to the database
        dbHelper.addReservation(database, "2023-05-24", "10:00 AM", "Massage");

        // Query the database to retrieve the added reservation
        Cursor cursor = database.rawQuery("SELECT * FROM reservations", null);

        // Move the cursor to the first row
        cursor.moveToFirst();

        // Get the reservation ID from the cursor
        int reservationId = cursor.getInt(cursor.getColumnIndex("id"));

        // Update the reservation with a new date
        String newDate = "2023-05-25";
        dbHelper.updateReservation(database, reservationId, newDate, null, null);

        // Query the database again to retrieve the updated reservation
        cursor = database.rawQuery("SELECT * FROM reservations WHERE id = ?", new String[]{String.valueOf(reservationId)});

        // Check if the cursor is not null
        assertNotNull(cursor);

        // Check if the cursor has a single row
        assertEquals(1, cursor.getCount());

        // Move the cursor to the first row
        cursor.moveToFirst();

        // Check the updated date of the reservation
        assertEquals(newDate, cursor.getString(cursor.getColumnIndex("date")));

        // Close the cursor
        cursor.close();
    }

    @Test
    public void testDeleteReservation() {
        // Add a reservation to the database
        dbHelper.addReservation(database, "2023-05-24", "10:00 AM", "Massage");

        // Query the database to retrieve the added reservation
        Cursor cursor = database.rawQuery("SELECT * FROM reservations", null);

        // Move the cursor to the first row
        cursor.moveToFirst();

        // Get the reservation ID from the cursor
        int reservationId = cursor.getInt(cursor.getColumnIndex("id"));

        // Delete the reservation from the database
        dbHelper.deleteReservation(database, reservationId);

        // Query the database again to retrieve the deleted reservation
        cursor = database.rawQuery("SELECT * FROM reservations WHERE id = ?", new String[]{String.valueOf(reservationId)});

        // Check if the cursor is not null
        assertNotNull(cursor);

        // Check if the cursor is empty (no rows)
        assertFalse(cursor.moveToFirst());

        // Close the cursor
        cursor.close();
    }

    // Helper method to check if a table exists in the database
    private boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean tableExists = cursor.getCount() > 0;
        cursor.close();
        return tableExists;
    }
}
