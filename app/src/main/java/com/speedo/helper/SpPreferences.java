package com.speedo.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ketan on 1/9/16.
 */
public class SpPreferences {

    private static final String LOG_TAG = "SpPreferences";
    private static String TAG = SpPreferences.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "speedo_pref";
    private static final String KEY_FIRST_TIME = "first_time";
    private static final String KEY_MULTIPLE_DEVICES = "multiple_devices";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_ROLE_ID = "roleId";
    private static final String KEY_ROLE_NAME = "roleName";
    private static final String KEY_BUSINESS_ID = "businessId";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_LOGO = "logo";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LONG = "long";
    private static final String KEY_CITY = "city";
    private static final String KEY_LOCALITY = "locality";
    private static final String KEY_ADDRESS = "address";

    private static final String HOME_DATA_CHANGED_KEY = "home_data_changed";

    private static final String KEY_VIBRATE = "is_vibrate";
    private static final String KEY_PERMISSION_GRANTED = "is_permission_granted";

    public SpPreferences(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        BLog.d(TAG, "User login session modified!");
    }

    public void setHomeDataChanged(boolean isDataChanged){
        editor.putBoolean(HOME_DATA_CHANGED_KEY, isDataChanged);
        editor.commit();
    }

    public boolean isHomeDataChanged(){
        return pref.getBoolean(HOME_DATA_CHANGED_KEY, true);
    }

    public void setFirstTime(boolean isFirstTime){
        editor.putBoolean(KEY_FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public void setMultipleDevices(boolean isFirstTime){
        editor.putBoolean(KEY_MULTIPLE_DEVICES, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTime(){
        return pref.getBoolean(KEY_FIRST_TIME, true);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void clearPreferences(){
        editor.clear();
        editor.commit();
        setFirstTime(false);
    }

    public void setPermissionGranted(boolean isPermissionGranted){
        editor.putBoolean(KEY_PERMISSION_GRANTED, isPermissionGranted);
        editor.commit();
    }

    public boolean isPermissionGranted(){
        return pref.getBoolean(KEY_PERMISSION_GRANTED, false);
    }

    public void setIsVibrate(boolean isVibrate){
        editor.putBoolean(KEY_VIBRATE, isVibrate);
        editor.commit();
    }

    public boolean isVibrate(){
        return pref.getBoolean(KEY_VIBRATE, true);
    }

}
