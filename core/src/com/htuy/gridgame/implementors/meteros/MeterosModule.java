package com.htuy.gridgame.implementors.meteros;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.htuy.gridgame.entity.player.MousePlayerDriver;
import com.htuy.gridgame.entity.player.PlayerAvatar;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.world.GridWorld;
import com.htuy.gridgame.world.WorldBuilder;

public class MeterosModule extends AbstractModule {
    public static final float METEOR_FREQUENCY = 3.5f;
    public static final float NUM_METEORS = 15;
    static final float METEOR_SPEED = 10;
    static final float METEOR_SIZE = 4;
    static final float METEOR_DAMAGE = 1;

    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new MeterosModule());

    }

    @Override
    protected void configure() {
        bind(WorldBuilder.class).to(MeterosWorldBuilder.class);

        PlayerAvatar avatar = new PlayerAvatar(new Point(100, 100), 20, 5.2f);

        bind(PlayerAvatar.class).toInstance(avatar);
        bind(InputFunctions.class).toInstance(new InputFunctions() {
            @Override
            public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
                new MousePlayerDriver(avatar).register(inputProvider);
            }
        });
    }

}
