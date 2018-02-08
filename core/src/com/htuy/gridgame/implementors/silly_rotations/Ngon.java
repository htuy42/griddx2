package com.htuy.gridgame.implementors.silly_rotations;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ngon extends BaseEntity {

    private int sides;
    private Polygon shape;
    private List<Color> colors = new ArrayList<>();
    private Point origin;

    public Ngon(Point location, int sides) {
        super(location);
        this.sides = sides;
        shape = new Polygon();
        float[] array = new float[sides * 2];
        List<Float> vertices = new ArrayList<>();
        Point curPoint = new Point(500, 500);
        Vector2 move = new Vector2(100, 0);
        float smx = 0;
        float smy = 0;
        for (int x = 0; x < sides; x++) {
            array[x * 2] = curPoint.getX();
            array[x * 2 + 1] = curPoint.getY();
            smx += curPoint.getX();
            smy += curPoint.getY();
            curPoint = curPoint.withDx(move.x).withDy(move.y);
            move.rotate(360 / sides);
            colors.add(randomColor());
        }
        shape = new Polygon(array);
//        shape.setPosition(500,500);
        origin = new Point((int) (smx / sides), (int) smy / sides);
        shape.setOrigin(smx / sides, smy / sides);
    }

    private Color randomColor() {
        Random r = new Random();

        return new Color((float) r.nextDouble(), (float) r.nextDouble(), (float) r.nextDouble(), (float) 1.0);
    }

    public void rotate(int intervals) {
        System.out.println("help" + intervals + " " + 360 * intervals / sides);
        shape.rotate(360 * intervals / sides);
    }

    public void reflect() {
        System.out.println("what!");
        float[] array = new float[sides * 2];

        for (int x = 0; x < sides; x++) {
            Point p = new Point((int) shape.getTransformedVertices()[x * 2], (int) shape.getTransformedVertices()[x * 2 + 1]);
            array[x * 2] = p.getX();
            array[x * 2 + 1] = p.getY() * -1 + 1000;
            System.out.println(p.getY() + " " + p.getY() * -1);
        }
        shape = new Polygon(array);
        shape.setOrigin(origin.getX(), origin.getY());
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        for (int x = 0; x < sides; x++) {
            Point p = new Point((int) shape.getTransformedVertices()[x * 2], (int) shape.getTransformedVertices()[x * 2 + 1]);
            renderer.setColor(colors.get(x));
            renderer.circle(p.getX(), p.getY(), 10);
        }
    }
}
