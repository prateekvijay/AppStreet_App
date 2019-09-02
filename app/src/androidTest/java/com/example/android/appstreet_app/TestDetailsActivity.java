package com.example.android.appstreet_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.example.android.appstreet_app.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(JUnit4.class)
public class TestDetailsActivity {

    Context context;
    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setup() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.recyclerViewList)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, MyViewAction.clickChildViewWithId(R.id.more_details)));

    }


    @Test
    public void testLink() {
        onView(withId(R.id.user_link_value))
                .perform(click());
    }

    @Test
    public void testUserName() {
        onView(withId(R.id.username_view)).check(matches(withText(context.getString(R.string.username))));
        onView(withId(R.id.username_value)).check(matches(not(withText(""))));
    }

    @Test
    public void testName() {
        onView(withId(R.id.name_view)).check(matches(withText(context.getString(R.string.name))));
        onView(withId(R.id.name_value)).check(matches(not(withText(""))));
    }

    @Test
    public void testUserType() {
        onView(withId(R.id.user_type_view)).check(matches(withText(context.getString(R.string.usertype))));
        onView(withId(R.id.user_type_value)).check(matches(not(withText(""))));
    }

    @Test
    public void testUserLink() {
        onView(withId(R.id.user_link_view)).check(matches(withText(context.getString(R.string.userlink))));
        onView(withId(R.id.user_link_value)).check(matches(not(withText(""))));
    }


    @Test
    public void testRepoName() {
        onView(withId(R.id.reponame_view)).check(matches(withText(context.getString(R.string.reponame))));
        onView(withId(R.id.textView2)).check(matches(not(withText(""))));
    }

    @Test
    public void testRepoDescription() {
        onView(withId(R.id.repo_Descp_view)).check(matches(withText(context.getString(R.string.repodescription))));
        onView(withId(R.id.repo_descp_value)).check(matches(not(withText(""))));
    }

    @Test
    public void testRepoLink() {
        onView(withId(R.id.repo_link_view)).check(matches(withText(context.getString(R.string.repoLink))));
        onView(withId(R.id.repolink_value)).check(matches(not(withText(""))));
    }


}

