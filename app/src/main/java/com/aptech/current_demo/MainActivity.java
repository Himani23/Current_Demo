package com.aptech.current_demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    music=MediaPlayer.create(MainActivity.this,R.raw.guitar_loud);
                    music.start();
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });


        th.start();

      /*  try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(music.isPlaying()){ //Must check if it's playing, otherwise it may be a NPE
            music.pause(); //Pauses the sound
            //ur.removeCallbacks(myRunnable);
            finish();
        }
    }


    }

