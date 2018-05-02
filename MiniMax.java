import java.util.*;
import java.io.*;

class Position {
    int xPos, yPos;
    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
}


class pointScore{
    int score;
    Position pos;
    pointScore(int score, Position pos){
        this.score = score;
        this.pos = pos;
    }
}

public class MiniMax{
    public Position bestMove;
    private char[][] board;
    private char currentPlayer;
    private int numTurns;
    private ArrayList<pointScore> rootsChildrenScores;

    public boolean winning(char player, char[][] newGameBoard){
        if(((newGameBoard[0][0] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][2] == player)) ||  // diagonal left
        ((newGameBoard[0][2] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][0] == player)) ||     // diagonal right
        ((newGameBoard[0][0] == player) && (newGameBoard[0][1] == player) && (newGameBoard[0][2] == player)) ||     // Top horizontal
        ((newGameBoard[1][0] == player) && (newGameBoard[1][1] == player) && (newGameBoard[1][2] == player)) ||     // Mid horizontal
        ((newGameBoard[2][0] == player) && (newGameBoard[2][1] == player) && (newGameBoard[2][2] == player)) ||     // Bot horizontal
        ((newGameBoard[0][0] == player) && (newGameBoard[1][0] == player) && (newGameBoard[2][0] == player)) ||     // Left Vertical
        ((newGameBoard[0][1] == player) && (newGameBoard[1][1] == player) && (newGameBoard[2][1] == player)) ||     // Mid Vertical
        ((newGameBoard[0][2] == player) && (newGameBoard[1][2] == player) && (newGameBoard[2][2] == player))){      // Right Vertical
            return true; 
        }
        return false;
    }

    public ArrayList<Position> getEmptySpots(char[][] currentBoard){
        ArrayList<Position> spotsArray = new ArrayList<Position>(); 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(currentBoard[i][j] == ' '){
                    Position newPoint = new Position(i, j);
                    spotsArray.add(newPoint);
                }
            }
        }
        return spotsArray;
    }

    public int returnMin(ArrayList<Integer> list){
        int min = 10000;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public int returnMax(ArrayList<Integer> list){
        int max = -10000;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    public Position returnBestMove(){
        int max = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScores.size(); ++i) { 
            if (max < rootsChildrenScores.get(i).score) {
                max = rootsChildrenScores.get(i).score;
                best = i;
            }
        }

        return rootsChildrenScores.get(best).pos;   
    }

    public int minimaxify(int level, char currentPlayer, char[][] newBoard){
        char[][] newGameBoard = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                newGameBoard[i][j] = newBoard[i][j];
            }
        }
        
        if(winning('O', newGameBoard)) return 1;
        if(winning('X', newGameBoard)) return -1;
        

        ArrayList<Position> availableSpots = new ArrayList<Position>(getEmptySpots(newGameBoard));    
        if(availableSpots.isEmpty()) return 0;

        ArrayList<Integer> scores = new ArrayList<Integer>();

        for(int i = 0; i < availableSpots.size(); i++){
            Position currentSpot = availableSpots.get(i);

            if(currentPlayer == 'O'){
                newGameBoard[currentSpot.xPos][currentSpot.yPos] = 'O';
                int currentScore = minimaxify(level + 1, 'X', newGameBoard);
                scores.add(currentScore);
                if(level == 0){
                    this.rootsChildrenScores.add(new pointScore(currentScore, currentSpot ));
                }
            } else if (currentPlayer =='X'){
                newGameBoard[currentSpot.xPos][currentSpot.yPos] = 'X';
                scores.add(minimaxify(level + 1, 'O', newGameBoard));
            }
            newGameBoard[currentSpot.xPos][currentSpot.yPos] = ' ';            
        }
        return currentPlayer == 'O' ? returnMax(scores) : returnMin(scores);
    }

    public void callMiniMax(int level, char currentPlayer, char[][] newBoard){
        this.rootsChildrenScores = new ArrayList<pointScore>();
        minimaxify(level, currentPlayer, newBoard);    
    }

    public MiniMax(char[][] board, char player){
        this.currentPlayer = player;
        this.board = board;
        callMiniMax(0, this.currentPlayer, this.board);
        this.bestMove = returnBestMove();
    }
}