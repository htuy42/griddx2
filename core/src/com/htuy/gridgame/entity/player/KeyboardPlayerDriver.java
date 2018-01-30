package com.htuy.gridgame.entity.player;

import com.badlogic.gdx.Input;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.KeyDownListener;
import com.htuy.gridgame.input.Registerable;

public class KeyboardPlayerDriver implements Registerable {

    private final PlayerAvatar avatar;

    public KeyboardPlayerDriver(PlayerAvatar avatar) {
        this.avatar = avatar;
    }

    public void register(InputProvider inputProvider) {
        int[] keys = {Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D};
        int[] dirs = {0, 1, -1, 0, 0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            inputProvider.registerKeyDownListener(keys[i], new KeyDownListener() {
                @Override
                public boolean trigger() {
                    avatar.move(dirs[finalI * 2], dirs[finalI * 2 + 1]);
                    return false;
                }
            });
        }
    }

}
