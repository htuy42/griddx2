package com.htuy.gridgame.entity;

import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface EntityProvider {

    Collection<Entity> getAllEntities();

    void tickEntities(GridProvider provider);

    void renderEntities(Display display);

    void spawnAtNextTick(Entity entity);

    List<Entity> getAllOfType(Class<? extends Entity> type);

    List<Entity> getAllNearby(Point location, int distance);

    void updateEntityLocation(Entity e, Point newLocation);

    void kill(Entity e);

    Entity getNearestOfType(Function<Entity, Boolean> tester, Point nearTo);
}
