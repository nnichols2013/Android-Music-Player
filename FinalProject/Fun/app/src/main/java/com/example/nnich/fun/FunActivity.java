package com.example.nnich.fun;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class FunActivity extends AppCompatActivity {

    private ImageButton mPlayPauseButton;
    private ImageButton fileButton;
    private ImageButton settingsButton;
    private ImageButton SkipBackButton;
    private ImageButton SkipForwardButton;
    private SeekBar mVolumeSlider;
    private SeekBar mPositionSlider;
    private SeekBar mPitchSlider;
    private SeekBar mSpeedSlider;
    private ImageView mAlbumArt;
    private TextView mTextView;
    private TextView mSliders;

    private ImageView mBlack;
    private ImageView mBlack1;
    private ImageView mBlack2;
    private ImageView mBlack3;
    private ImageView mBlack4;

    private MediaPlayer mMediaPlayer;

    private float mVolume = 1.0f;
    private float mPitch = 1.0f;
    private float mRate = 1.0f;

    private boolean paused;
    private static int FILE_SELECT_CODE = 0;
    private static int SETTINGS_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);

        paused = false;
        mMediaPlayer = new MediaPlayer();

        mPlayPauseButton = findViewById(R.id.play_pause);
        fileButton = findViewById(R.id.file_button);
        settingsButton = findViewById(R.id.settings_button);
        SkipBackButton = findViewById(R.id.skip_back);
        SkipForwardButton = findViewById(R.id.skip_forward);
        mVolumeSlider = findViewById(R.id.slider_volume);
        mPositionSlider = findViewById(R.id.slider_position);
        mPitchSlider = findViewById(R.id.slider_pitch);
        mSpeedSlider = findViewById(R.id.slider_speed);
        mAlbumArt = findViewById(R.id.album_art);
        mTextView = findViewById(R.id.media_name);
        mSliders = findViewById(R.id.metadata_text);

        mBlack = findViewById(R.id.top_filler);
        mBlack1 = findViewById(R.id.bottom_filler);
        mBlack2 = findViewById(R.id.sine_wave);
        mBlack3 = findViewById(R.id.black_filler);
        mBlack4 = findViewById(R.id.black_filler2);

        mPlayPauseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (paused) {
                            Toast.makeText(FunActivity.this,
                                    R.string.toast_play,
                                    Toast.LENGTH_SHORT).show();
                            mPlayPauseButton.setImageResource(R.drawable.pause_icon);
                            paused = false;
                            try
                            {mMediaPlayer.start();}
                            catch(Exception e)
                            {}
                        } else {
                            Toast.makeText(FunActivity.this,
                                    R.string.toast_pause,
                                    Toast.LENGTH_SHORT).show();
                            mPlayPauseButton.setImageResource(R.drawable.play_icon);
                            paused = true;
                            try
                            {mMediaPlayer.pause();}
                            catch(Exception e)
                            {}
                        }
                    }
                });

        mVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                    mVolume = (float) mVolumeSlider.getProgress() / 100;
                    mMediaPlayer.setVolume(mVolume,mVolume);
                String newstring = "Volume: " + mVolumeSlider.getProgress() + "\n" +
                        "\n" +
                        "Pitch: " + (mPitchSlider.getProgress() * 0.1) + "\n" +
                        "Speed: " + (mSpeedSlider.getProgress() * 0.1);
                    mSliders.setText(newstring);
                }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mPositionSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                int getPosition = mPositionSlider.getProgress();
                mMediaPlayer.seekTo(getPosition * mMediaPlayer.getDuration() / 100);
                String newstring = "Volume: " + mVolumeSlider.getProgress() + "\n" +
                        "\n" +
                        "Pitch: " + (mPitchSlider.getProgress() * 0.1) + "\n" +
                        "Speed: " + (mSpeedSlider.getProgress() * 0.1);
                mSliders.setText(newstring);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mPitchSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                PlaybackParams p = new PlaybackParams();
                p.setPitch((float) (mPitchSlider.getProgress() * 0.1));
                try{mMediaPlayer.setPlaybackParams(p);}catch(Exception e){}
                String newstring = "Volume: " + mVolumeSlider.getProgress() + "\n" +
                        "\n" +
                        "Pitch: " + (mPitchSlider.getProgress() * 0.1) + "\n" +
                        "Speed: " + (mSpeedSlider.getProgress() * 0.1);
                mSliders.setText(newstring);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSpeedSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                PlaybackParams p = new PlaybackParams();
                p.setSpeed((float) (mSpeedSlider.getProgress() * 0.1));
                try{mMediaPlayer.setPlaybackParams(p);}catch(Exception e){}
                String newstring = "Volume: " + mVolumeSlider.getProgress() + "\n" +
                        "\n" +
                        "Pitch: " + (mPitchSlider.getProgress() * 0.1) + "\n" +
                        "Speed: " + (mSpeedSlider.getProgress() * 0.1);
                mSliders.setText(newstring);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SkipBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition()-10000);
            }
        });

        SkipForwardButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition()+10000);
             }
         });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FunActivity.this,SettingsActivity.class);
                startActivityForResult(intent,SETTINGS_CODE);
            }
            }
        );

        fileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("audio/mpeg");
                        intent.addCategory(Intent.CATEGORY_OPENABLE);

                        try {
                            startActivityForResult(
                                    Intent.createChooser(intent, "Select a File to Upload"),
                                    FILE_SELECT_CODE);
                        } catch (android.content.ActivityNotFoundException ex) {
                            // Potentially direct the user to the Market with a Dialog
                            Toast.makeText(FunActivity.this,
                                    "Please install a File Manager.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0): {
                if (resultCode == RESULT_CANCELED) {
                    // action cancelled
                }
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        File f = new File("" + uri);
                        mTextView.setText(f.getName());

                        //start music
                        mMediaPlayer = MediaPlayer.create(
                                FunActivity.this,
                                uri);

                        mMediaPlayer.setVolume(mVolume, mVolume);
                        mMediaPlayer.start();
                    } catch (Exception e) {
                    }
                }
            }
            case (1): {
                Intent mIntent = getIntent();
                int i = mIntent.getIntExtra("F",0xFF00FF00);
                mBlack.setBackgroundColor(resultCode);
                mBlack1.setBackgroundColor(resultCode);
                mBlack2.setBackgroundColor(resultCode);
                mBlack3.setBackgroundColor(resultCode);
                mBlack4.setBackgroundColor(resultCode);
            }
        }
    }
}
