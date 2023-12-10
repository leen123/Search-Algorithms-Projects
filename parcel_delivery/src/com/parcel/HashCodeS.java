package com.parcel;



import java.util.ArrayList;

public class HashCodeS {
    String board[][];
    boolean validP[];
    boolean validD[];
    Point truck;
    int cost;
    HashCodeS(State state) throws CloneNotSupportedException {
        this.cost=state.cost;
        if(state.validP!=null){
            validP=new boolean[state.validP.length];
            System.arraycopy(state.validP, 0, validP, 0, state.validP.length);
        }else
            validP=new boolean[0];
        if(state.validD!=null){
            validD=new boolean[state.validD.length];
            System.arraycopy(state.validD, 0, validD, 0, state.validD.length);
        }else
            validD=new boolean[0];
        int n=state.n;
        int m=state.m;
        this.truck=(Point) state.truck.clone();
        this.board= new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(state.board[i] , 0,this.board[i], 0, m);
        }
    }

    boolean hashSet(State state){
        if(state.validP!=null){
            for (int i=0;i<validP.length;i++){
                if(state.validP[i]!=validP[i]) return false;
            }
        }

        if(state.validD!=null)
        for (int i=0;i<validD.length;i++){
            if(state.validD[i]!=validD[i]) return false;
        }
        //if(board==null) return false;
        int n=board.length;
        int m=board[0].length;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(!state.board[i][j].contains(board[i][j])){
                    return false;

                }
               /* else{
                    int recCost=state.cost;
                    state.findCost();
                    int findCost = state.cost;
                    state.cost=recCost;
                    System.out.println(Math.abs(this.cost-recCost));
                    if(Math.abs(this.cost-recCost)>findCost*2){
                        return false;
                    }
                }*/
            }
        }
        if(truck.x!=state.truck.x||truck.y!=state.truck.y) return false;
        return true;
    }

}
