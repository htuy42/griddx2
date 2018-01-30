package com.htuy.gridgame.entity.alive_entity;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface ChildMaker {
    AliveEntity makeChild(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider);
}
