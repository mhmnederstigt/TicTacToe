package com.example.gebruiker.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    public Tile[][] board;

    public Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

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
        gameOver = false;
    }

    public Tile draw(int row, int column) {
       if (board[row][column] == Tile.BLANK){
           if (playerOneTurn){
               board[row][column] = Tile.CROSS;
               playerOneTurn = false;
               movesPlayed += 1;
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
}
