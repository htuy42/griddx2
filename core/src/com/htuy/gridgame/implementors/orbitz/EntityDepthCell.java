package com.htuy.gridgame.implementors.orbitz;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

import java.util.LinkedList;
import java.util.List;

public class EntityDepthCell implements Cell {

    private static final int HISTORY_COUNT = 500;
    List<Integer> heights = new LinkedList<>();
    private int height;

    @Override
    public void tick(GridProvider provider, Point ownLocation, EntityProvider entities) {
        int add = entities.getCountInCell(ownLocation);
        this.heights.add(add);
        int rem = 0;
        if (this.heights.size() > HISTORY_COUNT) {
            rem = this.heights.remove(0);
        }
        this.height -= rem;
        this.height += add;
    }

    @Override
    public int getHeight() {
        if (heights.size() == 0) {
            return height;
        }
        return height / heights.size();
    }

    @Override
    public void setHeight(int i) {
        height = i;
    }
}
