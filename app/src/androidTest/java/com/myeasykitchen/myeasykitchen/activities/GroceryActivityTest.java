package com.myeasykitchen.myeasykitchen.activities;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.myeasykitchen.myeasykitchen.DatabaseClient;
import com.myeasykitchen.myeasykitchen.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GroceryActivityTest {
    @Rule
    public ActivityTestRule<GroceryActivity> mActivityRule
            = new ActivityTestRule<>(GroceryActivity.class, true, false);

    @Before
    public void setUp() {
        DatabaseClient.getInstance().setUser("1","user",mActivityRule.getActivity());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra("itemID", "");
        intent.putExtra("listID", "");
        mActivityRule.launchActivity(intent);

    }

    @Test
    public void testnewItemCancel() {
        onView(withId(R.id.add_grocery_item)).perform(click());
        onView(withId(R.id.cancel_button)).perform(click());
        onView(withId(R.id.add_grocery_item)).check(matches(isDisplayed()));
    }

    @Test
    public void testnewItemFail() {
        onView(withId(R.id.add_grocery_item)).perform(click());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testnewItemSuccess() {
        onView(withId(R.id.add_grocery_item)).perform(click());
        onView(withId(R.id.name_text)).perform(typeText("item"));
        onView(withId(R.id.quantity_text)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.add_grocery_item)).check(matches(isDisplayed()));
    }

}