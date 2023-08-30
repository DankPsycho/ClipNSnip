package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_RESERVATION, null, null);
    }

    @After
    public void tearDown() {
        database.close();
    }

    @Test
    public void testAddAndRetrieveReservation() {
        // Add a reservation
        dbHelper.addReservation(database, "Ivan", "john@example.com", "123456789", "2023-08-28", "10:00 AM", "Haircut", 20.0);

        // Retrieve reservations
        List<Reservation> reservations = dbHelper.getReservations(); // No need to pass the database instance

        // Check if there is at least one reservation
        assertTrue(reservations.size() > 0);

        // Get the first reservation
        Reservation reservation = reservations.get(0);

        // Check if the retrieved data matches the added data
        assertEquals("Ivan", reservation.getFullName());
        assertEquals("john@example.com", reservation.getEmail());
        assertEquals("123456789", reservation.getMobile());
        assertEquals("2023-08-28", reservation.getDate());
        assertEquals("10:00 AM", reservation.getTime());
        assertEquals("Haircut", reservation.getServices());
        assertEquals(20.0, reservation.getTotal(), 0.001); // Delta for double comparison
    }
    @Test
    public void testAddAndRetrieveMultipleReservations() {
        dbHelper.addReservation(database, "ABC", "john@example.com", "123456789", "2023-08-28", "10:00 AM", "Haircut", 20.0);
        dbHelper.addReservation(database, "DEF", "jane@example.com", "987654321", "2023-08-29", "2:00 PM", "Manicure", 15.0);

        // Retrieve reservations
        List<Reservation> reservations = dbHelper.getReservations();

        // Check if both reservations are retrieved
        assertEquals(2, reservations.size());

        // Check details of the first reservation
        Reservation reservation1 = reservations.get(0);
        assertEquals("ABC", reservation1.getFullName());
        assertEquals("john@example.com", reservation1.getEmail());
        assertEquals("123456789", reservation1.getMobile());
        assertEquals("2023-08-28", reservation1.getDate());
        assertEquals("10:00 AM", reservation1.getTime());
        assertEquals("Haircut", reservation1.getServices());
        assertEquals(20.0, reservation1.getTotal(), 0.001);

        // Check details of the second reservation
        Reservation reservation2 = reservations.get(1);
        assertEquals("DEF", reservation2.getFullName());
        assertEquals("jane@example.com", reservation2.getEmail());
        assertEquals("987654321", reservation2.getMobile());
        assertEquals("2023-08-29", reservation2.getDate());
        assertEquals("2:00 PM", reservation2.getTime());
        assertEquals("Manicure", reservation2.getServices());
        assertEquals(15.0, reservation2.getTotal(), 0.001);
    }

    // Add more test methods for other functionalities of DBHelper
}
