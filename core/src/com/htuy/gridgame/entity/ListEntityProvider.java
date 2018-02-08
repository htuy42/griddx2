package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.collections.CacheMap;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ListEntityProvider implements EntityProvider {

    private Set<Entity> entities;
    private Set<Entity> toSpawn;
    private Set<Entity> toKill;
    private CacheMap<Class<? extends Entity>, List<Entity>> allOfTypes;
    private ShapeRenderer renderer;
    private Multimap<Point, Entity> entitiesLocations;

    public ListEntityProvider() {
        entities = new HashSet<>();
        toSpawn = new HashSet<>();
        toKill = new HashSet<>();
        allOfTypes = new CacheMap<>(new Function<Class<? extends Entity>, List<Entity>>() {
            @Override
            public List<Entity> apply(Class<? extends Entity> aClass) {
                List<Entity> result = new ArrayList<>();
                for (Entity e : entities) {
                    if (aClass.isInstance(e)) {
                        result.add(e);
                    }
                }
                return result;
            }
        });
        renderer = new ShapeRenderer();
        entitiesLocations = HashMultimap.create();
    }

    @Override
    public Collection<Entity> getAllEntities() {
        return Collections.unmodifiableSet(entities);
    }

    @Override
    public void tickEntities(GridProvider provider) {
        for (Entity e : entities) {
            if (e.tick(provider, this)) {
                toKill.add(e);
                entitiesLocations.get(e.getLocation()).remove(e.getSelf());
                allOfTypes.remove(e.getClass());
            }
        }
        entities.removeAll(toKill);
        for (Entity e : toKill) {
            entitiesLocations.get(e.getLocation()).remove(e.getSelf());
        }
        for (Entity e : toSpawn) {
            entities.add(e);
            entitiesLocations.get(e.getLocation()).add(e.getSelf());
            allOfTypes.remove(e.getClass());
        }
        toKill.clear();
        toSpawn.clear();
    }

    @Override
    public void renderEntities(Display display) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity e : entities) {
            e.render(this, display, renderer);
        }
        renderer.end();
    }

    @Override
    public void spawnAtNextTick(Entity entity) {
        toSpawn.add(entity);
    }

    @Override
    public List<Entity> getAllOfType(Class<? extends Entity> type) {
        return allOfTypes.retrieve(type);
    }

    @Override
    public List<Entity> getAllNearby(Point location, int distance) {
        List<Entity> result = new ArrayList<>();
        FuncTools.rectIter(new Point(location.getX() - distance, location.getY() - distance), distance * 2, distance * 2,
                new BiConsumer<Integer, Integer>() {
                    @Override
                    public void accept(Integer x, Integer y) {
                        result.addAll(entitiesLocations.get(new Point(x, y)));
                    }
                });
        return result;
    }

    @Override
    public void updateEntityLocation(Entity e, Point newLocation) {
        entitiesLocations.get(e.getSelf().getLocation()).remove(e.getSelf());
        entitiesLocations.get(newLocation).add(e.getSelf());
    }

    @Override
    public void kill(Entity e) {
        toKill.add(e);
    }

    @Override
    public Entity getNearestOfType(Function<Entity, Boolean> tester, Point nearTo) {
        final Entity[] result = {null};
        FuncTools.spiralIter(nearTo, new Function<Point, Boolean>() {
            @Override
            public Boolean apply(Point point) {
                Collection<Entity> inCell = entitiesLocations.get(point);
                for (Entity e : inCell) {
                    if (tester.apply(e)) {
                        result[0] = e;
                        return true;
                    }
                }
                return false;
            }
        });
        return result[0];
    }

    @Override
    public int getCountInCell(Point location) {
        return entitiesLocations.get(location).size();
    }
}
