package com.htuy.gridgame.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.htuy.gridgame.cell.BasicCell;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.gridprovider.ArrayProvider2d;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.textrenderer.SystemOutput;
import com.htuy.gridgame.renderer.textrenderer.TextOutput;
import com.htuy.gridgame.world.BasicWorld;
import com.htuy.gridgame.world.GridWorld;

public class BasicModule extends AbstractModule {

    static final int MAX_CELL_HEIGHT = 100;
    static final int MIN_CELL_HEIGHT = 0;

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("Max Cell Height")).toInstance(MAX_CELL_HEIGHT);
        bind(Integer.class).annotatedWith(Names.named("Min Cell Height")).toInstance(MIN_CELL_HEIGHT);
        bind(GridProvider.class).to(ArrayProvider2d.class);
        bind(GridWorld.class).to(BasicWorld.class);
        bind(CellGenerator.class).toInstance(new CopyGenerator(BasicCell.class));
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(100);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(100);
        bind(Integer.class).annotatedWith(Names.named("Screen Width, Pixels")).toInstance(900);
        bind(Integer.class).annotatedWith(Names.named("Screen Height, Pixels")).toInstance(900);
        bind(TextOutput.class).to(SystemOutput.class);
    }



}
