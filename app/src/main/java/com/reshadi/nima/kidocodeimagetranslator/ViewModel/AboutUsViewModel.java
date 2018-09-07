package com.reshadi.nima.kidocodeimagetranslator.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;


public class AboutUsViewModel extends ViewModel {

    private Context context;

    public AboutUsViewModel(Context context) {
        this.context = context;
    }

    public String getAppVersion() {

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "version : " + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "version : 1.0.0";
    }

    public void openUrl(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nimareshadi"));
        context.startActivity(browserIntent);
    }
}
