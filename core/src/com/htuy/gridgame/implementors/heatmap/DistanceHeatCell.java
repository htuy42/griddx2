package com.htuy.gridgame.implementors.heatmap;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.input.MouseLocListener;
import com.htuy.gridgame.main;

public class DistanceHeatCell implements Cell {

    private int height = 0;


    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void tick(GridProvider provider, Point ownLocation) {
        height = ownLocation.manhattanDistanceTo(main.inputRegistry.getCurrentMouseScreenLocation());
    }
}
