package com.speedo.base;

import android.app.Application;

import com.speedo.helper.AppUtil;
import com.speedo.helper.BLog;
import com.speedo.helper.SpPreferences;

/**
 * Created by ketan on 2/16/16.
 */
public class AppController extends Application {

    private static final String LOG_TAG = "AppController";
    private SpPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        BLog.setShouldPrint(true);
        preferences = new SpPreferences(this);
        preferences.setMultipleDevices(false);
        BLog.e(LOG_TAG, "device id - "+ AppUtil.getDeviceId(this));
    }

}
