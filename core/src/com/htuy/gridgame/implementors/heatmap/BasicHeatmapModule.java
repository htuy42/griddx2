package com.htuy.gridgame.implementors.heatmap;


import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;

public class BasicHeatmapModule extends AbstractModule {

    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new BasicHeatmapModule());
    }

    @Override
    protected void configure() {
        bind(CellGenerator.class).toInstance(new CopyGenerator(DistanceHeatCell.class));
        bind(ColorProvider.class).to(BasicHeatmapColorProvider.class);
    }
}
