package com.reshadi.nima.kidocodeimagetranslator.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> shownMonthYear;

    private DrawerLayout drawerLayout;
    public MainActivityViewModel(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }


    public void drawerIconClicked(){

        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
