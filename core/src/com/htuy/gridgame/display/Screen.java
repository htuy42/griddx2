package com.htuy.gridgame.display;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.htuy.gridgame.geom_tools.Point;

public class Screen {

    private int height;
    private int width;

    @Inject
    public Screen(@Named("Screen Width, Pixels") int width, @Named("Screen Height, Pixels") int height){
        //assumes that it begins at 0,0 implicitly, and cannot provide other functionality
        this.height = height;
        this.width = width;
    }

    public Point toScreenLocation(Point loc, View context, int cellSize){


        int xTrans = loc.getX() - context.getLoc().getX();
        int yTrans = loc.getY() - context.getLoc().getY();
        return new Point(xTrans * cellSize,yTrans * cellSize);
    }

    public Point toGridLocation(Point loc, View context, int cellSize){


        int xLoc = loc.getX() / cellSize;
        int yLoc = loc.getY() / cellSize;
        return new Point(xLoc + context.getLoc().getX(),yLoc + context.getLoc().getY());
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    void setWidth(int width) {
        this.width = width;
    }
}
