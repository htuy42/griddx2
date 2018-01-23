package com.htuy.gridgame.cell;

import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface Cell {

    public default void tick(GridProvider provider, Point ownLocation) {

    }

    public default int getHeight() {
        return 0;
    }


}
