package com.htuy.gridgame.gridprovider;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.List;
import java.util.function.Consumer;

public class IterTools {

    public static void IterRows(GridProvider provider, View view, Consumer<List<Cell>> iterFunc) {
        GridRowProvider rowProvider = GridRowProvider.toRowProvider(provider);
        for (int y = view.getLoc().getY(); y < view.getLoc().getY() + view.getHeight(); y++) {
            iterFunc.accept(rowProvider.getRow(new Point(view.getLoc().getX(), y), view.getWidth()));
        }
    }
}
