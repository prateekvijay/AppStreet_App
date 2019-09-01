package com.example.android.appstreet_app;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.example.android.appstreet_app.api.ApiEndPoint;
import com.example.android.appstreet_app.api.AppConstants;
import com.example.android.appstreet_app.api.RepoApiClient;
import com.example.android.appstreet_app.api.RxSingleSchedulers;
import com.example.android.appstreet_app.api.model.User;
import com.example.android.appstreet_app.ui.viewmodel.RepoListViewState;
import com.example.android.appstreet_app.ui.viewmodel.RepoViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ListViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    ApiEndPoint apiEndPoint;
    @Mock
    RepoApiClient apiClient;
    private RepoViewModel viewModel;
    @Mock
    Observer<RepoListViewState> observer;
    @Mock
    private LifecycleOwner lifecycleOwner;
    private Lifecycle lifecycle;


    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new RepoViewModel(apiClient, RxSingleSchedulers.TEST_SCHEDULER);
        viewModel.getRepoListState().observeForever(observer);
    }

    @Test
    public void testNull() {
        when(apiClient.fetchRepo(AppConstants.LANGUAGE, AppConstants.DURATION)).thenReturn(null);
        assertNotNull(viewModel.getRepoListState());
        assertTrue(viewModel.getRepoListState().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        when(apiClient.fetchRepo(AppConstants.LANGUAGE, AppConstants.DURATION)).thenReturn(Single.just(new ArrayList<User>()));
        viewModel.fetchRepo();
        verify(observer).onChanged(RepoListViewState.LOADING_STATE);
        verify(observer).onChanged(RepoListViewState.SUCCESS_STATE);
    }

    @Test
    public void testApiFetchDataError() {
        when(apiClient.fetchRepo(AppConstants.LANGUAGE, AppConstants.DURATION)).thenReturn(Single.error(new Throwable("Api error")));
        viewModel.fetchRepo();
        verify(observer).onChanged(RepoListViewState.LOADING_STATE);
        verify(observer).onChanged(RepoListViewState.ERROR_STATE);
    }

    @After
    public void tearDown() {
        apiClient = null;
        viewModel = null;
    }
}
