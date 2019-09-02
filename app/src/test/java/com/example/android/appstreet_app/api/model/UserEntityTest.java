package com.example.android.appstreet_app.api.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
@RunWith(JUnit4.class)
public class UserEntityTest {


    private final String username = "JakeWharton";
    private final String name = "Jake Wharton";
    private final String type = "user";
    private final String url = "https://github.com/JakeWharton";
    private final User.RepoBean repo = new User.RepoBean();

    @Mock
    User.RepoBean repoBean;

    @Mock
    User user;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        Mockito.when(user.getUsername()).thenReturn(username);
        Mockito.when(user.getName()).thenReturn(name);
        Mockito.when(user.getType()).thenReturn(type);
        Mockito.when(user.getUrl()).thenReturn(url);
        Mockito.when(user.getRepo()).thenReturn(repoBean);
    }

    @Test
    public void testUserName() {
        Mockito.when(user.getUsername()).thenReturn(username);
        Assert.assertEquals("JakeWharton", user.getUsername());
    }

    @Test
    public void testName() {
        Mockito.when(user.getName()).thenReturn(name);
        Assert.assertEquals("Jake Wharton", user.getName());
    }

    @Test
    public void testUserType() {
        Mockito.when(user.getType()).thenReturn(type);
        Assert.assertEquals("user", user.getType());
    }

    @Test
    public void testUserLink() {
        Mockito.when(user.getUrl()).thenReturn(url);
        Assert.assertEquals("https://github.com/JakeWharton", user.getUrl());
    }


    @Test
    public void testRepoEntity() {
        Mockito.when(user.getRepo()).thenReturn(repo);
        Assert.assertEquals(new User.RepoBean(), user.getRepo());
    }


    @After
    public void tearDown() {
        user = null;
        repoBean = null;
    }
}

