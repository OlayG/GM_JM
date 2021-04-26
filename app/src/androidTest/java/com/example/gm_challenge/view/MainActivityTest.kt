package com.example.gm_challenge.view

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.gm_challenge.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_textInputEditText() {
        onView(withId(R.id.text_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_spinner_recyclerView() {
        onView(withId(R.id.text_edit_text)).perform(typeText("Mike"), closeSoftKeyboard())

        onView(withId(R.id.spinner)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        SystemClock.sleep(1000)

        onView(withId(R.id.rv_list)).check(matches(isDisplayed()))
    }
}