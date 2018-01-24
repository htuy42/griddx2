package com.htuy.gridgame.gridprovider;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ArrayProvider2d implements GridRowProvider {

    private final int width;
    private final int height;
    private final List<List<Cell>> cells;

    @Inject
    public ArrayProvider2d(CellGenerator generator, @Named("View Width, Tiles") int width,
                           @Named("View Height, Tiles") int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>();
        FuncTools.rectIter(width, height, (x, y) -> {
            if (cells.size() <= y) {
                cells.add(new ArrayList<>());
            }
            cells.get(y).add(generator.nextCell(new Point(x, y)));
        });
    }


    @Override
    public Cell getCell(Point toGet) {
        return getCell(toGet.getX(), toGet.getY());
    }

    @Override
    public void setCell(Point toSet, Cell setAs) {
        cells.get(toSet.getY()).set(toSet.getX(), setAs);
    }

    @Override
    public void save() {
        //
    }

    @Override
    public Cell getCell(int x, int y) {
        return cells.get(y).get(x);
    }

    @Override
    public boolean hasCell(Point toGet) {
        return hasCell(toGet.getX(), toGet.getY());
    }

    @Override
    public boolean hasCell(int x, int y) {
        return y >= 0 && y < cells.size() && x >= 0 && x < cells.get(0).size();
    }

    @Override
    public void iterCells(BiConsumer<Point, Cell> execute) {
        int y = 0;
        for (List<Cell> lst : cells) {
            int x = 0;
            for (Cell c : lst) {
                execute.accept(new Point(x, y), c);
                x++;
            }
            y++;
        }
    }

    @Override
    public View getFullView() {
        return new View(new Point(0, 0), width, height);
    }

    @Override
    public List<Cell> getRow(Point start, int width) {
        if (start.getX() == 0 && (width == -1 || width == this.width)) {
            return cells.get(start.getY());
        }
        return cells.get(start.getY()).subList(start.getX(), start.getX() + width);
    }
}
