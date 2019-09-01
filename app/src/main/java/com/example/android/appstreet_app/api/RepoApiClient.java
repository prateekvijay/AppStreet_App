package com.example.android.appstreet_app.api;


import com.example.android.appstreet_app.api.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.Single;

public class RepoApiClient {

    private final ApiEndPoint api;

    @Inject
    public RepoApiClient(ApiEndPoint api) {
        this.api = api;
    }

    public Single<ArrayList<User>> fetchRepo(String language, String duration) {
        return api.getRepositories(language,duration);
    }

}
