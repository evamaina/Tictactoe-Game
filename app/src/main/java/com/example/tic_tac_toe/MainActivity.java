package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red

    int activePlayer = 0;
    boolean gameIsActive = true;

    // 2 means uPlayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter =(ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            }else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotationBy(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    // someone has won
                    gameIsActive = false;

                    String winner = "red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "yellow";
                    }
                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText(" It's a draw!");
                        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);

                    }
                }

            }
        }

    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0 ; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i= 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }
}
