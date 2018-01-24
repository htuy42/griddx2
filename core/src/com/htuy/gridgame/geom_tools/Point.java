package com.htuy.gridgame.geom_tools;

import java.util.Objects;

public class Point {

    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point withDx(int dx){
        return withDeltas(dx,0);
    }

    public Point withDy(int dy){
        return withDeltas(0,dy);
    }

    public Point withDeltas(int dx, int dy){
        return new Point(x + dx, y + dy);
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    public String toString(){
        return x + "," + y;
    }

    public int manhattanDistanceTo(Point other) {
        return Math.abs(other.getX() - x) + Math.abs(other.getY() - y);
    }

    public double trueDistanceTo(Point other){
        int dx = other.getX() - x;
        int dy = other.getY() - y;

        return Math.sqrt(dx * dx + dy * dy);
    }
}
