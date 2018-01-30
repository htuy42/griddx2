package com.htuy.gridgame.cell;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface Cell {

    default void tick(GridProvider provider, Point ownLocation, EntityProvider entities) {

    }

    default int getHeight() {
        return 0;
    }


    void setHeight(int i);
}
