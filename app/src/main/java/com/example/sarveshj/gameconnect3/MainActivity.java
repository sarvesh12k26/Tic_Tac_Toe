package com.example.sarveshj.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int ansrupee;
    int anseuro;
    int firstplayer=0;
    boolean gameIsActive=true;
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    TextView rupeeValueText;
    TextView euroValueText;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive) {
            Log.i("Activeplayer:",Integer.toString(activePlayer));
            gameState[tappedCounter]=activePlayer;

            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.euro);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.rupee);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).rotation(720).setDuration(200);
            for(int[] winningPosition:winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2){
                    //Someone has won
                    gameIsActive=false;
                    String winner;

                    if(gameState[winningPosition[0]]==0){
                        winner="Euro";
                        euroValueText=(TextView) findViewById(R.id.euroValueText);
                        anseuro = Integer.parseInt(euroValueText.getText().toString());
                        anseuro+=1;
                        euroValueText.setText(Integer.toString(anseuro));
                    }else{
                        winner="Rupee";
                        rupeeValueText=(TextView) findViewById(R.id.rupeeValueText);
                        ansrupee = Integer.parseInt(rupeeValueText.getText().toString());
                        ansrupee+=1;
                        rupeeValueText.setText(Integer.toString(ansrupee));
                    }

                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner +" has won !");

                    LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE); break;
                }else {
                    boolean gameIsOver=true;
                    for(int counterState : gameState){
                        if(counterState==2) gameIsOver=false;
                    }
                    if(gameIsOver){

                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It is a draw!");
                        LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        /*GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
                        gridLayout.setVisibility(View.INVISIBLE);*/
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        gameIsActive=true;

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        //gridLayout.setVisibility(View.VISIBLE);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activePlayer=0;
    }
}
