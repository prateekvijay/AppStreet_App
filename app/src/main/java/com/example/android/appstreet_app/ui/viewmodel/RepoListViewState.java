package com.example.android.appstreet_app.ui.viewmodel;

import com.example.android.appstreet_app.api.model.User;
import com.example.android.appstreet_app.base.BaseViewState;

import java.util.ArrayList;
import java.util.List;

public class RepoListViewState extends BaseViewState<List<User>> {
    private RepoListViewState(List<User> data, int currentState, Throwable error) {
        this.data = data;
        this.error = error;
        this.currentState = currentState;
    }

    public static RepoListViewState ERROR_STATE = new RepoListViewState(null, BaseViewState.State.FAILED.value, new Throwable());
    public static RepoListViewState LOADING_STATE = new RepoListViewState(null, State.LOADING.value, null);
    public static RepoListViewState SUCCESS_STATE = new RepoListViewState(new ArrayList<User>(), State.SUCCESS.value, null);

}
