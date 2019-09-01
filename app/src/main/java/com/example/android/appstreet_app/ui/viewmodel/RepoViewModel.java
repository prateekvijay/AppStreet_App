package com.example.android.appstreet_app.ui.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.appstreet_app.api.AppConstants;
import com.example.android.appstreet_app.api.RepoApiClient;
import com.example.android.appstreet_app.api.RxSingleSchedulers;
import com.example.android.appstreet_app.api.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RepoViewModel extends ViewModel {

    private CompositeDisposable disposable;
    private final RepoApiClient apiClient;
    private final RxSingleSchedulers rxSingleSchedulers;
    private final MutableLiveData<RepoListViewState> repoListState = new MutableLiveData<>();

    public MutableLiveData<RepoListViewState> getRepoListState() {
        return repoListState;
    }

    @Inject
    public RepoViewModel(RepoApiClient apiClient, RxSingleSchedulers rxSingleSchedulers) {
        this.apiClient = apiClient;
        this.rxSingleSchedulers = rxSingleSchedulers;
        disposable = new CompositeDisposable();
    }

    public void fetchRepo() {
        disposable.add(apiClient.fetchRepo(AppConstants.LANGUAGE, AppConstants.DURATION)
                .doOnEvent((repoList, throwable) -> onLoading())
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(this::onSuccess,
                        this::onError));
    }

    private void onSuccess(List<User> repoList) {
        RepoListViewState.SUCCESS_STATE.setData(repoList);
        repoListState.postValue(RepoListViewState.SUCCESS_STATE);
    }

    private void onError(Throwable error) {
        RepoListViewState.ERROR_STATE.setError(error);
        repoListState.postValue(RepoListViewState.ERROR_STATE);
    }

    private void onLoading() {
        repoListState.postValue(RepoListViewState.LOADING_STATE);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
