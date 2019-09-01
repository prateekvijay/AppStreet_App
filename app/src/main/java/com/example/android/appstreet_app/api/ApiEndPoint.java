package com.example.android.appstreet_app.api;

import com.example.android.appstreet_app.api.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("developers")
    Single<ArrayList<User>> getRepositories(@Query("language") String language, @Query("since") String since);
}
