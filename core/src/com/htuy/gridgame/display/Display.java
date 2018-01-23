package com.htuy.gridgame.display;

import com.google.inject.Inject;
import com.htuy.gridgame.geom_tools.Point;

public class Display {

    private View view;
    private Screen screen;
    private int cellSize;

    @Inject
    public Display(View view, Screen screen){
        this.view = view;
        this.screen = screen;
        computeCellSize();
    }

    public Point toViewLoc(Point point) {
        return screen.toGridLocation(point,view,getCellSize());
    }

    public View getView() {
        return view;
    }

    private void computeCellSize(){
        int xSize = screen.getWidth() / view.getWidth();
        int ySize = screen.getHeight() / view.getHeight();
        this.cellSize = Math.min(xSize,ySize);
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setViewWidth(int width) {
        this.view.setWidth(width);
        computeCellSize();
    }

    public void setViewHeight(int height){
        this.view.setHeight(height);
        computeCellSize();
    }

    public void setScreenWidth(int width){
        this.screen.setWidth(width);
        computeCellSize();
    }

    public void setScreenHeight(int height){
        this.screen.setHeight(height);
        computeCellSize();
    }

    public Point toScreenLocation(Point point) {
        return screen.toScreenLocation(point,view,getCellSize());

    }

    public void setViewLoc(Point viewLoc) {
        this.view.setLoc(viewLoc);
    }

    public Screen getScreen() {
        return screen;
    }
}
