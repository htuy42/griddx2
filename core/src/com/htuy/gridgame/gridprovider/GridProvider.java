package com.htuy.gridgame.gridprovider;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.Random;
import java.util.function.BiConsumer;

public interface GridProvider {
    Random random = new Random();

    Cell getCell(Point toGet);

    void setCell(Point toSet, Cell setAs);

    void save();

    Cell getCell(int x, int y);

    boolean hasCell(Point toGet);

    boolean hasCell(int x, int y);

    void iterCells(BiConsumer<Point, Cell> execute);

    View getFullView();

    default Point randomAdjoiningLocations(Point location) {
        int tries = 0;
        while (tries < 4) {
            int nx = location.getX() + (random.nextBoolean() ? 0 : random.nextBoolean() ? -1 : 1);
            int ny = location.getY() + (random.nextBoolean() ? 0 : random.nextBoolean() ? -1 : 1);
            if (hasCell(nx, ny)) {
                return new Point(nx, ny);
            }
            tries++;
        }
        return location;
    }
}
