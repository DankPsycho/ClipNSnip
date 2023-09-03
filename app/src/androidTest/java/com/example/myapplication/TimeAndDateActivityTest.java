package com.example.myapplication;

import static androidx.test.core.app.ActivityScenario.launch;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TimeAndDateActivityTest {

    private ActivityScenario<TimeAndDateActivity> scenario;

    @Before
    public void setUp() {
        scenario = launch(TimeAndDateActivity.class);
    }

    @After
    public void tearDown() {
        scenario.close();
    }

    @Test
    public void testFormatDate() {
        scenario.onActivity(activity -> {
            String formattedDate = activity.formatDate(1679356800000L); // Using a specific timestamp

            // Test that the formatted date matches the expected format
            assertEquals("19/03/2023", formattedDate);
        });
    }

    @Test
    public void testGetSelectedDate() {
        scenario.onActivity(activity -> {
            String selectedDate = activity.getSelectedDate();

            // Test that the selected date is not null or empty
            assertFalse(selectedDate.isEmpty());
        });
    }
}
