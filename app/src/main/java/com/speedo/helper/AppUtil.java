package com.speedo.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.speedo.R;
import com.speedo.activity.MainActivity;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by Ketan on 4/10/16.
 */

public class AppUtil{


    /**
     * default scale for BigDecimal.
     */
    private static final int DEFAULT_SCALE = 4;

    /**
     * default rounding mode for BigDecimal.
     */
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    /**
     * conversion const for per second value.
     */
    private static final BigDecimal VALUE_PER_SECONDS = new BigDecimal(1000);

    /**
     * conversion const for M per second value.
     */
    private static final BigDecimal MEGA_VALUE_PER_SECONDS = new BigDecimal(1000000);


    private static final String LOG_TAG = "AppUtil";
    static DisplayMetrics displayMetrics;
    static android.app.AlertDialog no_internet_alert = null;

    public static void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener,
                                           DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    public static ProgressDialog createProgressDialog(Context context, String title, String msg, boolean cancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (title != null && title.length() > 0) {
            progressDialog.setTitle(title);
        }
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(cancelable);
        progressDialog.setCanceledOnTouchOutside(cancelable);
        return progressDialog;
    }

    public static void goToHome(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void goToIntro(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void goToLogin(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static boolean isWifi(Context context){
        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean isCellular(Context context){
        final ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static String extractNumber(final String str) {
        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }
        return sb.toString();
    }

    public static void setCustomBackgroundDrawable(Context context, View view, int drawable){
        if(Build.VERSION.SDK_INT >= 16){
            view.setBackground(context.getResources().getDrawable(drawable));
        }else{
            view.setBackgroundDrawable(context.getResources().getDrawable(drawable));
        }
    }

    @SuppressWarnings("MissingPermission")
    public static String getPlayStoreEmailId(Context context) {
        String strGmail = null;
        int count = 0;
        try {
            Account[] accounts = AccountManager.get(context).getAccounts();
//            Log.e("PIKLOG", "Size: " + accounts.length);
            for (Account account : accounts) {

                String possibleEmail = account.name;
                String type = account.type;

                if (type.equals("com.google")) {
                    count++;
                    if(count == 1){
                        strGmail = possibleEmail;
//                        Log.e("PIKLOG", "Emails: " + strGmail);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strGmail;
    }

    public static String handleNotificationCount(long count){
        if(count > 0 && count < 20){
            return String.valueOf(count);
        }else if(count > 20 & count <= 30){
            return "20+";
        }else if(count > 30 & count <= 40){
            return "30+";
        }else if(count > 40 & count <= 50){
            return "40+";
        }else {
            return "50+";
        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static int[] calculateImageSize(Context context, float image_width_height_ratio, int view_padding, float imageView_screen_ratio) {
        int[] scaledWidthHeight = {-1,-1};
        try {
            int padding = dpToPx((Activity) context, view_padding);
            int new_width = (int) (((getDisplaySize((Activity) context)[0]) * imageView_screen_ratio) - padding);

            int new_height = (int) ((float) new_width * (image_width_height_ratio));
            scaledWidthHeight[0] = new_width;
            scaledWidthHeight[1] = new_height;
        } catch (Exception e) {
        }
        return scaledWidthHeight;
    }

    public static int dpToPx(Activity activity, int dp) {
        if (displayMetrics == null) displayMetrics = activity.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }


    public static int[] getDisplaySize(Activity mActivity) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int[] arr = new int[2];
        arr[0] = width;
        arr[1] = height;
        return arr;
    }

    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean check_internet(Context context, boolean should_notify) {
        boolean is_connected = check_internet_connection(context);
        if (!is_connected && should_notify) {
            show_no_internet((Activity) context);
        }
        return is_connected;
    }

    public static Boolean check_internet_connection(Context activity) {
        ConnectivityManager cm = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return Boolean.valueOf(isConnected);
    }

    public static void show_no_internet(Activity activity) {
        try {
            no_internet_alert = (new android.app.AlertDialog.Builder(activity)).create();
            View e = activity.getLayoutInflater().inflate(R.layout.nointernet_layout_full, (ViewGroup)null);
            no_internet_alert.setView(e);
            no_internet_alert.requestWindowFeature(1);
            WindowManager.LayoutParams wmlp = no_internet_alert.getWindow().getAttributes();
            wmlp.gravity = 48;
            no_internet_alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    if(no_internet_alert != null) {
                        no_internet_alert.dismiss();
                        no_internet_alert = null;
                    }

                }
            });
            no_internet_alert.show();
        } catch (Exception var3) {
            BLog.e("Common", "Exception - " + var3.toString(), var3);
        }

    }

    public static String loadJSONFromAsset(Context context, String json_file) {
        String json;
        try {
            InputStream is = context.getAssets().open(json_file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            Log.e(LOG_TAG, " Exception loadJSONFromAsset - ", ex);
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void changeStatusBarColor(Activity activity, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    public static double roundOff(double value){
        double finalValue = Math.round( value * 100.0 ) / 100.0;
        return finalValue;
    }

    public static String calculateSpeed(BigDecimal speed){
        MathContext mc = new MathContext(2);
        BigDecimal realValue = speed.round(mc);
        double sizeIBytes = realValue.divide(new BigDecimal(8)).doubleValue();

        double fileSizeInBytes = sizeIBytes;
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        double fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        double fileSizeInMB = fileSizeInKB / 1024;

        if(realValue.compareTo(new BigDecimal(1000)) < 0){
            return "" + realValue+ " B/s";
        }else if(realValue.compareTo(new BigDecimal(1000)) > 0 && realValue.compareTo(new BigDecimal(1000*1000)) < 0){
            return "" + (roundOff(fileSizeInBytes)) + " Kb/s";
        }else {
            return "" + (roundOff(fileSizeInKB)) + " Mb/s";
        }
    }

}
