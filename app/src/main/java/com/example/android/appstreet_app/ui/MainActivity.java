package com.example.android.appstreet_app.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
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
    private RepoViewModel repoViewModel;

    @Inject
    RepoViewModelFactory repoViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoViewModel = ViewModelProviders.of(this, repoViewModelFactory).get(RepoViewModel.class);
        initRepoDataAdapter();
        observeDataChange();
        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Permission needed to use app", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_STORAGE);
            }
        } else {
            repoViewModel.fetchRepo();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_STORAGE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    repoViewModel.fetchRepo();
                } else {
                    Toast.makeText(this, "Permission needed to use app", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

        }
    }

    private void loadRepoData(List<User> data) {
        repoAdapter.addRepoList(data);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void initRepoDataAdapter() {
        repoAdapter = new RepoAdapter(new ArrayList<>());
        binding.recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewList.setAdapter(repoAdapter);
        repoAdapter.addListener(this);
    }

    private void observeDataChange() {
        repoViewModel.getRepoListState().observe(this, repoListViewState -> {
            switch (repoListViewState.getCurrentState()) {
                case 0:
                    binding.loadingView.setVisibility(View.VISIBLE);
                    binding.error.setVisibility(View.GONE);
                    binding.recyclerViewList.setVisibility(View.GONE);
                    break;
                case 1:
                    loadRepoData(repoListViewState.getData());
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
        bundle.putParcelable("SELECTED_USER", user);
        Intent intent = new Intent(this, DetailViewActivity.class);
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
