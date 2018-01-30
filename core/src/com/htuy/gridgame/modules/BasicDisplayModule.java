package com.htuy.gridgame.modules;

import com.badlogic.gdx.graphics.Color;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.input.BasicInputProvider;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.renderer.Renderer;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;
import com.htuy.gridgame.renderer.display_renderer.GradientColorProvider;
import com.htuy.gridgame.renderer.display_renderer.SimpleHeightmapRenderer;

public class BasicDisplayModule extends AbstractModule {

    public static Module getInstance() {
        return Modules.override(new BasicModule()).with(new BasicDisplayModule());
    }

    @Override
    protected void configure() {
        bind(Renderer.class).to(SimpleHeightmapRenderer.class);
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(150);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(150);
        bind(InputProvider.class).to(BasicInputProvider.class);
        bind(Color.class).annotatedWith(Names.named("High Color")).toInstance(new Color(255, 255, 255, 1));
        bind(Color.class).annotatedWith(Names.named("Low Color")).toInstance(new Color(0, 0, 0, 1));
        bind(ColorProvider.class).toInstance(new GradientColorProvider(BasicModule.MAX_CELL_HEIGHT, BasicModule.MIN_CELL_HEIGHT, Color.WHITE, Color.WHITE));
    }
}
