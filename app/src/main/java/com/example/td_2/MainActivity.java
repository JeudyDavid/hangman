package com.example.td_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.start_game);

        View.OnClickListener bClick = new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Intent gameIntent = new Intent(MainActivity.this,
                                        GameActivity.class);

                startActivity(gameIntent);
            }
        };

        mButton.setOnClickListener(bClick);
    }

//    @Override
//    protected onClick(View v){
//
//    }
}