package com.htuy.gridgame.gridprovider;

import com.google.inject.Guice;
import com.htuy.gridgame.cell.BasicCell;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.modules.TestTextModule;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GridRowProviderTest {

    private GridProvider provider;

    @Before
    public void setup() {
        provider = Guice.createInjector(TestTextModule.getInstance()).getInstance(GridProvider.class);
    }

    @Test
    public void toRowProvider() throws Exception {
        GridProvider gp = GridRowProvider.toRowProvider(provider);
        assertNotEquals(gp, provider);
        assertEquals(gp, GridRowProvider.toRowProvider(gp));
    }

    @Test
    public void basicFunction() throws Exception {
        GridRowProvider gp = GridRowProvider.toRowProvider(provider);
        Point p = new Point(0, 0);


        assertEquals(gp.getCell(p), provider.getCell(p));
        assertFalse(gp.hasCell(-1, -1));
        assertEquals(gp.getFullView().getHeight(), provider.getFullView().getHeight());
        assertEquals(gp.getFullView().getWidth(), provider.getFullView().getWidth());
        assertEquals(provider.getCell(p).getHeight(), 0);
        gp.setCell(p, new BasicCell(10));
        assertEquals(provider.getCell(p).getHeight(), 10);
    }

    @Test
    public void getRow() throws Exception {
        GridRowProvider gp = GridRowProvider.toRowProvider(provider);
        Point p = new Point(0, 0);

        List<Cell> cells = gp.getRow(p, -1);
        assertEquals(cells.size(), TestTextModule.VIEW_WIDTH);
        testRow(cells, 0);

        List<Cell> shortenedCells = gp.getRow(p, 4);
        assertEquals(shortenedCells.size(), 4);

        cells = gp.getRow(new Point(2, 0), -1);
        assertEquals(cells.size(), TestTextModule.VIEW_WIDTH - 2);
        testRow(cells, 2);

        cells = gp.getRow(new Point(0, 3), -1);
        assertEquals(cells.size(), TestTextModule.VIEW_WIDTH);
        testRow(cells, TestTextModule.VIEW_WIDTH * 3);
    }

    private void testRow(List<Cell> cells, int indexOffset) {
        for (int x = 0; x < cells.size(); x++) {
            assertEquals(x + indexOffset, cells.get(x).getHeight());
        }
    }

}