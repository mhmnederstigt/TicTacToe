package com.example.gebruiker.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Game game;
    Button[] tilesArray = new Button[9];
    TextView turn;
    TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = (TextView)findViewById(R.id.turn);
        message = (TextView)findViewById(R.id.message);

        tilesArray[0] = (Button)findViewById(R.id.tile01);
        tilesArray[1] = (Button)findViewById(R.id.tile02);
        tilesArray[2] = (Button)findViewById(R.id.tile03);
        tilesArray[3] = (Button)findViewById(R.id.tile04);
        tilesArray[4] = (Button)findViewById(R.id.tile05);
        tilesArray[5] = (Button)findViewById(R.id.tile06);
        tilesArray[6] = (Button)findViewById(R.id.tile07);
        tilesArray[7] = (Button)findViewById(R.id.tile08);
        tilesArray[8] = (Button)findViewById(R.id.tile09);

        game = new Game();

        // Restore board if necessary
        if (savedInstanceState != null) {
             game = (Game) savedInstanceState.getSerializable("previousGame");

            // Display board in UI
            int n = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++) {
                    setTile(game.board[i][j], tilesArray[n]);
                    n = n + 1;
                }
            }

            // Display who's turn it is
            if (game.playerOneTurn){
                turn.setText("Player X, it's your turn!");
                message.setText("");
            }
            else {
                turn.setText("Player O, it's your turn!");
                message.setText("");
            }
        }
    }

    // Displays the tile's state in the UI according to board
    public void setTile(Tile tile, Button button){


        message = (TextView)findViewById(R.id.message);
        switch(tile) {
            case CROSS:
                button.setText("X");
                message.setText("");
                break;
            case CIRCLE:
                button.setText("O");
                message.setText("");
                break;
            case INVALID:
                message.setText("INVALID");
                break;
            default:
                button.setText("");
                break;
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save state of game
        savedInstanceState.putSerializable("previousGame", game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.reset:
            game = new Game();
            for (int n=0; n<9; n++){
                Button reset = tilesArray[n];
                reset.setText("");
            }

            TextView turn = (TextView)findViewById(R.id.turn);
            turn.setText("Player X, you may start!");
            TextView message = (TextView)findViewById(R.id.message);
            message.setText("");
            return(true);
        case R.id.exit:
            finish();
        return(true);

    }
        return(super.onOptionsItemSelected(item));
    }

    public void tileClicked(View view) {
       int id = view.getId();
       Tile tile;

       switch (id) {
           case R.id.tile01:
               tile = game.draw(0, 0);
               setTile(tile, tilesArray[0]);
               break;
           case R.id.tile02:
               tile = game.draw(0, 1);
               setTile(tile, tilesArray[1]);
               break;
           case R.id.tile03:
               tile = game.draw(0, 2);
               setTile(tile, tilesArray[2]);
               break;
           case R.id.tile04:
               tile = game.draw(1, 0);
               setTile(tile, tilesArray[3]);
               break;
           case R.id.tile05:
               tile = game.draw(1, 1);
               setTile(tile, tilesArray[4]);
               break;
           case R.id.tile06:
               tile = game.draw(1, 2);
               setTile(tile, tilesArray[5]);
               break;
           case R.id.tile07:
               tile = game.draw(2, 0);
               setTile(tile, tilesArray[6]);
               break;
           case R.id.tile08:
               tile = game.draw(2, 1);
               setTile(tile, tilesArray[7]);
               break;
           case R.id.tile09:
               tile = game.draw(2, 2);
               setTile(tile, tilesArray[8]);
               break;
           // what to do here?
           default:
                tile = Tile.INVALID;
                break;
       }

       if (game.playerOneTurn){
           turn.setText("Player X, it's your turn!");
           }
       else {
           turn.setText("Player O, it's your turn!");
       }
    }
}
