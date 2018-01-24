package com.htuy.gridgame.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.input.BasicInputProvider;
import com.htuy.gridgame.input.MouseInputProvider;
import com.htuy.gridgame.renderer.Renderer;
import com.htuy.gridgame.renderer.display_renderer.SimpleHeightmapRenderer;

public class BasicDisplayModule extends AbstractModule {

    public static Module getInstance() {
        return Modules.override(new BasicModule()).with(new BasicDisplayModule());
    }

    @Override
    protected void configure() {
        bind(Renderer.class).to(SimpleHeightmapRenderer.class);
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(250);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(250);
        bind(MouseInputProvider.class).to(BasicInputProvider.class);
    }

}
