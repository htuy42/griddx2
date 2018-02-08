package com.htuy.gridgame.entity.alive_entity;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;

public class CopyBirther implements ChildMaker {
    @Override
    public AliveEntity makeChild(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider) {
        return new AliveEntity(self.color, self.size, self.getLocation(), self.energy, self.mover, self.energizer, self.childMaker, self.babyThreshold, self.eatScalar);
    }
}
