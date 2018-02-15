package com.htuy.gridgame.implementors.silly_rotations;

import com.badlogic.gdx.Input;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.KeyDownListener;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.world.GridWorld;
import com.htuy.gridgame.world.WorldBuilder;

public class SillyModule extends AbstractModule {
    public static Ngon gon = new Ngon(Point.randomPoint(new View(1000, 1000)), 3);
    private static int qdDir = 0;

    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new SillyModule());
    }

    @Override
    protected void configure() {

        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(500);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(500);
        Point gravityMouse = new Point(0, 0);

        bind(InputFunctions.class).toInstance(new InputFunctions() {
            @Override
            public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
                inputProvider.registerKeyDownListener(Input.Keys.R, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        gon.rotate(-1);
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.L, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        gon.rotate(1);
                        return false;
                    }
                });
                inputProvider.registerKeyDownListener(Input.Keys.M, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        gon.reflect();
//                        List<Transform> todo = ImmutableList.of(
//                                new Transform.ReflectTransform(),
//                                new Transform.RotateTransform(1));
//                        List<Transform> equivalence = gon.collapseSequence(todo);
//                        for(Transform t : equivalence){
//                            System.out.println(t.toString());
//                        }
//                        System.out.println("Is the same as");
//                        for(Transform t : todo){
//                            System.out.println(t.toString());
//                        }
                        return false;
                    }
                });
            }
        });
        bind(WorldBuilder.class).to(NgonBuilder.class);

    }
}
