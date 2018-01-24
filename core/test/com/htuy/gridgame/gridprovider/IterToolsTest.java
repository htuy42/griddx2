package com.htuy.gridgame.gridprovider;

import com.google.inject.Guice;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.modules.TestTextModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IterToolsTest {

    private GridProvider provider;

    @Before
    public void setup() {
        provider = Guice.createInjector(TestTextModule.getInstance()).getInstance(GridProvider.class);
    }

    @Test
    public void iterRows() throws Exception {
        final int[] seenSum = {0};
        final int[] expectedSum = {0};
        final int[] cellIndex = {0};
        IterTools.iterRows(provider, provider.getFullView(), cells -> {
            for (Cell c : cells) {
                seenSum[0] += c.getHeight();
                expectedSum[0] += cellIndex[0];
                cellIndex[0] += 1;
                assertEquals(seenSum[0], expectedSum[0]);
            }
        });
        assertEquals(cellIndex[0], TestTextModule.VIEW_HEIGHT * TestTextModule.VIEW_WIDTH);
    }

}