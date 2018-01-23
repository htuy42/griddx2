package com.htuy.gridgame.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.BasicCell;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.FlatDictProvider;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.Renderer;
import com.htuy.gridgame.renderer.textrenderer.StringAppenderOutput;
import com.htuy.gridgame.renderer.textrenderer.TextOutput;
import com.htuy.gridgame.renderer.textrenderer.TextRenderer;
import com.htuy.gridgame.world.BasicWorld;
import com.htuy.gridgame.world.GridWorld;

public class TestTextModule extends AbstractModule {

    public static final int VIEW_WIDTH = 10;
    public static final int VIEW_HEIGHT = 15;
    private final Class<? extends GridProvider> providerClass;

    public static Module getInstance(Class<? extends GridProvider> providerClass) {
        return Modules.override(new BasicModule()).with(new TestTextModule(providerClass));
    }

    private static class TestGenerator implements CellGenerator {

        private int curHeight = 0;

        @Override
        public Cell nextCell(Point p) {
            return new BasicCell(curHeight++);
        }
    }

    public TestTextModule(){
        this.providerClass = FlatDictProvider.class;
    }

    public TestTextModule(Class<? extends GridProvider> gridProvider){
        this.providerClass = gridProvider;
    }

    @Override
    protected void configure() {
        bind(GridProvider.class).to(providerClass);
        bind(Renderer.class).to(TextRenderer.class);
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(VIEW_WIDTH);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(VIEW_HEIGHT);
        bind(CellGenerator.class).toInstance(new TestGenerator());
        bind(GridWorld.class).to(BasicWorld.class);
        bind(TextOutput.class).to(StringAppenderOutput.class);
    }

    public static Module getInstance(){
        return Modules.override(new BasicModule()).with(new TestTextModule());
    }

    @Override
    public String toString() {
        return "Test text module with " + this.providerClass.getName();
    }
}
