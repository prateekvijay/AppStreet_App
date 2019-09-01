package com.example.android.appstreet_app;

import android.Manifest;
import android.content.Intent;
import android.test.InstrumentationTestCase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.example.android.appstreet_app.api.AppConstants;
import com.example.android.appstreet_app.ui.MainActivity;
import com.example.android.appstreet_app.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class MockApiTest extends InstrumentationTestCase {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        AppConstants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void testApiResponse(){
        String fileName = "mock_json.json";
        String body = Utils.readFile(getInstrumentation().getTargetContext(), fileName);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(body));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}