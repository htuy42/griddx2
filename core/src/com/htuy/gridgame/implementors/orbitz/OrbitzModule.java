package com.htuy.gridgame.implementors.orbitz;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.htuy.gridgame.entity.physics_entity.Physics;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.MouseLocListener;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.world.GridWorld;
import com.htuy.gridgame.world.WorldBuilder;

public class OrbitzModule extends AbstractModule {
    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new OrbitzModule());
    }

    @Override
    protected void configure() {
        bind(WorldBuilder.class).to(OrbitzWorldBuilder.class);


        bind(InputFunctions.class).toInstance(new InputFunctions() {
            @Override
            public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
                inputProvider.registerMouseLocListener(new MouseLocListener() {
                    @Override
                    public boolean trigger(Point loc) {
                        Physics.GRAVITY_CENTER = loc;
                        return false;
                    }

                    @Override
                    public boolean interestedIn(Point loc) {
                        return true;
                    }
                });
            }
        });
    }
}
