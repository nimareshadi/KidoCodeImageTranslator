package com.reshadi.nima.kidocodeimagetranslator.View.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.AboutUsViewModel;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.MainActivityViewModel;
import com.reshadi.nima.kidocodeimagetranslator.databinding.AboutUsActivityBinding;

public class AboutUsActivity extends ParentActivity {

    private AboutUsActivityBinding aboutUsActivityBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = "AboutUsActivity";
        aboutUsActivityBinding = DataBindingUtil.setContentView(this, R.layout.about_us_activity);

        aboutUsActivityBinding.setViewModel(new AboutUsViewModel(context));
        aboutUsActivityBinding.setLifecycleOwner(this);

    }
}
