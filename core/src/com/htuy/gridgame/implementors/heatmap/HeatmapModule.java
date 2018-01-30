package com.htuy.gridgame.implementors.heatmap;


import com.badlogic.gdx.graphics.Color;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;
import com.htuy.gridgame.renderer.display_renderer.GradientColorProvider;

public class HeatmapModule extends AbstractModule {

    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new HeatmapModule());
    }

    @Override
    protected void configure() {
        bind(CellGenerator.class).toInstance(new CopyGenerator(DistanceHeatCell.class));
        bind(ColorProvider.class).to(GradientColorProvider.class);
        bind(InputFunctions.class).to(HeatmapInputFunctions.class);
        bind(Color.class).annotatedWith(Names.named("Low Color")).toInstance(Color.YELLOW);
        bind(Color.class).annotatedWith(Names.named("High Color")).toInstance(Color.RED);
    }
}
