package com.htuy.gridgame.gridprovider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.cell.BasicCell;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.modules.TestTextModule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;


public class ProviderTest {

    private static final List<Module> allModules;

    static {
        allModules = new ArrayList<>();
        allModules.add(TestTextModule.getInstance(FlatDictProvider.class));
        allModules.add(TestTextModule.getInstance(ArrayProvider2d.class));
    }

    private GridProvider provider;
    private Random random;

    @Test
    public void orchestrateTests() throws Exception {
        random = new Random();
        for (Module mod : allModules) {
            System.out.println(mod.toString());
            Injector inject = Guice.createInjector(mod);
            provider = inject.getInstance(GridProvider.class);
            runTests();
        }
    }

    private void runTests() throws Exception {
        getCell();
        setCell();
        save();
        getCellPoint();
        hasCell();
        hasCellPoint();
        iterCells();
        getFullView();
    }

    private void getCell() {
        testCellFunction((x, y) -> {
            if (provider.hasCell(x, y)) {
                Cell c = provider.getCell(x, y);
                return c.getHeight() == y * TestTextModule.VIEW_WIDTH + x;
            }
            return false;
        });
    }

    private void setCell() {
        assertEquals(provider.getCell(0, 0).getHeight(), 0);
        provider.setCell(new Point(0, 0), new BasicCell(2));
        assertEquals(provider.getCell(0, 0).getHeight(), 2);
        provider.setCell(new Point(0, 0), new BasicCell(0));
        assertEquals(provider.getCell(0, 0).getHeight(), 0);
    }

    private void save() {
        //
    }


    private void getCellPoint() {
        testCellFunction((x, y) -> {
            if (provider.hasCell(x, y)) {
                Cell c = provider.getCell(new Point(x, y));
                return c.getHeight() == y * TestTextModule.VIEW_WIDTH + x;
            }
            return false;
        });
    }

    private void hasCell() {
        testCellFunction(provider::hasCell);
    }

    private void hasCellPoint() {
        testCellFunction((x, y) -> provider.hasCell(new Point(x, y)));
    }

    private void iterCells() {
    }

    private void getFullView() {
        View v = provider.getFullView();
        assert (v.getLoc().getY() == 0);
        assert (v.getLoc().getX() == 0);
        assert (v.getWidth() == TestTextModule.VIEW_WIDTH);
        assert (v.getHeight() == TestTextModule.VIEW_HEIGHT);
    }

    private void testCellFunction(BiFunction<Integer, Integer, Boolean> test) {
        FuncTools.rectIter(new Point(0, 0), TestTextModule.VIEW_WIDTH, TestTextModule.VIEW_HEIGHT, (x, y) -> {
            assert (test.apply(x, y));
        });
        for (int i = 0; i < 1000000; i++) {
            int x = random.nextInt();
            int y = random.nextInt();
            if ((x > 0 && x < 10) || (y > 0 && y < 10)) {
                continue;
            }
            assert (!test.apply(x, y));
        }
    }

}