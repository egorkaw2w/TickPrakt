package com.example.krestiki_prosti_gospodi_noliki;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String WINS_KEY = "wins";
    private static final String LOSSES_KEY = "losses";
    private static final String DRAWS_KEY = "draws";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setTheme(sharedPreferences.getBoolean("theme", false) ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
        setContentView(R.layout.activity_stats);

        TextView tvWins = findViewById(R.id.tv_wins);
        TextView tvLosses = findViewById(R.id.tv_losses);
        TextView tvDraws = findViewById(R.id.tv_draws);
        Button btnBackToMain = findViewById(R.id.btn_back_to_main);

        int wins = sharedPreferences.getInt(WINS_KEY, 0);
        int losses = sharedPreferences.getInt(LOSSES_KEY, 0);
        int draws = sharedPreferences.getInt(DRAWS_KEY, 0);

        tvWins.setText("Победы: " + wins);
        tvLosses.setText("Поражения: " + losses);
        tvDraws.setText("Ничьи: " + draws);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}