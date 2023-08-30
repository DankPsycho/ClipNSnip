package com.example.myapplication;

import static androidx.test.core.app.ActivityScenario.launch;
import static org.junit.Assert.assertEquals;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ServicesActivityTest {

    private ActivityScenario<ServicesActivity> scenario;

    @Before
    public void setUp() {
        scenario = launch(ServicesActivity.class);
    }

    @After
    public void tearDown() {
        scenario.close();
    }

    @Test
    public void testUpdateStatusText_NoSelection() {
        scenario.onActivity(activity -> {
            activity.updateStatusText();

            // Test that the status text should ask to select at least one service
            assertEquals("Please select at least one service.", activity.getStatusText());
        });
    }

    @Test
    public void testUpdateStatusText_OneServiceSelected() {
        scenario.onActivity(activity -> {
            activity.toggleButton1.setChecked(true);
            activity.updateStatusText();

            // Test that the status text should display the selected service and total price
            assertEquals("You selected Hair Cutting (10€)\nTotal Price: €10.0", activity.getStatusText());
        });
    }

    @Test
    public void testUpdateStatusText_AllServicesSelected() {
        scenario.onActivity(activity -> {
            activity.toggleButton1.setChecked(true);
            activity.toggleButton2.setChecked(true);
            activity.toggleButton3.setChecked(true);
            activity.toggleButton4.setChecked(true);
            activity.updateStatusText();

            // Test that the status text should display all selected services and total price
            assertEquals("You selected Hair Cutting (10€), Beard Trimming (8€), Hair Washing (5€), Styling (5€)\nTotal Price: €28.0", activity.getStatusText());
        });
    }
}
