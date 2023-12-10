package com.company;

public class Point implements Cloneable {
    int x,y;
    Point(){
        x=0;
        y=0;
    }
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    Point(Point point){
        this.x= point.x;
        this.y= point.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
