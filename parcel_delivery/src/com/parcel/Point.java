package com.parcel;

import java.util.Objects;

public class Point implements Cloneable {
    int x,y;
    Point(){
        x=0;
        y=0;
    }
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
    Point(Point point){
        this.x= point.x;
        this.y= point.y;
    }
    public void setPoint(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
