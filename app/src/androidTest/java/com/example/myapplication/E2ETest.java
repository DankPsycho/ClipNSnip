package com.example.myapplication;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class E2ETest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testE2EFlow() {
        // Navigate to ServicesActivity
        Espresso.onView(withId(R.id.button_reservation)).perform(click());

        // Click on the Hair Cutting ToggleButton
        Espresso.onView(withId(R.id.toggleButton1)).perform(click());

        // Verify that the Hair Cutting ToggleButton is now selected
        Espresso.onView(withId(R.id.toggleButton1))
                .check(matches(isChecked()));

        // Verify that the status text is updated
        Espresso.onView(withId(R.id.statusTextView))
                .check(matches(withText("You selected Hair Cutting (10€)\nTotal Price: €10.0")));

        // Click on the "Select Services" button
        Espresso.onView(withId(R.id.button_select_services)).perform(click());

        // Click on the "Confirm Time and Date" button
        Espresso.onView(withId(R.id.time_and_date_button)).perform(click());

        // Verify that the UserDetailsActivity is displayed
        Espresso.onView(withId(R.id.editTextFullName))
                .check(matches(isDisplayed()));

        // Enter user details (replace with actual values)
        Espresso.onView(withId(R.id.editTextFullName)).perform(typeText("John Doe"));
        Espresso.onView(withId(R.id.editTextEmail)).perform(typeText("john@example.com"));
        Espresso.onView(withId(R.id.editTextMobile)).perform(typeText("1234567890"));
        // Close the keyboard
        Espresso.closeSoftKeyboard();
        // Click on the "Next" button
        Espresso.onView(withId(R.id.button_confirm_details)).perform(click());
        // Verify that the Confirmation is displayed
        Espresso.onView(withId(R.id.titleTextView))
                .check(matches(withText("Confirmation")));
        // Click on the "Confirm" button
        Espresso.onView(withId(R.id.confirmButton)).perform(click());
        // Verify that the ThankYouActivity is displayed
        Espresso.onView(withId(R.id.titleTextView))
                .check(matches(withText("Thank You for Your Reservation")));
        // Click on the "Return to Main Page" button
        Espresso.onView(withId(R.id.returnButton)).perform(click());
    }
}
