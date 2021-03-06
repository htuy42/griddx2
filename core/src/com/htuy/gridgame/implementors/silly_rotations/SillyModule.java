package com.htuy.gridgame.implementors.silly_rotations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.KeyDownListener;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.world.GridWorld;
import com.htuy.gridgame.world.WorldBuilder;

public class SillyModule extends AbstractModule {
    public static BitmapFont font = new BitmapFont();
    public static Ngon gon = new Ngon(Point.randomPoint(new View(1, 1)), 3, "Hit n to get a new shape with a different number of sides.", true);
    public static Ngon otherGon = new Ngon(new Point(0, 0), 3, "", false);

    public static EntityProvider provider;
    private static int qdDir = 0;
    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new SillyModule());
    }

    @Override
    protected void configure() {
        otherGon.colors = gon.colors;
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(650);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(650);
        Point gravityMouse = new Point(0, 0);
        bind(InputFunctions.class).toInstance(new InputFunctions() {
            @Override
            public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
                inputProvider.registerKeyDownListener(Input.Keys.R, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        otherGon.rotate(-1);
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.L, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        otherGon.rotate(-1);
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.F, new KeyDownListener() {
                    @Override
                    public boolean trigger() {

                        otherGon.reflect();
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.S, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        MyTextInputListener listener = new MyTextInputListener();
                        Gdx.input.getTextInput(listener, "Type some symmetries!", "", "");
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.N, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        MyTextInputListener1 listener = new MyTextInputListener1();
                        Gdx.input.getTextInput(listener, "Type number of sides.", "", "");
                        return false;
                    }
                });
            }
        });
        bind(WorldBuilder.class).to(NgonBuilder.class);

    }
}
