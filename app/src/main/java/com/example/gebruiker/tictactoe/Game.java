package com.example.gebruiker.tictactoe;

import android.util.Log;
import android.widget.Button;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    public Tile[][] board;

    public Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    public Boolean gameOver;

    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++) {
                board[i][j] = Tile.BLANK;
            }
        }

        playerOneTurn = true;
        gameOver = false;
        movesPlayed = 0;
     }

    // Check if the requested move is valid, and draws on board
    public Tile draw(int row, int column) {
        Log.d("board", String.valueOf(board[row][column]));
        Log.d("row", String.valueOf(row));
        Log.d("column", String.valueOf(column));

       if (board[row][column] == Tile.BLANK){

           if (playerOneTurn){
               board[row][column] = Tile.CROSS;
               playerOneTurn = false;
               movesPlayed += 1;
               checkGameOver();
               return Tile.CROSS;
           }
           else {
               board[row][column] = Tile.CIRCLE;
               playerOneTurn = true;
               movesPlayed += 1;
               return Tile.CIRCLE;
           }
       }
       else {
           return Tile.INVALID;
       }
    }

    // Returns the row, column and tileId of a valid random move
    public int[] randMove() {
        int[] rowCol  = {0,0,0};
        int random = (int) (Math.random() * 8);
        int count = -1;

        for(int i=0; i<BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                count += 1;
                if (count == random) {
                    if (board[i][j] == Tile.BLANK) {
                        rowCol[0] = i;
                        rowCol[1] = j;
                        rowCol[2] = random;

                    } else {
                        rowCol = randMove();
                        return rowCol;
                    }
                }
            }
        }
        return rowCol;
    }

    private void checkGameOver(){

        for(int i=0; i<BOARD_SIZE; i++) {
            //vertical
            if (board[0][i] != Tile.BLANK && board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                gameOver = true;
            }
            //horizontal
            if (board[i][0] != Tile.BLANK &&  board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                gameOver = true;
            }
        }
        //diagonal down
        if (board[0][0] != Tile.BLANK && (board[0][0] == board[1][1] && board[0][0] == board[2][2])){
            gameOver = true;
        }
        // diagonal up
        else if (board[2][0] != Tile.BLANK && (board[2][0] == board[1][1] && board[2][0] == board[0][2])){
            gameOver = true;
        }
        // entire board filled
        else if (movesPlayed == 9){
            gameOver = true;
        }
    }

}
