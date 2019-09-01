package com.example.android.appstreet_app.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android.appstreet_app.R;
import com.example.android.appstreet_app.api.RepoViewModelFactory;
import com.example.android.appstreet_app.api.model.User;
import com.example.android.appstreet_app.base.BaseActivity;
import com.example.android.appstreet_app.databinding.ActivityMainBinding;
import com.example.android.appstreet_app.ui.callback.IItemClick;
import com.example.android.appstreet_app.ui.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements IItemClick<User> {
    private RepoAdapter repoAdapter;
    private RepoViewModel newsViewModel;

    @Inject
    RepoViewModelFactory newsViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(RepoViewModel.class);
        initNewsDataAdapter();
        observeDataChange();
        newsViewModel.fetchRepo();
    }

    private void loadNewsData(List<User> data) {
        repoAdapter.addNewsList(data);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void initNewsDataAdapter() {
        repoAdapter = new RepoAdapter(new ArrayList<>());
        binding.recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewList.setAdapter(repoAdapter);
        repoAdapter.addListener(this);
    }

    private void observeDataChange() {
        newsViewModel.getNewsListState().observe(this, newsListViewState -> {
            switch (newsListViewState.getCurrentState()) {
                case 0:
                    binding.loadingView.setVisibility(View.VISIBLE);
                    binding.error.setVisibility(View.GONE);
                    binding.recyclerViewList.setVisibility(View.GONE);
                    break;
                case 1:
                    loadNewsData(newsListViewState.getData());
                    binding.loadingView.setVisibility(View.GONE);
                    binding.error.setVisibility(View.GONE);
                    binding.recyclerViewList.setVisibility(View.VISIBLE);
                    break;
                case -1: // show error
                    binding.loadingView.setVisibility(View.GONE);
                    binding.recyclerViewList.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.error.setText(getString(R.string.error_msg));
                    break;
            }
        });
    }

    @Override
    public void onItemClick(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SELECTED_USER",user);
        Intent intent = new Intent(this,DetailViewActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repoAdapter != null) {
            repoAdapter.removeListener();
        }
    }
}
