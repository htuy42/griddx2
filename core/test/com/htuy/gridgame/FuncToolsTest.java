package com.htuy.gridgame;

import com.htuy.gridgame.geom_tools.Point;
import org.junit.Before;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FuncToolsTest {

    private Random random;

    @Before
    public void setup() {
        random = new Random();
    }


    @org.junit.Test
    public void wrap() throws Exception {
        final int[] counterOuter = {0};
        final int[] counterInner = {0};
        FuncTools.wrap(
                () -> counterOuter[0] += 1,
                () -> {
                    assertEquals(counterOuter[0], 1);
                    counterInner[0] += 1;
                });
        assertEquals(counterInner[0], 1);
        assertEquals(counterOuter[0], 2);
    }

    @org.junit.Test
    public void rectIter() throws Exception {
        for (int i = 0; i < 5; i++) {
            int startX = random.nextInt(20) - 10;
            int startY = random.nextInt(20) - 10;
            int width = random.nextInt(10);
            int height = random.nextInt(10);
            Set<Point> pointsEncountered = new HashSet<>();
            FuncTools.rectIter(new Point(startX, startY), width, height, (x, y) -> pointsEncountered.add(new Point(x, y)));
            for (int x = startX; x < startX + width; x++) {
                for (int y = startY; y < startY + height; y++) {
                    assert (pointsEncountered.contains(new Point(x, y)));
                }
            }
        }


    }

}