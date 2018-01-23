package com.htuy.gridgame.display;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.htuy.gridgame.geom_tools.Point;

public class View {

    private Point loc;
    private int height;
    private int width;

    @Inject
    public View(@Named("View Width, Tiles") int width, @Named("View Height, Tiles") int height) {
        this.loc = new Point(0,0);
        this.height = height;
        this.width = width;
    }

    public View(Point loc, int width, int height){
        this.loc = loc;
        this.height = height;
        this.width = width;
    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }

    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }
}
