package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.Color;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.alive_entity.AliveEntity;
import com.htuy.gridgame.entity.alive_entity.CopyBirther;
import com.htuy.gridgame.entity.alive_entity.EnergizeDecider;
import com.htuy.gridgame.entity.alive_entity.RandomMover;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class Eater extends AliveEntity {
    private Point location;

    public Eater(Point location) {
        super(Color.CORAL, .25f, location, 10, new RandomMover(), new EnergizeDecider() {
            @Override
            public void energize(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider) {
                Cell loc = gridProvider.getCell(self.getLocation());
                self.addEnergy(loc.getHeight());
                loc.setHeight(0);
            }
        }, new CopyBirther(), 50, 5.0f);
    }
}
