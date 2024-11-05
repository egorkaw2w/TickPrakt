package com.example.krestiki_prosti_gospodi_noliki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String THEME_KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setTheme(sharedPreferences.getBoolean(THEME_KEY, false) ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
        setContentView(R.layout.activity_main);

        Button btnViewStats = findViewById(R.id.btn_view_stats);
        Button btnStartGame = findViewById(R.id.btn_start_game);
        Button btnChangeTheme = findViewById(R.id.btn_change_theme);

        btnViewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameModeActivity.class);
                startActivity(intent);
            }
        });

        btnChangeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentTheme = sharedPreferences.getBoolean(THEME_KEY, false);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(THEME_KEY, !currentTheme);
                editor.apply();
                recreate();
            }
        });
    }
}