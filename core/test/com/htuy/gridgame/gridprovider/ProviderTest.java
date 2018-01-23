package com.htuy.gridgame.gridprovider;

import com.google.inject.AbstractModule;
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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;


public class ProviderTest {

    private static List<Module> allModules;

    static{
        allModules = new ArrayList<>();
        allModules.add(TestTextModule.getInstance(FlatDictProvider.class));
        allModules.add(TestTextModule.getInstance(ArrayProvider2d.class));
    }

    private GridProvider provider;
    private Random random;

    @Test
    public void orchestrateTests() throws Exception{
        random = new Random();
        for (Module mod : allModules) {
            System.out.println(mod.toString());
            Injector inject = Guice.createInjector(mod);
            provider = inject.getInstance(GridProvider.class);
            runTests();
        }
    }

    private void runTests() throws Exception{
        getCell();
        setCell();
        save();
        getCellPoint();
        hasCell();
        hasCellPoint();
        iterCells();
        getFullView();
    }

    public void getCell() throws Exception {
        testCellFunction(new BiFunction<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x, Integer y) {
                if (provider.hasCell(x, y)) {
                    Cell c = provider.getCell(x, y);
                    return c.getHeight() == y * TestTextModule.VIEW_WIDTH + x;
                }
                return false;
            }
        });
    }

    public void setCell() throws Exception {
        assertEquals(provider.getCell(0, 0).getHeight(), 0);
        provider.setCell(new Point(0, 0), new BasicCell(2));
        assertEquals(provider.getCell(0, 0).getHeight(), 2);
        provider.setCell(new Point(0, 0), new BasicCell(0));
        assertEquals(provider.getCell(0, 0).getHeight(), 0);
    }

    public void save() throws Exception {
        //
    }


    public void getCellPoint() throws Exception {
        testCellFunction(new BiFunction<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x, Integer y) {
                if (provider.hasCell(x, y)) {
                    Cell c = provider.getCell(new Point(x, y));
                    return c.getHeight() == y * TestTextModule.VIEW_WIDTH + x;
                }
                return false;
            }
        });
    }

    public void hasCell() throws Exception {
        testCellFunction(provider::hasCell);
    }

    public void hasCellPoint() throws Exception {
        testCellFunction(new BiFunction<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x, Integer y) {
                return provider.hasCell(new Point(x, y));
            }
        });
    }

    public void iterCells() throws Exception {
    }

    public void getFullView() throws Exception {
        View v = provider.getFullView();
        assert (v.getLoc().getY() == 0);
        assert (v.getLoc().getX() == 0);
        assert (v.getWidth() == TestTextModule.VIEW_WIDTH);
        assert (v.getHeight() == TestTextModule.VIEW_HEIGHT);
    }

    public void testCellFunction(BiFunction<Integer, Integer, Boolean> test) {
        FuncTools.rectIter(new Point(0, 0), TestTextModule.VIEW_WIDTH, TestTextModule.VIEW_HEIGHT, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer x, Integer y) {
                assert (test.apply(x, y));
            }
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