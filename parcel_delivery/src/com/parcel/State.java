package com.parcel;

import java.util.*;

public class State implements Comparable{

    int n, m;
    String board[][];
    Point start,truck;
    boolean validP[];
    boolean validD[];
    int cost,heuristic;
    ArrayList<String> movement;
    public  State(int n , int m){
        this.n = n ;
        this.m = m;
        cost=0;
        this.heuristic=0;
        this.movement= new ArrayList<String>();
        this.validP=new boolean[0];
        this.validD=new boolean[0];
        board= new String[n][m];
        start= new Point();
        truck = new Point();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                board[i][j]=".";
            }
        }
    }
    public  State(String[][] board) {
        this.m=board[n].length;
        this.n=board.length;
        this.cost=0;
        this.heuristic=0;
        this.movement= new ArrayList<String>();
       //this.valid= new ArrayList<>();
        this.board= new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, m);
        }
        int np=0,nd=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j].contains("T")){
                    truck = new Point(i,j);
                    start = new Point(i,j);
                }
                else if(board[i][j].substring(0).contains("P")) np++;
                else if(board[i][j].substring(0).contains("D")) nd++;
            }
        }
        this.validP= new boolean[np];
        this.validD= new boolean[nd];
    }
    public  State(String[][] board,Point start) throws CloneNotSupportedException {
        this.n=board.length;
        this.m=board[n].length;
        this.cost=0;
        this.heuristic=0;
        this.movement= new ArrayList<String>();
        this.board= new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, m);
        }
        int np=0,nd=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j].contains("T")){
                    truck = new Point(i,j);
                }
                else if(board[i][j].substring(0).contains("P")) np++;
                else if(board[i][j].substring(0).contains("D")) nd++;
            }
        }
        this.validP= new boolean[np];
        this.validD= new boolean[nd];
        this.start = (Point) start.clone();
    }
    public  State(State state ) throws CloneNotSupportedException {
        this.n=state.n;
        this.m=state.m;
        this.cost=state.cost;
        this.heuristic=state.heuristic;
        validP=new boolean[state.validP.length];
        validD=new boolean[state.validD.length];
        System.arraycopy(state.validP, 0, validP, 0, state.validP.length);
        System.arraycopy(state.validD, 0, validD, 0, state.validD.length);
        this.movement= (ArrayList<String>) state.movement.clone();
        this.start=(Point) state.start.clone();
        this.truck=(Point) state.truck.clone();
        this.board= new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(state.board[i], 0, board[i], 0, m);
        }
        state.n = this.n;
        state.m = this.m;
    }

    public int getCost() {
        return cost;
    }

    public State() {

    }
    public void deletT(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                    int length=board[i][j].length()-1;
                    if(board[i][j].substring(length).contains("T")){
                        board[i][j]=board[i][j].substring(0,length);
                    }
                    if(board[i][j].length()==0)
                        board[i][j]=".";

            }
        }
        board[truck.x][truck.y]+="T";
    }
    public void move(Point point){
        //board[truck.x][truck.y]=board[truck.x][truck.y].substring(0,board[truck.x][truck.y].length()-1);
        truck= new Point(point);
        if(!board[truck.x][truck.y].substring(board[truck.x][truck.y].length()-1).contains("T"))
            board[truck.x][truck.y]+='T';
        String s=point.x+","+ point.y+","+"M";
        movement.add(s);

    }
    public void recDev(Point point){
       // System.out.println(Integer.parseInt(board[point.x][point.y].substring(1)));
        int length=board[truck.x][truck.y].length();
        if(truck.equals(point)) length-=1;
        if(board[point.x][point.y].substring(0).contains("P")){

            if(!validP[Integer.parseInt(board[point.x][point.y].substring(1,length))]){
                validP[Integer.parseInt(board[point.x][point.y].substring(1,length))]=true;
                deletT();
                movement.add(point.x+","+ point.y+","+"P");

            }}
            else if(board[point.x][point.y].substring(0).contains("D")){
                if(!validD[Integer.parseInt(board[point.x][point.y].substring(1,length))]){
                if(validP[Integer.parseInt(board[point.x][point.y].substring(1,length))]) {
                    validD[Integer.parseInt(board[point.x][point.y].substring(1,length))] = true;
                    deletT();
                    movement.add(point.x + "," + point.y + "," + "D");
                }
                }
            }
    }
    public void process(Point point){
        if(board[point.x][point.y].substring(0).contains("#")) return;
        if(this.truck.equals(point)){
            recDev(point);
        }
        else{
            move(point);
        }
    }
    public boolean checkProcess(Point point){
        if(point.x<0||point.y<0||point.x>=n||point.y>=m) return false;
        if(board[point.x][point.y].substring(0).contains("#")) return false;
        if(this.truck.equals(point)){
            int length=board[truck.x][truck.y].length();
            if(truck.equals(point)) length-=1;
            if(board[point.x][point.y].substring(0).contains("P")) {

                if (validP[Integer.parseInt(board[point.x][point.y].substring(1,length))]) {
                    return false;
                }
            }
            else if(board[point.x][point.y].substring(0).contains("D")){
                if(!validD[Integer.parseInt(board[point.x][point.y].substring(1,length))]){
                    if(!validP[Integer.parseInt(board[point.x][point.y].substring(1,length))]) {
                       return false;
                    }
                }
                else return false;
            }
        }
        return true;
    }
    public List<State> nextAllProcess() throws CloneNotSupportedException {
        List<State> nextBoards = new LinkedList<>();
        List<Point> points = new LinkedList<>();
        points.add(new Point(truck.x,truck.y));
        points.add(new Point(truck.x+1,truck.y));
        points.add(new Point(truck.x-1,truck.y));
        points.add(new Point(truck.x,truck.y+1));
        points.add(new Point(truck.x,truck.y-1));
        for (Point element : points){
            if(checkProcess(element)){
                State nextBoard = new State(this);
                nextBoard.process(element);
                nextBoards.add(nextBoard);
            }
        }
        return nextBoards;
    }
    public boolean isFinish(){
        if(!truck.equals(start)) return false;
        for (int i=0;i<validP.length;i++){
            if(!validP[i]) return false;
        }
        for (int i=0;i<validD.length;i++){
            if(!validD[i]) return false;
        }
        return true;
    }
    public void findCost(){
        this.cost=1;
        for (int i=0;i<validP.length;i++){
            if(validP[i]&&!validD[i]) cost++;
        }
    }
    public void findCost(int oldCost){
        findCost();
        this.cost+=oldCost;
    }
    public void heuristic1(){
        heuristic=Math.abs(truck.x-start.x)+Math.abs(start.y- truck.y);
    }
    public void heuristic2(){
        heuristic=0;
        Point pointP=new Point(),pointD =new Point();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j].substring(0).contains("P0")) pointP=new Point(i,j);
                else if(board[i][j].substring(0).contains("D0")) pointD=new Point(i,j);
            }
        }
        int cot=1;
        for (int i=0;i<validP.length;i++){
            if(validP[i]&&!validD[i]) cot++;
        }
        heuristic*=cot;
        if(!validP[0]){
            heuristic=(Math.abs(truck.x-pointP.x)+Math.abs(truck.y- pointP.y))*(cot)+(Math.abs(pointD.x-pointP.x)+Math.abs(pointD.y- pointP.y))*(cot+1);
        }
        else if(validP[0]&&!validD[0]){
            heuristic=(Math.abs(truck.x-pointD.x)+Math.abs(truck.y- pointD.y))*cot;
        }
        else heuristic=0;

    }
    public void heuristic3(){
        heuristic=0;
        Point pointP[]=new Point[validP.length];
        Point pointD[]=new Point[validD.length];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j].substring(0).contains("P")) pointP[Integer.parseInt(board[i][j].substring(1,2))]=new Point(i,j);
                else if(board[i][j].substring(0).contains("D")) pointD[Integer.parseInt(board[i][j].substring(1,2))]=new Point(i,j);
            }
        }
        int cot=1;
        for (int i=0;i<validP.length;i++){
            if(validP[i]&&!validD[i]) cot++;
        }
        int max=0;
        for (int i=0;i<validP.length;i++){
            if(!validP[i]){
                heuristic=(Math.abs(truck.x-pointP[i].x)+Math.abs(truck.y- pointP[i].y))*(cot)+(Math.abs(pointD[i].x-pointP[i].x)+Math.abs(pointD[i].y- pointP[i].y))*(cot+1);
            }
            else if(validP[i]&&!validD[i]){
                heuristic=(Math.abs(truck.x-pointD[i].x)+Math.abs(truck.y- pointD[i].y))*cot;
            }
            else heuristic=0;
            max=Math.max(heuristic,max);
        }
        heuristic=max;


    }
    public Point toPoint(String string){
        int x=0,y=0;
        int i=0;
        for(char element : string.toCharArray()){
            if(element==','){
                i++;
                if(i>1) break;
            }
            else{
                if(i==0){
                    x*=10;
                    x+=element-'0';
                }else{
                    y*=10;
                    y+=element-'0';
                }
            }
        }
        return new Point(x,y);
    }
    @Override
    public String toString() {
        String temp[][]= new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(this.board[i], 0,temp[i] , 0, m);
        }
        int num=1;
        for(String element : movement){

            Point pointTemp=toPoint(element);
          //  if(temp[pointTemp.x][pointTemp.y].contains(".")) temp[pointTemp.x][pointTemp.y]="";
            temp[pointTemp.x][pointTemp.y]+=',';
            temp[pointTemp.x][pointTemp.y]+=num;
            char end=element.toCharArray()[element.length()-1];
            if(end=='P'||end=='D') temp[pointTemp.x][pointTemp.y]+=end;

            num++;
        }

        String b= new String();
        b+="\n";
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                b+="  ("+temp[i][j]+")";
            }
            b+="\n";
        }

        return "*************************"+ b + "*************************";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(board, state.board) &&
               // Objects.equals(truck, state.truck) &&
                Arrays.equals(validP, state.validP) &&
                Arrays.equals(validD, state.validD);
    }

    @Override
    public int hashCode() {
        int result =0;//= Objects.hash(truck);
        result = 31 * result + Arrays.hashCode(board);
        result = 31 * result + Arrays.hashCode(validP);
        result = 31 * result + Arrays.hashCode(validD);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        State state = (State) o;
        if((this.cost+this.heuristic)<(state.cost+state.heuristic)) return -1;
        else if((this.cost+this.heuristic)>(state.cost+state.heuristic)) return 1;
        else return 0;
    }

}

