package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.hardikgoswami.JokeTeller;
import com.udacity.gradle.builditbigger.ApiHelper.GceHelper;
import com.udacity.gradle.builditbigger.ApiHelper.JokeListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by geniushkg on 6/24/2016.
 */
public class JokeTellerTest extends ApplicationTestCase<Application> {

    CountDownLatch signal;
    String mJoke;

    public JokeTellerTest() {
        super(Application.class);
    }

    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new GceHelper().execute(new JokeListener() {
                @Override
                public void onJokeFetched(String Joke) {
                    mJoke = Joke;
                    signal.countDown();
                }
            });
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("Null Joke", mJoke);
            assertFalse("Joke Fetched Empty", mJoke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }
}