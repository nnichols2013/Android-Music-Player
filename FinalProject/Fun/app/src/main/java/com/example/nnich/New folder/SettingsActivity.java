package com.example.nnich.fun;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;

    private int redVal = 0;
    private int greenVal = 0;
    private int blueVal = 0;

    public int finalColor = 0;

    private ImageView preview;

    private ColorDrawable mColorDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Settings");
        setContentView(R.layout.activity_settings);

        red = findViewById(R.id.seekBarRed);
        green = findViewById(R.id.seekBarGreen);
        blue = findViewById(R.id.seekBarBlue);

        preview = findViewById(R.id.previewImage);

        final Intent intent = new Intent(SettingsActivity.this,FunActivity.class);

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redVal = red.getProgress();
                preview.setBackgroundColor(getIntFromColor(redVal,greenVal,blueVal));
                finalColor = getIntFromColor(redVal,greenVal,blueVal);
                intent.putExtra("F",finalColor);
                setResult(finalColor,intent);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenVal = green.getProgress();
                preview.setBackgroundColor(getIntFromColor(redVal,greenVal,blueVal));
                finalColor = getIntFromColor(redVal,greenVal,blueVal);
                intent.putExtra("F",finalColor);
                setResult(finalColor,intent);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueVal = blue.getProgress();
                preview.setBackgroundColor(getIntFromColor(redVal,greenVal,blueVal));
                finalColor = getIntFromColor(redVal,greenVal,blueVal);
                intent.putExtra("F",finalColor);
                setResult(finalColor,intent);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
        public int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

        @Override
        public void onDestroy() {
            super.onDestroy();


            finish();
        }

}