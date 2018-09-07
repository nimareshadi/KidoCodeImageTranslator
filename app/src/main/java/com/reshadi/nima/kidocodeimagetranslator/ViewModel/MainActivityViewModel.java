package com.reshadi.nima.kidocodeimagetranslator.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;



public class MainActivityViewModel extends ViewModel {

    private DrawerLayout drawerLayout;
    private Context context;
    public MainActivityViewModel(DrawerLayout drawerLayout,Context context) {
        this.drawerLayout = drawerLayout;
        this.context = context;
    }
    public void drawerIconClicked(){ drawerLayout.openDrawer(Gravity.LEFT); }
}
