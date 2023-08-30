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
public class UserDetailsActivityTest {

    private ActivityScenario<UserDetailsActivity> scenario;

    @Before
    public void setUp() {
        scenario = launch(UserDetailsActivity.class);
    }

    @After
    public void tearDown() {
        scenario.close();
    }

    @Test
    public void testGetFullName() {
        scenario.onActivity(activity -> {
            activity.editTextFullName.setText("John Doe");

            // Test that the retrieved full name matches the entered value
            assertEquals("John Doe", activity.getFullName());
        });
    }

    @Test
    public void testGetEmail() {
        scenario.onActivity(activity -> {
            activity.editTextEmail.setText("john@example.com");

            // Test that the retrieved email matches the entered value
            assertEquals("john@example.com", activity.getEmail());
        });
    }

    @Test
    public void testGetMobile() {
        scenario.onActivity(activity -> {
            activity.editTextMobile.setText("123456789");

            // Test that the retrieved mobile number matches the entered value
            assertEquals("123456789", activity.getMobile());
        });
    }
}
