package com.htuy.gridgame;

import com.htuy.gridgame.geom_tools.Point;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FuncTools {

    public static void wrap(Runnable wrapper, Runnable inner) {
        // really, this function shouldn't exist, because java syntax winds up meaning any call to it
        // will look like a mess. But I feel like that shouldn't be the case so I'm using it anyways.
        wrapper.run();
        inner.run();
        wrapper.run();
    }

    public static void rectIter(int width, int height, BiConsumer<Integer, Integer> perform) {
        rectIter(new Point(0, 0), width, height, perform);
    }

    public static void rectIter(Point start, int width, int height, BiConsumer<Integer, Integer> perform) {
        for (int y = start.getY(); y < start.getY() + height; y++) {
            for (int x = start.getX(); x < start.getX() + width; x++) {
                perform.accept(x, y);
            }
        }
    }

    public static void atDelta(float deltaAmount, AtDeltaFunction function) {
        function.deltaCount += main.LAST_DELTA;
        if (function.deltaCount >= deltaAmount) {
            function.apply();
            function.deltaCount = 0;
        }
    }

    public static void spiralIter(Point location, Function<Point, Boolean> atStep) {
        Set<Point> visited = new HashSet<>();
        int range = 0;
        final boolean[] done = {false};
        while (range < 10 && !done[0]) {
            Point s = new Point(location.getX() - range, location.getY() - range);
            rectIter(s, range * 2, range * 2, new BiConsumer<Integer, Integer>() {
                @Override
                public void accept(Integer x, Integer y) {
                    if (done[0]) {
                        return;
                    }
                    Point p = new Point(x, y);
                    if (visited.contains(p)) {
                        return;
                    } else {
                        if (atStep.apply(p)) {
                            done[0] = true;
                            return;
                        }
                        visited.add(p);
                    }
                }
            });
            range += 1;
        }
    }

    public static abstract class AtDeltaFunction {
        int deltaCount;

        public abstract void apply();
    }

}
