package com.example.android.appstreet_app.di.component;

import android.app.Application;

import com.example.android.appstreet_app.AppStreetApp;
import com.example.android.appstreet_app.di.module.ActivityBindingModule;
import com.example.android.appstreet_app.di.module.ApplicationModule;
import com.example.android.appstreet_app.di.module.ContextModule;
import com.example.android.appstreet_app.di.module.RxModule;
import com.example.android.appstreet_app.di.scope.AppScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;


@AppScope
@Component(modules = {ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class, RxModule.class, ContextModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(AppStreetApp application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}