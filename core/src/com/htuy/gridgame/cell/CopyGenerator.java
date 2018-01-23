package com.htuy.gridgame.cell;

import com.htuy.gridgame.geom_tools.Point;

public class CopyGenerator implements CellGenerator {

    private final Class<? extends Cell> type;

    public CopyGenerator(Class<? extends Cell> type) {
        this.type = type;
    }

    @Override
    public Cell nextCell(Point loc) {
        try {
            return type.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Problem instantiating a cell in the copy generator.");
            throw new IllegalArgumentException();
        }
    }
}
