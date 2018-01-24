package com.htuy.gridgame.cell;

import com.htuy.gridgame.geom_tools.Point;

public interface CellGenerator {

    Cell nextCell(Point loc);

}
