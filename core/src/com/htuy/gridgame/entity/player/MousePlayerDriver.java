package com.htuy.gridgame.entity.player;

import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.MouseLocListener;
import com.htuy.gridgame.input.Registerable;

public class MousePlayerDriver implements Registerable {

    private final PlayerAvatar avatar;

    public MousePlayerDriver(PlayerAvatar avatar) {
        this.avatar = avatar;
    }


    @Override
    public void register(InputProvider provider) {
        provider.registerMouseLocListener(new MouseLocListener() {
            @Override
            public boolean trigger(Point loc) {
                avatar.setLocation(loc.getX(), loc.getY());
                return false;
            }

            @Override
            public boolean interestedIn(Point loc) {
                return true;
            }
        });
    }
}
