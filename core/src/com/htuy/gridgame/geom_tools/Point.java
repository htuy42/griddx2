package com.htuy.gridgame.geom_tools;

import com.htuy.gridgame.display.View;

import java.util.Objects;
import java.util.Random;

public class Point {

    static Random r = new Random();
    final int x;
    final int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point randomPoint(View v) {
        return new Point(r.nextInt(v.getWidth()), r.nextInt(v.getHeight()));
    }

    private Point withDeltas(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    public Point withDx(float dx) {
        return withDeltas((int) dx, 0);
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

    public Point withDy(float dy) {
        return withDeltas(0, (int) dy);
    }


    public int manhattanDistanceTo(Point other) {
        return Math.abs(other.getX() - x) + Math.abs(other.getY() - y);
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public double trueDistanceTo(Point other) {
        int dx = other.getX() - x;
        int dy = other.getY() - y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public Point closerTo(Point location) {

        if (location.getX() == x) {
            if (location.getY() == y) {
                return location;
            } else if (location.getY() < y) {
                return new Point(x, y - 1);
            } else {
                return new Point(x, y + 1);
            }
        } else {
            if (location.getX() < x) {
                return new Point(x - 1, y);
            } else {
                return new Point(x + 1, y);
            }
        }

    }

    public Point withY(int y) {
        return new Point(x, y);
    }


    public Point withX(int x) {
        return new Point(x, y);
    }
}
