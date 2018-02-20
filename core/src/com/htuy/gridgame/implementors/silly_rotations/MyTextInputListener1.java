package com.htuy.gridgame.implementors.silly_rotations;

import com.badlogic.gdx.Input;
import com.htuy.gridgame.geom_tools.Point;

public class MyTextInputListener1 implements Input.TextInputListener {
    @Override
    public void input(String text) {
        int numSides = Integer.parseInt(text);
        SillyModule.provider.kill(SillyModule.gon);
        SillyModule.provider.kill(SillyModule.otherGon);
        SillyModule.otherGon = new Ngon(new Point(0, 0), numSides, "", false);
        SillyModule.gon = new Ngon(new Point(0, 0), numSides, "Press s to input a sequence", true);
        SillyModule.otherGon.colors = SillyModule.gon.colors;
        SillyModule.provider.spawnAtNextTick(SillyModule.gon);
        SillyModule.provider.spawnAtNextTick(SillyModule.otherGon);
    }

    @Override
    public void canceled() {
    }
}

