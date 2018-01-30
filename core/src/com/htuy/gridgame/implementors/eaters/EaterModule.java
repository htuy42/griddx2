package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.Color;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;
import com.htuy.gridgame.renderer.display_renderer.GradientColorProvider;
import com.htuy.gridgame.world.WorldBuilder;

public class EaterModule extends AbstractModule {
    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new EaterModule());
    }

    @Override
    protected void configure() {
        bind(CellGenerator.class).toInstance(new CopyGenerator(BasicPlantCell.class));
        bind(ColorProvider.class).to(GradientColorProvider.class);
        bind(WorldBuilder.class).to(EaterWorldBuilder.class);
        bind(Color.class).annotatedWith(Names.named("Low Color")).toInstance(new Color(105f / 255, 70f / 255, 41f / 255, 1));
        bind(Color.class).annotatedWith(Names.named("High Color")).toInstance(new Color(.17f, .48f, .12f, 1));
    }
}
