package com.speedo.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.speedo.R;
import com.speedo.helper.AppUtil;
import com.speedo.helper.BLog;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fr.bmartel.speedtest.ISpeedTestListener;
import fr.bmartel.speedtest.SpeedTestError;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;

public class MainActivity extends AppCompatActivity {


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


    private static final String LOG_TAG = "MainActivity";
    SpeedTestSocket speedTestSocket = new SpeedTestSocket();
    SpeedTestTask speedTestTask;
    private Button startBtn;
    private TextView downBitTxt, downProgressTxt, upBitTxt, upProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        speedTestTask = new SpeedTestTask();
        initTest(speedTestSocket);
    }

    private void initViews(){
        downBitTxt = (TextView)findViewById(R.id.down_bit);
        downProgressTxt = (TextView)findViewById(R.id.down_progress);
        upBitTxt = (TextView)findViewById(R.id.up_bit);
        upProgress = (TextView)findViewById(R.id.up_progress);
        startBtn = (Button)findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speedTestTask.execute('D');
            }
        });
    }

    private void initTest(SpeedTestSocket sts){
        sts.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onDownloadPacketsReceived(long packetSize, float transferRateBps, float transferRateOps) {
                BLog.e(LOG_TAG, "onDownloadPacketsReceived - "+transferRateBps);
                BLog.e(LOG_TAG, "transferRateOps - "+transferRateOps);
//                BLog.e(LOG_TAG, "download transfer rate  : " + transferRateBps + " bit/second   | " +
//                        report.getTransferRateBit().divide(VALUE_PER_SECONDS, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
//                        + " Kbit/second  | " + report.getTransferRateBit().divide(MEGA_VALUE_PER_SECONDS) + " Mbit/second");
                resetSTT();
                speedTestTask.execute('U');
            }

            @Override
            public void onDownloadProgress(final float percent, final SpeedTestReport report) {
                BLog.e(LOG_TAG, "onDownloadProgress");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        downBitTxt.setText(""+ AppUtil.calculateSpeed(report.getTransferRateBit()) + "");
                        downProgressTxt.setText("Progress: "+percent+"%");
                    }
                });
            }

            @Override
            public void onDownloadError(SpeedTestError speedTestError, String errorMessage) {
                BLog.e(LOG_TAG, "onDownloadError - "+errorMessage);
            }

            @Override
            public void onUploadPacketsReceived(long packetSize, float transferRateBps, float transferRateOps) {
                BLog.e(LOG_TAG, "onUploadPacketsReceived - "+transferRateBps);
                resetSTT();
            }

            @Override
            public void onUploadError(SpeedTestError speedTestError, String errorMessage) {
                BLog.e(LOG_TAG, "onUploadError - "+errorMessage);
            }

            @Override
            public void onUploadProgress(final float percent, final SpeedTestReport report) {
                BLog.e(LOG_TAG, "onUploadProgress");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        upBitTxt.setText(""+AppUtil.calculateSpeed(report.getTransferRateBit()) + "");
                        upProgress.setText("Progress: "+percent+"%");
                    }
                });
            }
        });

    }

    public class SpeedTestTask extends AsyncTask<Character, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Character... params) {
            char type = params[0];
            if(type=='D') {
                speedTestSocket.startDownload("1.testdebit.info", 80, "/fichiers/10Mo.dat");
            } else if (type == 'U') {
                speedTestSocket.startUpload("1.testdebit.info", 80, "/", 1000000);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private void resetSTT(){
        speedTestSocket.closeSocket();
        speedTestSocket.forceStopTask();
        speedTestTask.cancel(true); //cancel async task
        speedTestSocket = new SpeedTestSocket(); //initialize new speed test socket for next test
        initTest(speedTestSocket); //initialize new test (listeners etc) for next test
        speedTestTask = new SpeedTestTask(); //initialize new async task
    }
}
