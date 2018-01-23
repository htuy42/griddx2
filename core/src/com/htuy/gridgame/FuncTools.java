package com.htuy.gridgame;

import com.htuy.gridgame.geom_tools.Point;

import java.util.function.BiConsumer;

public class FuncTools {

    public static void wrap(Runnable wrapper, Runnable inner) {
        // really, this function shouldn't exist, because java syntax winds up meaning any call to it
        // will look like a mess. But I feel like that shouldn't be the case so I'm using it anyways.
        wrapper.run();
        inner.run();
        wrapper.run();
    }

    public static void rectIter(Point start, int width, int height, BiConsumer<Integer, Integer> perform) {
        for (int y = start.getY(); y < start.getY() + height; y++) {
            for (int x = start.getX(); x < start.getX() + width; x++) {
                perform.accept(x, y);
            }
        }
    }

    public static void rectIter(int width, int height, BiConsumer<Integer,Integer> perform){
        rectIter(new Point(0,0),width,height,perform);
    }

}
