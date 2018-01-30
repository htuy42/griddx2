package com.htuy.gridgame.implementors.heatmap;

import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.MouseLocListener;
import com.htuy.gridgame.world.GridWorld;

public class HeatmapInputFunctions implements InputFunctions {
    @Override
    public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
        inputProvider.registerClickListener(new MouseLocListener() {
            @Override
            public boolean trigger(Point loc) {
                HeatBeacon beacon = new HeatBeacon(loc);
                world.getEntityProvider().spawnAtNextTick(beacon);
                return false;
            }

            @Override
            public boolean interestedIn(Point loc) {
                return true;
            }
        });
    }
}
