package com.reshadi.nima.kidocodeimagetranslator.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.reshadi.nima.kidocodeimagetranslator.R;
import com.reshadi.nima.kidocodeimagetranslator.ViewModel.MainActivityViewModel;
import com.reshadi.nima.kidocodeimagetranslator.databinding.ActivityMainBinding;


public class MainActivity extends ParentActivity implements View.OnClickListener{


    private ActivityMainBinding activityMainBinding;
    private static final int RC_OCR_CAPTURE = 9003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setViewModel(new MainActivityViewModel(activityMainBinding.activityMainDrawer,context));
        activityMainBinding.setLifecycleOwner(this);

        TAG = "MainActivity";

        //setupNavigationDrawer();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.capture_fab) {
            // launch Ocr capture activity.
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            intent.putExtra(OcrCaptureActivity.AutoFocus, true);
            intent.putExtra(OcrCaptureActivity.UseFlash, true);

            startActivityForResult(intent, RC_OCR_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
                    //statusMessage.setText(R.string.ocr_success);
                    //textValue.setText(text);
                    Log.d(TAG, "Text read: " + text);
                } else {
                   // statusMessage.setText(R.string.ocr_failure);
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
               // statusMessage.setText(String.format(getString(R.string.ocr_error),CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
