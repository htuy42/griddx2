package com.htuy.gridgame.entity.alive_entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

public class AliveEntity implements Entity {

    Color color;
    float size;

    Point location;
    float energy;
    MovementDecider mover;
    EnergizeDecider energizer;
    ChildMaker childMaker;
    int babyThreshold;
    float eatScalar;
    private float timeSinceMove = 0.0f;

    protected AliveEntity(Color color, float size, Point location, float energy, MovementDecider mover, EnergizeDecider energizer, ChildMaker childMaker, int babyThreshold,
                          float eatScalar) {
        this.color = color;
        this.size = size;
        this.location = location;
        this.energy = energy;
        this.mover = mover;
        this.energizer = energizer;
        this.childMaker = childMaker;
        this.babyThreshold = babyThreshold;
        this.eatScalar = eatScalar;
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        renderCircleSelf(color, size, renderer, display);
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        energy -= 1.0f * main.LAST_DELTA * eatScalar;
        handleMove(grid, entities);
        energizer.energize(this, grid, entities);
        if (energy > babyThreshold + 1) {
            energy = babyThreshold + 1;
        }
        handleBirth(grid, entities);
        return energy <= 0;
    }

    private void handleMove(GridProvider grid, EntityProvider entities) {
        timeSinceMove += main.LAST_DELTA;
        if (timeSinceMove > .1) {
            Point curLoc = getLocation();
            Point newLocation = mover.move(this, grid, entities);
            entities.updateEntityLocation(this, newLocation);
            location = newLocation;
            energy -= location.manhattanDistanceTo(curLoc) * .1f * eatScalar;
            timeSinceMove = 0;
        }
    }

    private void handleBirth(GridProvider grid, EntityProvider entities) {
        if (energy > babyThreshold) {
            energy /= 3;
            AliveEntity child = childMaker.makeChild(this, grid, entities);
            child.location = grid.randomAdjoiningLocations(location);
            entities.spawnAtNextTick(child);
        }
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public void addEnergy(int amount) {
        energy += amount;
    }

    public float getSize() {
        return size;
    }

    public float getEnergy() {
        return energy;
    }

    public static class Builder {
        Color color = Color.BLACK;
        float size = 1.0f;
        Point location = new Point(0, 0);
        float energy = 0;
        float timeSinceMove = 0.0f;
        MovementDecider mover = new RandomMover();
        EnergizeDecider energizer;
        ChildMaker childMaker = new CopyBirther();
        int babyThreshold;
        float eatScalar;

        public AliveEntity build() {
            return new AliveEntity(color, size, location, energy, mover, energizer, childMaker, babyThreshold, eatScalar);
        }

        public void setEatScalar(float eatScalar) {
            this.eatScalar = eatScalar;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setSize(float size) {
            this.size = size;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

        public void setEnergy(float energy) {
            this.energy = energy;
        }

        public void setTimeSinceMove(float timeSinceMove) {
            this.timeSinceMove = timeSinceMove;
        }

        public void setMover(MovementDecider mover) {
            this.mover = mover;
        }

        public void setEnergizer(EnergizeDecider energizer) {
            this.energizer = energizer;
        }

        public void setChildMaker(ChildMaker childMaker) {
            this.childMaker = childMaker;
        }

        public void setBabyThreshold(int babyThreshold) {
            this.babyThreshold = babyThreshold;
        }
    }
}
