package com.example.mediaplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST=1888;
    ImageView photo;
    Button click, player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button)findViewById(R.id.camera);
        player=(Button)findViewById(R.id.music);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(MainActivity.this,music.class);
                startActivity(next);
            }
        });

    }
    protected void onActivityResult(int rqc, int rc, Intent data) {
        super.onActivityResult(rqc, rc, data);
        if (rqc == CAMERA_REQUEST) {
            Bitmap snap = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(snap);
        }
    }

}
