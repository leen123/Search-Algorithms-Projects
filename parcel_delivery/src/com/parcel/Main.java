package com.parcel;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
       /* State state = new State(3,3);
        State state1 = new State(state);
        State state2 =  new State(state.board);
       // System.out.printf(state.hashCode()+" "+state.hashCode()+" "+state.hashCode());
        System.out.println(state2.n+" "+state2.m);
        state1.board[0][0]="a";
        System.out.println(state.board[0][0]);
        state1.movement.add("1,2,M");
       // state1.valid.add(new Point(1,1));
        System.out.println(state.movement);
        System.out.println(state1.movement);
        System.out.println(state1.toString());

        //start
        state1.start.x=2;
        System.out.println(state.start.x);
        System.out.println(state1.start.x
        );
        // move if #
        state1.board[0][0]=".";
        state1.process(new Point(1,0));
        System.out.println(state1.movement.get(1));

        // recDev
        state1.board[0][0]="P1";
        state1.validP= new boolean[2];
        state1.recDev(new Point(0,0));
        System.out.println(state1.validP[0]);
        System.out.println(state1.movement.get(2));

        //toPoint
        System.out.println(state1.toPoint("132,214,M").y);

        //to string
        System.out.println(state1.toString());

        //hashset
        HashCodeS hashCodeS = new HashCodeS(state2);
        System.out.println(hashCodeS.hashSet(state2));*/
        String board[][]={
                {"D0",".",".",".",".","T"},
                {".","#","#","P1",".","."},
                {".",".","#","#",".","."},
                {"P0",".",".",".",".","D1"}
        };
        String board1[][]={
                {".",".",".",".",".","#","T"},
                {".","P0","#","#",".","#","."},
                {"D0",".",".","#",".",".","."},
                {".",".",".","#",".",".","."}
        };
        String board2[][]={
                {".",".","#","T","#",".","."},
                {".",".","#","P0","#",".","."},
                {".",".","#",".","#",".","."},
                {"D0",".",".","P1",".",".","D1"}
        };
        State state = new State(board2);
        System.out.println(state.toString());
        Controller controller = new Controller(state);
        //controller.ucs();
       controller.aStar();

    }
}
