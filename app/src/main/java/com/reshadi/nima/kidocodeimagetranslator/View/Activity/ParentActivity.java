package com.reshadi.nima.kidocodeimagetranslator.View.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ParentActivity extends AppCompatActivity {
    protected Context context;
    protected String TAG = "ParentActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
    }
}
