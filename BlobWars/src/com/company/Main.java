package com.company;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
	// write your code here
        State state;
        int width=8;
        char board[][]= new char[width][width];
        board= new char[][]{
                {'B','_','_','_','_','_','_','B'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'R','_','_','_','_','_','_','R'}
        };
        char board1[][]={
                {'B','_','_','_','_','B'},
                {'_','_','_','_','_','_'},
                {'_','_','#','#','_','_'},
                {'_','_','#','#','_','_'},
                {'_','_','_','_','_','_'},
                {'R','_','_','_','_','R'}
        };
        char board2[][]={
                {'B','_','_','B'},
                {'_','_','_','_'},
                {'_','_','_','_'},
                {'R','_','_','R'}
        };
        char board3[][]={
                {'B','R','R','B'},
                {'R','R','R','R'},
                {'R','R','R','R'},
                {'R','R','_','R'}
        };
        char board4[][]={
                {'B','_','B'},
                {'_','_','_'},
                {'R','_','R'}
        };
        char board5[][]={
                {'B','_','_','B'},
                {'_','_','_','-'},
                {'_','_','_','_'},
                {'R','_','_','B'}
        };
        char board6[][]={
                {'B','_','_','_','B'},
                {'_','#','_','#','_'},
                {'_','_','_','_','_'},
                {'_','#','_','#','_'},
                {'R','_','_','_','R'}
        };
        Game game= new Game();
        game.board=new State(8,board);
        game.runGame();
    }
}
