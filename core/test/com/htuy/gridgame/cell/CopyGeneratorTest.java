package com.htuy.gridgame.cell;

import com.htuy.gridgame.geom_tools.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CopyGeneratorTest {


    @Test
    public void nextCell() throws Exception {
        CopyGenerator cg = new CopyGenerator(BasicCell.class);
        for (int i = 0; i < 10000; i++) {
            Cell c = cg.nextCell(new Point(0, 0));
            assert (c instanceof BasicCell);
            assertNotNull(c);
        }
        cg = new CopyGenerator(TestCell.class);
        for (int i = 0; i < 10000; i++) {
            Cell c = cg.nextCell(new Point(0, 0));
            assert (c instanceof TestCell);
            assertNotNull(c);
            assertEquals(c.getHeight(), 0);
        }
    }

    private static class TestCell implements Cell {

    }

}