package com.example.android.appstreet_app.base;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;
    protected static final int PERMISSIONS_REQUEST_STORAGE = 1001;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, getLayout());
        super.onCreate(savedInstanceState);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();


}
