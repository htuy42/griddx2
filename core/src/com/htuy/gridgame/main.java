package com.htuy.gridgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.htuy.gridgame.implementors.silly_rotations.SillyModule;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.world.GridWorld;


public class main extends ApplicationAdapter {
    public static InputProvider inputRegistry;
    private SpriteBatch batch;
    private GridWorld world;
    private float sm = 0;
    private int cnt = 0;
    public static double LAST_DELTA;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Injector injector = Guice.createInjector(SillyModule.getInstance());
        world = injector.getInstance(GridWorld.class);
        inputRegistry = injector.getInstance(InputProvider.class);
        Gdx.input.setInputProcessor(inputRegistry);
        injector.getInstance(InputFunctions.class).bindInitialInputFunctions(inputRegistry, world);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.tick();
        batch.begin();
        world.render();
        batch.end();
        handleFps();
    }

    private void handleFps() {
        //in theory would eventually wrap and fail. We don't care.
        LAST_DELTA = Gdx.graphics.getRawDeltaTime();
        sm += LAST_DELTA;
        cnt += 1;
        if (cnt % 100 == 0) {
            System.out.println(1 / (sm / cnt));
            sm = 0;
            cnt = 0;
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
