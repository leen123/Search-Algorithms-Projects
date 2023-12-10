package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class State {
    private int width;
    private char[][] board;
    private int numB,numR;

    public State(int width) {
        this.width = width;
        board = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = '_';
            }
        }
    }
    public State(int width,char[][] board) {
        this.width = width;
        this.board=new char[width][width];
        for (int i = 0; i < width; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, this.width);
        }
        score();
    }
    public State(State state) {
        this.width = state.width;
        this.numB=state.numB;
        this.numR=state.numR;
        board = new char[width][width];
        for (int i = 0; i < width; i++) {
            System.arraycopy(state.board[i], 0, board[i], 0, width);
        }

    }


    public int getWidth() {
        return this.width;
    }

    public int getNumB() {
        return numB;
    }

    public int getNumR() {
        return numR;
    }

    public char[][] getBoard() {
        return board;
    }

    void score(){
        numR=numB=0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if(board[i][j]=='R') numR++;
                else if(board[i][j]=='B') numB++;
            }
        }
    }
    public int eval() {
        if (isFinish()){
            if(numB>numR) return -numB;
            else  if(numB<numR) return numR;
            else return 0;
        }
        else
            return 0;


    }
    public boolean checkMove(Point current,Point target){
        if(target.x==-1&&target.y==-1) return true;
        if((current.x<0)||(current.y<0)||(target.x<0)||(target.y<0))return false;
        else if((current.x>width-1)||(current.y>width-1)||(target.x>width-1)||(target.y>width-1))return false;
        else if(board[current.x][current.y]=='_'||board[current.x][current.y]=='#') return false;
        else if(board[target.x][target.y]!='_'||board[target.x][target.y]=='#') return false;
        else if(Math.abs(target.x-current.x)>2||Math.abs(target.x-current.x)<0) return false;
        else if(Math.abs(target.y-current.y)>2||Math.abs(target.y-current.y)<0) return false;
        else return true;
    }
    public boolean isFinish(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if(board[i][j]=='_') return false;
            }
        }
        return true;
    }

    public void move(Point current,Point target){
        char type= board[current.x][current.y];
        if(type=='B') type='R';
        else type='B';
        if(target.x==-1&&target.y==-1) pass(type);
        else if(board[target.x][target.y]=='_'){
                board[target.x][target.y]=board[current.x][current.y];
                if(Math.abs(target.x-current.x)==2||Math.abs(target.y-current.y)==2){
                board[current.x][current.y]='_';
                }
                attack(target);
                score();
        }
    }
    public void attack(Point attacker){
        int minX=attacker.x-1;
        if(minX<0)minX=0;
        int minY=attacker.y-1;
        if(minY<0)minY=0;
        int maxX=attacker.x+1;
        if(maxX>=width)maxX=width-1;
        int maxY=attacker.y+1;
        if(maxY>=width)maxY=width-1;
        for(int j=minY;j<=maxY;j++){
            if(board[minX][j]!='#'&&board[minX][j]!='_'&&board[minX][j]!=board[attacker.x][attacker.y]){
                board[minX][j]=board[attacker.x][attacker.y];
            }
        }
        for(int j=minY;j<=maxY;j++){
            if(board[maxX][j]!='#'&&board[maxX][j]!='_'&&board[maxX][j]!=board[attacker.x][attacker.y]){
                board[maxX][j]=board[attacker.x][attacker.y];
            }
        }
        if(board[attacker.x][minY]!='#'&&board[attacker.x][minY]!='_'&&board[attacker.x][minY]!=board[attacker.x][attacker.y])
            board[attacker.x][minY]=board[attacker.x][attacker.y];
        if(board[attacker.x][maxY]!='#'&&board[attacker.x][maxY]!='_'&&board[attacker.x][maxY]!=board[attacker.x][attacker.y])
            board[attacker.x][maxY]=board[attacker.x][attacker.y];
    }
    void pass(char type){
        for(int i=0;i<width;i++){
            for(int j=0;j<width;j++){
                if(board[i][j]=='_')
                    board[i][j]=type;
            }
        }
    }
    public List<State> nextAllMove(char type){
        List<State> nextBoards = new LinkedList<>();
        List<State> nextBoardsSide = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if(board[i][j]==type){
                    for (Point element: allowAllMovement(new Point(i,j))){
                        State nextBoard = new State(this);
                        nextBoard.move(new Point(i,j),element);
                        nextBoards.add(nextBoard);
                        if((i>element.x-2&&i<element.x+2)&&(j>element.y-2&&j<element.y+2)){
                            nextBoardsSide.add(nextBoard);
                        }
                    }
                }
            }
        }
        if(nextBoardsSide.size()>0) return nextBoardsSide;
        if(nextBoards.size()<1&&!isFinish()&&type!='B'){
            State nextBoard = new State(this);
            if(type=='R') type='B';
            else type='R';
            nextBoard.pass(type);
            nextBoards.add(nextBoard);
        }

        return nextBoards;
    }
    public List<Point> allowAllMovement(Point item){
        List<Point> allowMovement = new LinkedList<>();
        List<Point> sideMovement = new LinkedList<>();
        boolean side=false;
        for(int i=item.x-2;i<=item.x+2;i++){
            for(int j=item.y-2;j<=item.y+2;j++){
                if(checkMove(item,new Point(i,j))){
                    allowMovement.add(new Point(i,j));
                    if((i>item.x-2&&i<item.x+2)&&(j>item.y-2&&j<item.y+2)){
                        sideMovement.add(new Point(i,j));
                    }
                }
            }
        }
        if(sideMovement.size()>0){
            return sideMovement;
        }

        return allowMovement;
    }
    @Override
    public String toString() {
        String sBoard="";
        for(int i=0;i<width;i++){
            for(int j=0;j<width;j++){
                sBoard+=board[i][j]+" ";
            }
            sBoard+="\n";
        }
        return "\n" +
                sBoard+
                "................."+
                  "\n numB=" + numB +
                  "  numR=" + numR +
                "\n................."+
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(board, state.board);
    }

    @Override
    public int hashCode() {
        //return Arrays.hashCode(board);
        int hash=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<width;j++){
                if(board[i][j]=='#') hash+=2+(2*Math.pow(10,i))+(2*Math.pow(10,j))+(2*Math.pow(10,i))*(2*Math.pow(10,j));
                else if(board[i][j]=='B') hash+=5+(5*Math.pow(10,i))+(5*Math.pow(10,j))+(5*Math.pow(10,i))*(5*Math.pow(10,j));
                else if(board[i][j]=='R') hash+=2+(9*Math.pow(10,i))+(9*Math.pow(10,j))+(9*Math.pow(10,i))*(9*Math.pow(10,j));
            }
        }
        return hash;
    }
}
