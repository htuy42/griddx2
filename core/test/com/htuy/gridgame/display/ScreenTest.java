package com.htuy.gridgame.display;

import com.htuy.gridgame.geom_tools.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScreenTest {
    @Test
    public void toScreenLocation() throws Exception {
        View v = new View(new Point(0,0),10,10);
        Screen s = new Screen(100,100);
        Display d = new Display(v,s);
        assertEquals(d.toScreenLocation(new Point(3,3)),new Point(30,30));
        d.setViewWidth(20);
        assertEquals(d.toScreenLocation(new Point(3,3)),new Point(15,15));
    }

    @Test
    public void toGridLocation() throws Exception {
        View v = new View(new Point(0,0),10,10);
        Screen s = new Screen(100,100);
        Display d = new Display(v,s);
        assertEquals(d.toViewLoc(new Point(90,90)),new Point(9,9));
        d.setViewLoc(new Point(-1,0));
        assertEquals(d.toViewLoc(new Point(90,90)),new Point(8,9));
        d.setViewLoc(new Point(1,0));
        assertEquals(d.toViewLoc(new Point(90,90)),new Point(10,9));

    }

}