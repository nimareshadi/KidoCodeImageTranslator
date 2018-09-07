package com.reshadi.nima.kidocodeimagetranslator.Model.Database.Initializer;

import android.content.Context;
import com.reshadi.nima.kidocodeimagetranslator.Model.Database.Model.MyObjectBox;
import io.objectbox.BoxStore;


public class DBIntializer {
    private static  BoxStore boxStore;
    public static void init(Context context){

        boxStore  = MyObjectBox.builder().androidContext(context).build();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
