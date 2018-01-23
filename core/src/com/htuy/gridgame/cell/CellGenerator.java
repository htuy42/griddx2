package com.htuy.gridgame.cell;

import com.htuy.gridgame.geom_tools.Point;

public interface CellGenerator {

    public Cell nextCell(Point loc);

}
