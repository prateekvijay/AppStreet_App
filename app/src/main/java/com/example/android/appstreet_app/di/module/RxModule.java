package com.example.android.appstreet_app.di.module;

import com.example.android.appstreet_app.api.RxSingleSchedulers;
import com.example.android.appstreet_app.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @AppScope
    @Provides
    public RxSingleSchedulers providesScheduler() {
        return RxSingleSchedulers.DEFAULT;
    }
}
