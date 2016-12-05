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

import com.myeasykitchen.myeasykitchen.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddGroceryItemActivityTest {
    @Rule
    public ActivityTestRule<AddKitchenItemActivity> mActivityRule
            = new ActivityTestRule<>(AddKitchenItemActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra("itemID", "");
        intent.putExtra("listID", "");
        mActivityRule.launchActivity(intent);

    }

    @Test
    public void testSaveButtonNoName() {
        try {
            onView(withId(R.id.quantity_text)).perform(typeText("5"));
            mActivityRule.getActivity().getKitchenItem();
            fail();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void testSaveButton() {
        try {
            onView(withId(R.id.name_text)).perform(typeText("item name"));
            onView(withId(R.id.quantity_text)).perform(typeText("5"));
            mActivityRule.getActivity().getKitchenItem();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}