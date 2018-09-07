package com.reshadi.nima.kidocodeimagetranslator.View.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.Util.Constans;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.MainActivityViewModel;
import com.reshadi.nima.kidocodeimagetranslator.databinding.ActivityMainBinding;


public class MainActivity extends ParentActivity implements View.OnClickListener{


    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        activityMainBinding.setViewModel(new MainActivityViewModel(activityMainBinding.activityMainDrawer,
                activityMainBinding.targetLanguageSpinner,
                activityMainBinding.readTextView,activityMainBinding.savedListView,context));
        activityMainBinding.setLifecycleOwner(this);

        TAG = "MainActivity";

        setupNavigationDrawer();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.capture_fab) {
            // launch Ocr capture activity.
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            intent.putExtra(OcrCaptureActivity.AutoFocus, true);
            intent.putExtra(OcrCaptureActivity.UseFlash, true);

            startActivityForResult(intent, Constans.RC_OCR_CAPTURE);
        }
    }

    private void setupNavigationDrawer(){

        activityMainBinding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.create_class:
                        startActivity(new Intent(context, AboutUsActivity.class));
                        activityMainBinding.activityMainDrawer.closeDrawer(Gravity.LEFT);
                        break;
                    default:
                        return true;
                }
                return false;
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constans.RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
                    activityMainBinding.readTextView.setText(text);
                    Log.d(TAG, "Text read: " + text);
                } else {
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
