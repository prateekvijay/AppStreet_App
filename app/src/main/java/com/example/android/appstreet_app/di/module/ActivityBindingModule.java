package com.example.android.appstreet_app.di.module;


import com.example.android.appstreet_app.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

}
