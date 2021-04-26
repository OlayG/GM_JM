package com.example.gm_challenge.view

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.gm_challenge.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private lateinit var stringTyped: String

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        stringTyped = "Test"
    }

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_textInputEditText() {
        onView(withId(R.id.text_edit_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_buttonSearch() {
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_spinner_recyclerView() {
        onView(withId(R.id.text_edit_text))
                .perform(typeText(stringTyped), closeSoftKeyboard())

        onView(withId(R.id.btn_search)).perform(click())

        onView(withId(R.id.spinner)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        SystemClock.sleep(1000)

        onView(withId(R.id.rv_list)).check(matches(isDisplayed()))
    }
}