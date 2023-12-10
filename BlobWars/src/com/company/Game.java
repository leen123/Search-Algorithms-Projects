package com.company;

import java.util.*;

public class Game {
    State board ;
    int typeGame,level;
    char typePlayer;
    public ArrayList<HashCodeS> visit= new ArrayList<HashCodeS>();
    Scanner in = new Scanner(System.in);
    public void runGame() throws CloneNotSupportedException {

        System.out.println("===========================================");
        System.out.println("===========================================");
        System.out.println("==               Blob Wars               ==");
        System.out.println("===========================================");
        System.out.println("===========================================");
        System.out.println("=                                         =");
        System.out.println("=                                         =");
        do {
            System.out.println("===========================================");
            System.out.println("=                1- 1 Player              =");
            System.out.println("=                2- 2 Players             =");
            System.out.println("=                3- Exit                  =");
            System.out.println("===========================================");
            System.out.print("Enter type game : ");
            typeGame= in.nextInt();
            switch (typeGame){
                case 2:
                    startGame();
                    break;
                case 1:
                    System.out.println("===========================================");
                    System.out.println("=                   LEVEL                 =");
                    System.out.println("===========================================");
                    System.out.println("=                1- Easy                  =");
                    System.out.println("=                2- Medium                =");
                    System.out.println("=                3- Hard                  =");
                    System.out.println("===========================================");
                    System.out.print("Enter level game : ");
                    level= in.nextInt();
                    startGame();
                    break;
                default:
                    System.out.println("Wrong Input!!");
                    break;
            }
        }while (typeGame!=3);

    }
    public void startGame() throws CloneNotSupportedException {
        visit.add(new HashCodeS(board));
        System.out.println(board);
        while (true) {
            System.out.println("************Player 1************");
            typePlayer='B';
            playerMove();
            System.out.println(board);
            if (board.isFinish()) {
                System.out.println("Player 2 Wins");
                break;
            }
            System.out.println("************Player 2************");
            typePlayer='R';
            if(typeGame==2) playerMove();
            else computer();
            System.out.println(board);
            if (board.isFinish()) {
                System.out.println("Player 1 Wins");
                break;
            }
        }
    }
    public void playerMove(){
        Point current=new Point();
        Point target=new Point();
        while (true) {
            System.out.print("Enter point current (x,y): ");
            current.x=in.nextInt();
            current.y=in.nextInt();
            System.out.println();
            System.out.print("Enter point target (x,y): ");
            target.x=in.nextInt();
            target.y=in.nextInt();
            if (board.checkMove(current,target)&&board.getBoard()[current.x][current.y]==typePlayer) {
                break;
            }
            else{
                System.out.println("Wrong Input!!");
            }
        }
        board.move(current,target);
    }
    public void computer() throws CloneNotSupportedException {
        switch (level){
            case 1:
                easy();
                break;
            case 2:
                medium();
                break;
            case 3:
                hard();
                break;
        }
    }
    public void easy(){
        List<State> nextState = board.nextAllMove(typePlayer);
        int rand = new Random().nextInt(nextState.size());
        board=nextState.get(rand);
    }
    int count=0,numNode,iNode=0;
    State best;
    public void medium() throws CloneNotSupportedException {
        best=null;
        numNode=10;
        max2(board,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("count : "+count);
        count=0;
        iNode=0;
    }
    public void hard() throws CloneNotSupportedException {
        best=null;
        numNode=100;
        max2(board,0,Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("count : "+count);
        count=0;
        iNode=0;
    }
    int d=0;
    int max(State b, int depth, int alpha, int beta) throws CloneNotSupportedException {
        count++;
        d++;
        //System.out.println("count : "+count);
        //System.out.println(board.toString());
        visit.add(new HashCodeS(b) );
        if (b.isFinish()) {
            count++;
            return b.eval();
        }
        State best=null;
        int maxvar=Integer.MIN_VALUE;
        List<State> nexts = b.nextAllMove('R');
            for (State next :nexts)
            {
                System.out.println("count : "+count);
                boolean visiter=false;
                for (HashCodeS element:visit){
                    if(element.hashSet(next)) visiter=true;
                }
                if(!visiter||(next.getNumB()==(next.getWidth()*next.getWidth()))){
                    //System.out.println(next.getNumB());
                    // System.out.println("count : "+count);
                   System.out.println(next.toString());
                    int max1 = min(next, depth+1, alpha,beta);
                    if(maxvar<=max1)
                    {
                        maxvar=max1;
                        best=next;
                        alpha = Math.max(alpha, max1);
                    }
                    if (beta <= alpha)
                        break;
                   // if(d>100) break;
                }

            }

        if (depth==0&&best!=null)
            board=best;
        return maxvar;
    }
    int min(State b, int depth, int alpha, int beta) throws CloneNotSupportedException {
        count++;
        visit.add(new HashCodeS(b) );
        State best=null;
        int minvar=Integer.MAX_VALUE;
        if (b.isFinish())
        {
            count++;
            return b.eval();
        }
        List<State> nexts =b.nextAllMove('B') ;
        for (State next :nexts)
        {
            boolean visiter=false;
            for (HashCodeS element:visit){
                if(element.hashSet(next)) visiter=true;
            }
            if(!visiter||(next.getNumB()==(next.getWidth()*next.getWidth()))){

                int min1 = max(next, depth+1, alpha,beta);
                if(minvar>=min1)
                {
                    minvar=min1;
                    best=next;
                    beta=Math.max(alpha, min1);
                }
                if (beta <= alpha)
                    break;
            }

        }
        if (depth==0&&best!=null)
            board=best;
        return minvar;
    }


    int max2(State b, int depth, int alpha, int beta) throws CloneNotSupportedException {
        //count++;
        visit.add(new HashCodeS(b) );;
        if (b.isFinish())
        {
            count++;
            if(b.getNumB()+b.getNumR()==b.getWidth()*b.getWidth()) {
                iNode++;
               // System.out.println(b.toString());
                if(best.eval()<b.eval())
                best=b;
            }

            return b.eval();

        }if(best!=null&&numNode<iNode){
            return best.eval();
        }

        int maxvar=Integer.MIN_VALUE;
        List<State> nexts = b.nextAllMove('R');
        for (State next :nexts)
        {
            //.out.println("count : "+count);
            boolean visiter=false;
            for (HashCodeS element:visit){
                if(element.hashSet(next)) visiter=true;
            }
            if(!visiter||(next.getNumB()==(next.getWidth()*next.getWidth()))){
                //System.out.println(next.getNumB());
                // System.out.println("count : "+count);
               // System.out.println(next.toString());
                int max1 = min2(next, depth+1, alpha,beta);
                if(maxvar<=max1)
                {
                    maxvar=max1;
                    best=next;
                    alpha = Math.max(alpha, max1);
                }
                if (beta <= alpha)
                    break;
            }

        }

        if (depth==0&&best!=null)
            board=best;
        return maxvar;
    }
    int min2(State b, int depth, int alpha, int beta) throws CloneNotSupportedException {
        visit.add(new HashCodeS(b) );
        State best=null;
        int minvar=Integer.MAX_VALUE;
        if (b.isFinish())
        {
            count++;
            return b.eval();

        }if(best!=null&&numNode<iNode){
            return best.eval();
        }
        List<State> nexts =b.nextAllMove('B') ;
        for (State next :nexts)
        {
            boolean visiter=false;
            for (HashCodeS element:visit){
                if(element.hashSet(next)) visiter=true;
            }
            if(!visiter||(next.getNumB()==(next.getWidth()*next.getWidth()))){

                int min1 = max2(next, depth+1, alpha,beta);
                if(minvar>=min1)
                {
                    minvar=min1;
                    best=next;
                    beta=Math.max(alpha, min1);
                }
                if (beta <= alpha)
                    break;
            }

        }
        if (depth==0&&best!=null)
            board=best;
        return minvar;
    }

}
