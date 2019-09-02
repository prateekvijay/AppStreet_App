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
public class RepoEntityTest {

    private final String name = "butterknife";
    private final String description = "Binds Android views and callbacks to fields and methods.";
    private final String url = "https://github.com/JakeWharton/butterknife";

    @Mock
    User.RepoBean repoEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(repoEntity.getName()).thenReturn(name);
        Mockito.when(repoEntity.getDescription()).thenReturn(description);
        Mockito.when(repoEntity.getUrl()).thenReturn(url);

    }

    @Test
    public void testRepoName() {
        Mockito.when(repoEntity.getName()).thenReturn(name);
        Assert.assertEquals("butterknife", repoEntity.getName());
    }

    @Test
    public void testRepoDescription() {
        Mockito.when(repoEntity.getDescription()).thenReturn(description);
        Assert.assertEquals("Binds Android views and callbacks to fields and methods.", repoEntity.getDescription());
    }

    @Test
    public void testRepoLink() {
        Mockito.when(repoEntity.getUrl()).thenReturn(url);
        Assert.assertEquals("https://github.com/JakeWharton/butterknife", repoEntity.getUrl());
    }


    @After
    public void tearDown() throws Exception {
        repoEntity = null;
    }
}

