package com.example.mausam.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar eggTimerSeekBar;

    TextView eggTimerTextView;

    boolean counterIsActive=false;

    Button goButton ;

   CountDownTimer countDownTimer;

    public void updateTimer(int leftSeconds) {

        int minutes = (int) leftSeconds / 60;

        int second = leftSeconds - minutes * 60;


        String secondString = Integer.toString(second);

        if (second <= 9) {

            secondString = "0"+secondString;

        }
        eggTimerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void reStart(){
        eggTimerTextView.setText("0:30");
        eggTimerSeekBar.setProgress(30);
        countDownTimer.cancel();
        goButton.setText("Go!");
        eggTimerSeekBar.setEnabled(true);
        counterIsActive =false;
    }

    public void timerCounter(View view) {

        if (counterIsActive == false) {

            counterIsActive = true;

            eggTimerSeekBar.setEnabled(false);

            goButton.setText("Stop");

         countDownTimer =  new CountDownTimer(eggTimerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long miliSecondUntilFinished) {

                    updateTimer((int) miliSecondUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    eggTimerTextView.setText("0:00");

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                    mediaPlayer.start();

                    reStart();

                }
            }.start();
        }else {

            reStart();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        eggTimerSeekBar = (SeekBar) findViewById(R.id.eggTimerSeekBar);

        eggTimerSeekBar.setMax(600);

        eggTimerSeekBar.setProgress(30);

        eggTimerTextView = (TextView) findViewById(R.id.eggTimerTextView);

        goButton =(Button)findViewById(R.id.eggTimerBtn);

        eggTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                updateTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
