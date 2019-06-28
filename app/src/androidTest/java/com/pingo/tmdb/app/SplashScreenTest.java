package com.pingo.tmdb.app;


import android.content.Intent;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.pingo.tmdb.app.movies.MoviesCatalogActivity;
import com.pingo.tmdb.app.onboard.SplashActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-27.
 * ----------------------------------------------
 *
 * Test for Splash Activity
 */
@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityTestRule = new ActivityTestRule<>(SplashActivity.class, true,
            false);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void viewSplash_NavigateToMoviesCatalogAfter1000ms() {


        // setup intent
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);

        // setup idle resource time
        int splashScreenWaitingTime = 1000;
        IdlingResource idlingResource = new DelayedIdlingResource(splashScreenWaitingTime);

        // ask to wait
        IdlingRegistry.getInstance().register(idlingResource);

        // navigate
        intended(hasComponent(MoviesCatalogActivity.class.getName()));

        // let it go now
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

}