package com.htuy.gridgame.display;

import com.badlogic.gdx.graphics.Color;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;
import com.htuy.gridgame.renderer.display_renderer.SimpleHeightmapRenderer;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayTest {


    @Test
    public void getCellSizeTest() {
        View v = new View(new Point(0, 0), 10, 10);
        Screen s = new Screen(100, 100);
        Display d = new Display(v,s);

        assertEquals(d.getCellSize(), 10);
        d.setViewWidth(5);
        assertEquals(d.getCellSize(), 10);
        d.setViewWidth(20);
        assertEquals(d.getCellSize(), 5);
        d.setViewWidth(10);
        d.setScreenHeight(20);
        assertEquals(d.getCellSize(), 2);
        d.setScreenHeight(200);
        assertEquals(d.getCellSize(), 10);
        d.setScreenWidth(200);
        assertEquals(d.getCellSize(), 20);
    }

}