package com.example.loginexample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    private View decorView ;
    @Before
    public void setUp()
    {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.loginexample", appContext.getPackageName());
    }
    @Test
    public void girisLabelControl()
    {
        onView(withId(R.id.giris)).check(matches(withText("Giriş Yap")));

    }
    @Test
    public void buttonControl()
    {
        onView(withText("LOGIN")).check(matches(isDisplayed()));
    }
    @Test
    public void placeholderControl()
    {
        onView(withId(R.id.mail)).check(matches(withHint("Mail")));
    }
    @Test
    public void buttonClickControl()
    {
        //onView(withId(R.id.signinbtn)).perform(click());
        onView(withText("LOGIN")).perform(click());
    }
    @Test
    public  void toastMessageControl() throws InterruptedException {

        onView(withId(R.id.mail))
                .perform(typeText("root"));
        onView(withId(R.id.password)).perform(typeText("12345"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.signinbtn)).perform(click());
        //Thread.sleep(2000);
        onView(withText("Giriş Yapıldı"))
                .inRoot(withDecorView(Matchers.not(decorView)))// Here you use decorView
                .check(matches(isDisplayed()));
        //onView(allOf(withText(containsString("Giriş Yapıldı")))).check(matches(isDisplayed()));
    }


}