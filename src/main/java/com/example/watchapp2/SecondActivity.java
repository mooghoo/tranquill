package com.example.watchapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

//import java.util.Timer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class SecondActivity extends Activity {
    private static TextView inexhale;
    private static boolean isClicked = true;
    private static int numClicks;
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button onButton = findViewById(R.id.button);
        ImageButton nextButton = findViewById(R.id.imageButton2);

        inexhale = findViewById(R.id.textView2);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButton.setText("STOP");
                isClicked=true;
                numClicks++;
                if (numClicks%2==0) {
                    onButton.setText("START");
                    isClicked=false;
                }
                if (isClicked) {
                    startTimer();
                }
                else {
                    timer.cancel();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(i);
            }
        });

    }
    public void startTimer() {
        timer = new Timer();

        initializeTimerTask();

        timer.schedule(timerTask, 3000, 3000); //
    }


    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        if(inexhale.getText().equals("IN")) {
                            inexhale.setText("OUT");
                        }
                        else if (inexhale.getText().equals("OUT")) {
                            inexhale.setText("IN");
                        }
                    }
                });
            }
        };
    }
}