package com.htuy.gridgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.implementors.heatmap.BasicHeatmapModule;
import com.htuy.gridgame.input.MouseInputProvider;
import com.htuy.gridgame.world.GridWorld;

public class main extends ApplicationAdapter {
    private SpriteBatch batch;
    private GridWorld world;
    public static MouseInputProvider inputRegistry;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Injector injector = Guice.createInjector(BasicHeatmapModule.getInstance());
        world = injector.getInstance(GridWorld.class);
        inputRegistry = injector.getInstance(MouseInputProvider.class);
        Gdx.input.setInputProcessor(inputRegistry);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.tick();
        batch.begin();
        world.render();
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
