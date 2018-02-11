package com.htuy.gridgame.implementors.silly_rotations;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.google.common.collect.ImmutableList;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Ngon extends BaseEntity {

    private int sides;
    private Polygon shape;
    private List<Color> colors = new ArrayList<>();
    private Point origin;
    private float tX = 0;
    private float tY = 0;

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
        shape.rotate(360 * intervals / sides);
    }

    public void reflect() {
        float[] array = vertical_reflect(shape.getTransformedVertices());
        shape = new Polygon(array);
        shape.setOrigin(origin.getX(), origin.getY());
    }

//     public float[] vertical_reflect_old(float[] points) {
// //        points[i] = tX;
// //        points[i+1] = tY;
//         int size = points.length;
//         float[] new_points = new float[size];
//         boolean past = false;
//         int e = (((size / 2) + 1) % 2);
//         int i = 0;
//         int k = 1;
//         while (i < size) {
//             if (past) {
//                 new_points[i - 2 * k] = points[i];
//                 new_points[i - 1 * k] = points[i + 1];
//                 k += 1;
//             } else {
//                 new_points[size - i - 1] = points[i + 1];
//                 new_points[size - i - 2] = points[i];
//             }
// //            if(!past && points[i] == tX && points[i+1] == tY){
// //                past = true;
// //            }
//             if (!past && (i == ((size / 2) - e))) {
//                 past = true;
//             }
//             i += 2;
//         }

        return new_points;

    }

     public static int find_top(float[] points) {
        int largest_i = 0;  
        for(int i = 0; i < points.length; i += 2) {
            if(points[i] > points[largest_i]) {
                largest_i = i; 
            }
        }
        return largest_i; 
    }

    public static int find_swap(float x, float[] points) {
        boolean found_first = false; 
        for(int i = 0; i < points.length; i += 2) {
            if(points[i] == x) {
                if(found_first) {
                    return i; 
                }
                found_first = true; 
            }
        }
        // not found
        return -1; 
    }
    
    public static float[] vertical_reflect(float[] points) {
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
        List already_seen = new LinkedList(); 
        int times = 0;
        for(int i = 0; i < size && times < num_symmetries; i += 2, times += 1) {
            if(odd == 1 && i == index_top) {
                times -= 1; 
                continue; 
            }
            float curr_x = points[i]; 
            if(already_seen.contains(curr_x)) {
                continue; 
            }
            already_seen.add(curr_x); 
            int index = find_swap(curr_x, points); 
            new_points[index] = curr_x;
            new_points[index + 1] = points[i + 1]; 
            new_points[i] = points[index]; 
            new_points[i + 1] = points[index + 1]; 
        }
        return new_points; 
    
    }


    public List<Transform> collapseSequence(List<Transform> sequence) {
//        Ngon real = new Ngon(new Point(0,0),sides);
        Ngon real = this;
        for (Transform t : sequence) {
            t.perform(real);
        }
//        if(allTransforms().get(new FloatRapper(this.shape.getTransformedVertices())) == null){
//            System.out.println("CORRECT");
//            for(float f : this.shape.getTransformedVertices()){
//                System.out.println(f);
//            }
//        }

        return allTransforms().get(new FloatRapper(real.shape.getTransformedVertices()));
//        return sequence;
    }

    public HashMap<FloatRapper, List<Transform>> allTransforms() {
        HashMap<FloatRapper, List<Transform>> res = new HashMap<>();
        Transform flip = new Transform.ReflectTransform();
        for (int x = 0; x < sides; x++) {
            Transform rot = new Transform.RotateTransform(x);
            Ngon n = new Ngon(new Point(0, 0), sides);
            Ngon n2 = new Ngon(new Point(0, 0), sides);
            rot.perform(n);
            res.put(new FloatRapper(n.shape.getTransformedVertices()), ImmutableList.of(rot));
            rot.perform(n2);
            flip.perform(n2);
            res.put(new FloatRapper(n2.shape.getTransformedVertices()), ImmutableList.of(rot, flip));
        }
//        for(List<Transform> transforms : res.values()){
//            System.out.println("transform:");
//            for(Transform e : transforms){
//                System.out.println(e.toString());
//            }
//        }
//        for(FloatRapper f : res.keySet()){
//            System.out.println("GWESS");
//            for(float f1 : f.rapper){
//                System.out.println(f1);
//            }
//        }
        return res;
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
    }
}
