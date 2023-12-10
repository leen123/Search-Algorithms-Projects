package com.company;


public class HashCodeS {
    private char[][] board;
    HashCodeS(State state) throws CloneNotSupportedException {
        state.getWidth();
        this.board=new char[state.getWidth()][state.getWidth()];
        for (int i = 0; i < state.getWidth(); i++) {
            System.arraycopy(state.getBoard()[i] , 0,this.board[i], 0, state.getWidth());
        }
    }

    boolean hashSet(State state){
        //if(board==null) return false;
        int n=board.length;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if(state.getBoard()[i][j]!=board[i][j]) return false;
            }
        }
        return true;
    }

}
