package com.example.android.appstreet_app;

import com.example.android.appstreet_app.di.component.ApplicationComponent;
import com.example.android.appstreet_app.di.component.DaggerApplicationComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class AppStreetApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
