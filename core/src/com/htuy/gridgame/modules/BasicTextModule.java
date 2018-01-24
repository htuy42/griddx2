package com.htuy.gridgame.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.BasicCell;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.renderer.Renderer;
import com.htuy.gridgame.renderer.textrenderer.TextRenderer;
import com.htuy.gridgame.world.BasicWorld;
import com.htuy.gridgame.world.GridWorld;

class BasicTextModule extends AbstractModule {
    public static Module getInstance() {
        return Modules.override(new BasicModule()).with(new BasicTextModule());
    }

    @Override
    protected void configure() {
        bind(Renderer.class).to(TextRenderer.class);
        bind(CellGenerator.class).toInstance(new CopyGenerator(BasicCell.class));
        bind(GridWorld.class).to(BasicWorld.class);
    }
}
