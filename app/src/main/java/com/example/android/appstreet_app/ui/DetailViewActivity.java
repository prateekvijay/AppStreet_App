package com.example.android.appstreet_app.ui;

import android.os.Bundle;

import com.example.android.appstreet_app.R;
import com.example.android.appstreet_app.api.model.User;
import com.example.android.appstreet_app.base.BaseActivity;
import com.example.android.appstreet_app.databinding.ScreenDetailsBinding;
import com.example.android.appstreet_app.utils.ImageLoader;

public class DetailViewActivity extends BaseActivity<ScreenDetailsBinding> {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            user = getIntent().getParcelableExtra("SELECTED_USER");
        }
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(user.getAvatar(), binding.detailsUserImage);
        binding.setUser(user);
    }

    @Override
    public int getLayout() {
        return R.layout.screen_details;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
