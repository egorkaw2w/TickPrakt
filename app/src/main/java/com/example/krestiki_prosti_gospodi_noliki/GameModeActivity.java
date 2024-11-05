package com.example.krestiki_prosti_gospodi_noliki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        Button btnStartMultiplayer = findViewById(R.id.btn_start_multiplayer);
        Button btnBackToMain = findViewById(R.id.btn_back_to_main);
        Button btnStartBot = findViewById(R.id.btn_start_bot);

        btnStartMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModeActivity.this, MultiplayerGameActivity.class);
                startActivity(intent);
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnStartBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModeActivity.this, BotGameActivity.class);
                startActivity(intent);
            }
        });
    }
}