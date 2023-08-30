package com.example.myapplication;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ServicesActivityUITest {

    @Rule
    public ActivityScenarioRule<ServicesActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ServicesActivity.class);

    @Test
    public void testDisplayedServiceTitles() {
        // Verify that the service titles are displayed
        Espresso.onView(allOf(withId(R.id.textViewHairCutting), isDisplayed()))
                .check(matches(withText("Hair Cutting")));

        Espresso.onView(allOf(withId(R.id.textViewBeardTrimming), isDisplayed()))
                .check(matches(withText("Beard Trimming")));

        Espresso.onView(allOf(withId(R.id.textViewHairWashing), isDisplayed()))
                .check(matches(withText("Hair Washing")));

        Espresso.onView(allOf(withId(R.id.textViewStyling), isDisplayed()))
                .check(matches(withText("Styling")));
    }

    @Test
    public void testToggleButtonVisibility() {
        // Verify that all ToggleButtons are visible
        Espresso.onView(withId(R.id.toggleButton1)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.toggleButton2)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.toggleButton3)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.toggleButton4)).check(matches(isDisplayed()));
    }

    @Test
    public void testStatusText() {
        // Verify that the initial status text is displayed
        Espresso.onView(withId(R.id.statusTextView))
                .check(matches(withText("Please select at least one service.")));
    }

    @Test
    public void testSelectServicesButton() {
        // Verify that the "Select services" button is displayed
        Espresso.onView(withId(R.id.button_select_services))
                .check(matches(isDisplayed()));
    }
    @Test
    public void testToggleButtonSelection() {
        // Click on the Hair Cutting ToggleButton
        Espresso.onView(withId(R.id.toggleButton1)).perform(click());

        // Verify that the Hair Cutting ToggleButton is now selected
        Espresso.onView(withId(R.id.toggleButton1))
                .check(matches(isChecked()));

        // Verify that the status text is updated
        Espresso.onView(withId(R.id.statusTextView))
                .check(matches(withText("You selected Hair Cutting (10€)\nTotal Price: €10.0")));
    }

    @Test
    public void testMultipleToggleButtonSelection() {
        // Click on the Hair Cutting and Beard Trimming ToggleButtons
        Espresso.onView(withId(R.id.toggleButton1)).perform(click());
        Espresso.onView(withId(R.id.toggleButton2)).perform(click());

        // Verify that both ToggleButtons are now selected
        Espresso.onView(withId(R.id.toggleButton1))
                .check(matches(isChecked()));
        Espresso.onView(withId(R.id.toggleButton2))
                .check(matches(isChecked()));

        // Verify that the status text is updated with both selected services
        Espresso.onView(withId(R.id.statusTextView))
                .check(matches(withText("You selected Hair Cutting (10€), Beard Trimming (8€)\nTotal Price: €18.0")));
    }

}
