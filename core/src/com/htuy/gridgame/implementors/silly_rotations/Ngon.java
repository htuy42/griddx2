package com.htuy.gridgame.implementors.silly_rotations;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.google.common.collect.ImmutableList;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;

import java.util.*;

public class Ngon extends BaseEntity {

    private int sides;
    private Polygon shape;
    private List<Color> colors = new ArrayList<>();
    private Point origin;
    private float tX = 0;
    private float tY = 0;
    private String descr;

    public Ngon(Point location, int sides, String descr) {
        super(location);
        this.sides = sides;
        this.descr = descr;
        shape = new Polygon();
        float[] array = new float[sides * 2];
        List<Float> vertices = new ArrayList<>();
        Point curPoint = new Point(200, 50);
        Vector2 move = new Vector2(100, 0);
        float smx = 0;
        float smy = 0;
        float mxX = -10000;
        for (int x = 0; x < sides; x++) {
            array[x * 2] = curPoint.getX();
            array[x * 2 + 1] = curPoint.getY();
            if (curPoint.getX() > mxX) {
                mxX = curPoint.getX();
                tX = curPoint.getX();
                tY = curPoint.getY();
            }
            smx += curPoint.getX();
            smy += curPoint.getY();
            curPoint = curPoint.withDx(move.x).withDy(move.y);
            move.rotate(360.0f / sides);
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

    public static List<Transform> collapseSequence(List<Transform> sequence, int sides) {
        Ngon real = new Ngon(new Point(0, 0), sides, "");
        for (Transform t : sequence) {
            t.perform(real);
        }

        return allTransforms(real).get(new FloatRapper(real.shape.getTransformedVertices()));
    }

    public void reflect() {
        float[] array = vertical_reflect(shape.getTransformedVertices());
        shape = new Polygon(array);
        shape.setOrigin(origin.getX(), origin.getY());
    }

    public static HashMap<FloatRapper, List<Transform>> allTransforms(Ngon gon) {
        HashMap<FloatRapper, List<Transform>> res = new HashMap<>();
        Transform flip = new Transform.ReflectTransform();
        for (int x = 0; x < gon.sides; x++) {
            Transform rot = new Transform.RotateTransform(x);
            Ngon n = new Ngon(new Point(0, 0), gon.sides, "");
            Ngon n2 = new Ngon(new Point(0, 0), gon.sides, "");
            rot.perform(n);
            res.put(new FloatRapper(n.shape.getTransformedVertices()), ImmutableList.of(rot));
            rot.perform(n2);
            flip.perform(n2);
            res.put(new FloatRapper(n2.shape.getTransformedVertices()), ImmutableList.of(rot, flip));
        }
        return res;
    }

    public void rotate(int intervals) {
        shape.rotate(360.0f * intervals / sides);
    }

    public int find_swap(float y, float[] points) {
        boolean found_first = false;
        for(int i = 0; i < points.length; i += 2) {
            if (Math.abs(points[i + 1] - y) < 2.5 * this.sides) {
                if (found_first) {
                    return i;
                }
                found_first = true;
            }
        }
        // not found
        return -1;
    }

    public float[] vertical_reflect(float[] points) {
        System.out.println(Arrays.toString(points));
        int size = points.length;
        float[] new_points = new float[size];
        int odd = ((size / 2) % 2);
        int num_symmetries = (size - odd) / 2; // technically this is the number of symmetries * 2
        int index_top = 0;
        if(odd == 1) {
            index_top = find_top(points);
            new_points[index_top] = points[index_top];
            new_points[index_top + 1] = points[index_top + 1];
        }
        LinkedList<Float> already_seen = new LinkedList<Float>();
        //boolean[] points_visited = new boolean[size];
        int times = 0;
        for(int i = 0; i < size && times < num_symmetries; i += 2, times += 1) {
            if(odd == 1 && i == index_top) {
                times -= 1;
                continue;
            }
            float curr_x = points[i];
            float curr_y = points[i + 1];
            if (member_epsilon(already_seen, curr_y)) {
                continue;
            }
            //points_visited[i] = true;
            already_seen.add(curr_y);
            int index = find_closest(curr_y, points);
//            if(points_visited[index]) {
//                continue;
//            }
            //points_visited[index] = true;
            System.out.println(i);
            new_points[index] = curr_x;
            new_points[index + 1] = points[i + 1];
            new_points[i] = points[index];
            new_points[i + 1] = points[index + 1];
        }
        return new_points;

    }

    public static int find_top(float[] points) {
        int largest_i = 0;
        for (int i = 0; i < points.length; i += 2) {
            if (points[i + 1] > points[largest_i + 1]) {
                largest_i = i;
            }
        }
        return largest_i;
    }

    public boolean member_epsilon(LinkedList<Float> points_seen, float new_point) {
        for (float n : points_seen) {
            if (Math.abs(n - new_point) < 2.5 * this.sides) {
                return true;
            }
        }
        return false;
    }

    public int find_closest(float y, float[] points) {
        boolean found_self = false;
        int closest = 0;
        for (int i = 0; i < points.length; i += 2) {
            if (!found_self) {
                if (points[i + 1] == y) {
                    found_self = true;
                    closest = i + 2;
                }
                continue;
            }
            if (Math.abs(points[i + 1] - y) < Math.abs(y - points[closest + 1])) {
                closest = i;
            }
        }
        return closest;
    }

    private static class FloatRapper {
        private float[] rapper;

        FloatRapper(float[] rapper) {
            this.rapper = rapper;
        }

        public int hashCode() {
            return 1;
        }

        public boolean equals(Object o) {
            FloatRapper fr = (FloatRapper) o;
            for (int x = 0; x < rapper.length; x++) {
                if (Math.abs(rapper[x] - fr.rapper[x]) > 5) {
                    return false;
                }
            }
            return true;
        }

    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        for (int x = 0; x < sides; x++) {
            Point p = new Point((int) shape.getTransformedVertices()[x * 2], (int) shape.getTransformedVertices()[x * 2 + 1]);
            renderer.setColor(colors.get(x));
            renderer.circle(p.getX(), p.getY(), 10);
        }
        SpriteBatch spriteBatch;
        spriteBatch = new SpriteBatch();

        spriteBatch.begin();
        SillyModule.font.setColor(Color.BLACK);
        SillyModule.font.draw(spriteBatch, descr, 10, 480);
        spriteBatch.end();
    }
}
