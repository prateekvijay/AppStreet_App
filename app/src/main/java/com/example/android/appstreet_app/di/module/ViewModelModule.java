package com.example.android.appstreet_app.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.appstreet_app.api.RepoViewModelFactory;
import com.example.android.appstreet_app.di.scope.ViewModelKey;
import com.example.android.appstreet_app.ui.viewmodel.RepoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel.class)
    abstract ViewModel bindNewsViewModel(RepoViewModel searchViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindNewsViewModelFactory(RepoViewModelFactory factory);
}
