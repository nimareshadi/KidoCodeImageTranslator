package com.reshadi.nima.kidocodeimagetranslator.View.Activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.MainActivityViewModel;
import com.reshadi.nima.kidocodeimagetranslator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private Context context;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setViewModel(new MainActivityViewModel(activityMainBinding.activityMainDrawer));
        activityMainBinding.setLifecycleOwner(this);

        context = this;
        //setupNavigationDrawer();
    }
}
