package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class music extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer player;
    ImageButton playerButton, stopButton, resetButton;
    boolean play_reset = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setSelected(true);

        playerButton = (ImageButton) this.findViewById(R.id.play);
        playerButton.setOnClickListener(this);
        stopButton = (ImageButton) this.findViewById(R.id.stop);
        stopButton.setOnClickListener(this);
        resetButton = (ImageButton) this.findViewById(R.id.reset);
        resetButton.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
// If the user presses the play/pause button
// if no player instance is available, then create a media player first
                if (play_reset) {
                    play_reset = false;
                    player = MediaPlayer.create(this, R.raw.jarico_retrovi);
                    player.setLooping(false); // Set looping
                }
                playPause();
                break;
            case R.id.stop:
                if (!play_reset){
// If the user presses the stop button
                    player.stop();
// change the image of the play button to play
                    playerButton.setImageResource(R.drawable.ic_play);
                    Toast.makeText(this, "Music stopped", Toast.LENGTH_SHORT).show();
                    try {
                        player.prepare();
                    } catch (IllegalStateException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.reset:
                if (!play_reset){
// If the user presses the reset button
                    player.reset();
// change the image of the play button to play
                    playerButton.setImageResource(R.drawable.ic_play);
                    Toast.makeText(this, "Replay", Toast.LENGTH_SHORT).show();
// Release media instance to system
                    player.release();
                    play_reset = true;
                    break;
                }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        player.reset();
// change the image of the play button to play
        playerButton.setImageResource(R.drawable.ic_play);
        Toast.makeText(this, "successfully playing", Toast.LENGTH_SHORT).show();
// Release media instance to system
        player.release();
        play_reset = true;
    }

    // Toggle between the play and pause
    private void playPause() {
        // if the music is playing then pause the music playback
        if(player.isPlaying()) {
            player.pause();
// change the image of the play button to play
            playerButton.setImageResource(R.drawable.ic_play);
            Toast.makeText(this, "Music Paused", Toast.LENGTH_SHORT).show();
        }
// Music is paused, start, or resume playback
        else {
// change the image of the play button to pause

            playerButton.setImageResource(R.drawable.ic_pause);
            player.start();
            Toast.makeText(this, "Successfully playing", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
