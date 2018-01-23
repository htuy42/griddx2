package com.htuy.gridgame.gridprovider;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Do not use! Only exists because it is easy to create and read, as a stepping piece. Very inefficient.
 */
public class FlatDictProvider implements GridProvider {

    private Map<Point, Cell> cells;
    private int width;
    private int height;

    @Inject
    public FlatDictProvider(CellGenerator generator, @Named("View Width, Tiles") int width,
                            @Named("View Height, Tiles") int height) {
        this.cells = new HashMap<>();
        FuncTools.rectIter(new Point(0, 0), width, height,
                (Integer x, Integer y) -> {
                    cells.put(new Point(x, y), generator.nextCell(new Point(x,y)));
                });
        this.width = width;
        this.height = height;
    }


    @Override
    public Cell getCell(Point toGet) {
        return cells.get(toGet);
    }

    @Override
    public void setCell(Point toSet, Cell setAs) {
        cells.put(toSet, setAs);
    }

    @Override
    public void save() {
        //TODO
    }

    @Override
    public Cell getCell(int x, int y) {
        return cells.get(new Point(x, y));
    }

    @Override
    public boolean hasCell(Point toGet) {
        return cells.containsKey(toGet);
    }

    @Override
    public boolean hasCell(int x, int y) {
        return cells.containsKey(new Point(x, y));
    }

    @Override
    public void iterCells(BiConsumer<Point, Cell> execute) {
        for (Map.Entry<Point, Cell> entry : cells.entrySet()) {
            execute.accept(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public View getFullView() {
        return new View(new Point(0, 0), this.width, this.height);
    }
}
