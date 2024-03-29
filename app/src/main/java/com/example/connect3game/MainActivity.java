package com.example.connect3game;


import androidx.appcompat.app.AppCompatActivity;

import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1:red 2:empty
    int activeplayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameActive==true) {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1000);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yelllow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive=false;
                    String winner = "";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view)
    {
        Button playAgainButton=(Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView)findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout mygridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<mygridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)mygridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activeplayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
