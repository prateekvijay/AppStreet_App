package com.example.android.appstreet_app;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.android.appstreet_app.di.component.ApplicationComponent;
import com.example.android.appstreet_app.di.component.DaggerApplicationComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class AppStreetApp extends DaggerApplication {
    private static AppStreetApp application;
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appContext = getApplicationContext();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static AppStreetApp getApplication() {
        return application;
    }

    @NonNull
    public String getApplicationId() {
        return BuildConfig.APPLICATION_ID;
    }
}
