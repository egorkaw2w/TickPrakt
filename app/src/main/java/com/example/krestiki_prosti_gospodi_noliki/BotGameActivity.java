package com.example.krestiki_prosti_gospodi_noliki;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class BotGameActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String THEME_KEY = "theme";
    private static final String WINS_KEY = "wins";
    private static final String LOSSES_KEY = "losses";
    private static final String DRAWS_KEY = "draws";

    private Button[] buttons;
    private boolean playerTurn = true;
    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setTheme(sharedPreferences.getBoolean(THEME_KEY, false) ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
        setContentView(R.layout.activity_multiplayer_game);

        buttons = new Button[9];
        for (int i = 0; i < 9; i++) {
            String buttonID = "btn_cell_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(new CellClickListener(i));
        }
    }

    private class CellClickListener implements View.OnClickListener {
        private int cellIndex;

        public CellClickListener(int cellIndex) {
            this.cellIndex = cellIndex;
        }

        @Override
        public void onClick(View v) {
            if (buttons[cellIndex].getText().toString().equals("")) {
                buttons[cellIndex].setText("X");
                roundCount++;

                if (checkForWin()) {
                    Toast.makeText(BotGameActivity.this, "Игрок (X) победил!", Toast.LENGTH_SHORT).show();
                    updateStats(true);
                    resetBoard();
                } else if (roundCount == 9) {
                    Toast.makeText(BotGameActivity.this, "Ничья!", Toast.LENGTH_SHORT).show();
                    updateStats(false);
                    resetBoard();
                } else {
                    botMove();
                }
            }
        }
    }

    private void botMove() {
        Random random = new Random();
        int botIndex;
        do {
            botIndex = random.nextInt(9);
        } while (!buttons[botIndex].getText().toString().equals(""));

        buttons[botIndex].setText("O");
        roundCount++;

        if (checkForWin()) {
            Toast.makeText(BotGameActivity.this, "Бот (O) победил!", Toast.LENGTH_SHORT).show();
            updateStats(false);
            resetBoard();
        } else if (roundCount == 9) {
            Toast.makeText(BotGameActivity.this, "Ничья!", Toast.LENGTH_SHORT).show();
            updateStats(false);
            resetBoard();
        }
    }

    private boolean checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText().toString();
        }

        for (int[] win : new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}) {
            if (board[win[0]].equals(board[win[1]]) && board[win[1]].equals(board[win[2]]) && !board[win[0]].equals("")) {
                return true;
            }
        }
        return false;
    }

    private void updateStats(boolean playerWins) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (playerWins) {
            int wins = sharedPreferences.getInt(WINS_KEY, 0);
            editor.putInt(WINS_KEY, wins + 1);
        } else {
            int losses = sharedPreferences.getInt(LOSSES_KEY, 0);
            editor.putInt(LOSSES_KEY, losses + 1);
        }
        editor.apply();
    }

    private void resetBoard() {
        for (Button button : buttons) {
            button.setText("");
        }
        roundCount = 0;
        playerTurn = true;
    }
}