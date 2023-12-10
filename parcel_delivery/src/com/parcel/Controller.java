package com.parcel;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Controller {
    State state;
    PriorityQueue<State> priorityQueue= new PriorityQueue<>();
    ArrayList<HashCodeS> visit = new ArrayList<>();
    Controller(){
    }
    Controller(State state) throws CloneNotSupportedException {
        this.state= new State(state);
    }
    void loadBoard(){

    }
    void ucs() throws CloneNotSupportedException {
        double first=System.currentTimeMillis();
        PriorityQueue<State> priorityQueue= new PriorityQueue<>();
        ArrayList<HashCodeS> visit = new ArrayList<>();
        priorityQueue.add(state);
        visit.add(new HashCodeS(state));
        State winState=new State(state) ;
        int num=0;
        while(!priorityQueue.isEmpty()){
            state=priorityQueue.poll();
            if (state.isFinish()){
                if (winState.isFinish()&&winState.cost<state.cost);
                else
                    winState=new State(state);
               // break;
            }
            //طباعة الحالات
            //if(grid.numbers.size()<=2)grid.printMagic();
           // System.out.println(state.toString());
            for (State element:state.nextAllProcess()){
                boolean visiter=false;
                /*if(element.movement.contains("3,0,M") &&element.validP[0]&&element.validD[1])
                System.out.println(element.toString());*/
                for (HashCodeS codeS:visit){
                    if(codeS.hashSet(element)) visiter=true;
                }
                if (!visiter){

                    element.findCost(state.getCost());
                    priorityQueue.add(element);
                    visit.add(new HashCodeS(element));
                    num++;
                }
            }
        }
        double end=System.currentTimeMillis();
        System.out.println("عدد الحالات :"+num);
        System.out.println("الوقت :"+(end-first)/1000);
        if(winState.isFinish()){
            System.out.println("كلفة الحل :"+winState.cost);
            System.out.println("عدد العمليات :"+winState.movement.size());
            System.out.println(winState.toString());
        }

        else
            System.out.println("NOR FOUND");
    }
    void aStar() throws CloneNotSupportedException {
        double first=System.currentTimeMillis();
        PriorityQueue<State> priorityQueue= new PriorityQueue<>();
        ArrayList<HashCodeS> visit = new ArrayList<>();
        priorityQueue.add(state);
        visit.add(new HashCodeS(state));
        State winState=new State(state) ;
        int num=0;
        while(!priorityQueue.isEmpty()){
            state=priorityQueue.poll();
            if (state.isFinish()){
                if (winState.isFinish()&&winState.cost<state.cost);
                else
                    winState=new State(state);
                break;
            }
            //طباعة الحالات
            //if(grid.numbers.size()<=2)grid.printMagic();
            // System.out.println(state.toString());
            for (State element:state.nextAllProcess()){
                boolean visiter=false;
                /*if(element.movement.contains("3,0,M") &&element.validP[0]&&element.validD[1])
                System.out.println(element.toString());*/
               // System.out.println(num);
                for (HashCodeS codeS:visit){
                    if(codeS.hashSet(element)) visiter=true;
                }
                if (!visiter){
                    element.findCost(state.getCost());
                    element.heuristic2();
                    priorityQueue.add(element);
                    visit.add(new HashCodeS(element));
                    num++;
                }
            }
        }
        double end=System.currentTimeMillis();
        System.out.println("عدد الحالات :"+num);
        System.out.println("الوقت :"+(end-first)/1000);
        if(winState.isFinish()){
            System.out.println("كلفة الحل :"+winState.cost);
            System.out.println("عدد العمليات :"+winState.movement.size());
            winState.deletT();
            System.out.println(winState.toString());
        }

        else
            System.out.println("NOR FOUND");
    }
}
