package com.htuy.gridgame.implementors.eaters;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.Random;

public class BasicPlantCell implements Cell {

    private static final double RATE = 25;
    private static Random r = new Random();
    private float height = 25;

    @Override
    public void tick(GridProvider provider, Point ownLocation, EntityProvider entities) {
        grow();
    }

    @Override
    public int getHeight() {
        return (int) height;
    }

    @Override
    public void setHeight(int i) {
        height = i;
    }

    protected void grow() {
        height += (r.nextFloat() * main.LAST_DELTA * RATE);
        height = Math.min(100, height);
    }

}
