package com.htuy.gridgame.gridprovider;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public interface GridRowProvider extends GridProvider {

    static GridRowProvider toRowProvider(GridProvider provider) {
        if (provider instanceof GridRowProvider) {
            return (GridRowProvider) provider;
        }
        return new SimpleRowProvider(provider);
    }

    /**
     * Get up to an entire row from the provider.
     *
     * @param start The point to begin from
     * @param width The width of the row to fetch. If -1, the entire row after the given point will be returned
     * @return A list of cell, the row requested.
     */
    List<Cell> getRow(Point start, int width);

    class SimpleRowProvider implements GridRowProvider {
        private final GridProvider provider;

        SimpleRowProvider(GridProvider provider) {
            this.provider = provider;
        }

        @Override
        public List<Cell> getRow(Point start, int width) {
            List<Cell> result;
            if (width == -1) {
                result = new ArrayList<>();
                Point cur = start;
                while (provider.hasCell(cur)) {
                    result.add(provider.getCell(cur));
                    cur = cur.withDx(1);
                }
                return result;
            }
            result = new ArrayList<>(width);
            for (int i = 0; i < width; i++) {
                result.add(provider.getCell(start.getX() + i, start.getY()));
            }
            return result;
        }

        @Override
        public Cell getCell(Point toGet) {
            return provider.getCell(toGet);
        }

        @Override
        public void setCell(Point toSet, Cell setAs) {
            provider.setCell(toSet, setAs);
        }

        @Override
        public void save() {
            provider.save();
        }

        @Override
        public Cell getCell(int x, int y) {
            return provider.getCell(x, y);
        }

        @Override
        public boolean hasCell(Point toGet) {
            return provider.hasCell(toGet);
        }

        @Override
        public boolean hasCell(int x, int y) {
            return provider.hasCell(x, y);
        }

        @Override
        public void iterCells(BiConsumer<Point, Cell> execute) {
            provider.iterCells(execute);
        }

        @Override
        public View getFullView() {
            return provider.getFullView();
        }

    }

}
