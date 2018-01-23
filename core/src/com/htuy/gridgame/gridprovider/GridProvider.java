package com.htuy.gridgame.gridprovider;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.function.BiConsumer;

public interface GridProvider {

    Cell getCell(Point toGet);

    void setCell(Point toSet, Cell setAs);

    void save();

    Cell getCell(int x, int y);

    boolean hasCell(Point toGet);

    boolean hasCell(int x, int y);

    void iterCells(BiConsumer<Point, Cell> execute);

    View getFullView();

}
