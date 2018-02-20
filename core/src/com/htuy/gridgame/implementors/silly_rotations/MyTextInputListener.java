package com.htuy.gridgame.implementors.silly_rotations;

import com.badlogic.gdx.Input;
import com.htuy.gridgame.geom_tools.Point;
import javafx.util.Pair;

import java.util.List;

public class MyTextInputListener implements Input.TextInputListener {
    @Override
    public void input(String text) {
        SillyModule.provider.kill(SillyModule.otherGon);
        SillyModule.otherGon = new Ngon(new Point(0, 0), SillyModule.gon.sides, "", false);
        SillyModule.otherGon.colors = SillyModule.gon.colors;
        Pair<Integer, List<Transform>> pair = Parser.parse(text);
        StringBuilder sb = new StringBuilder();
        System.out.print(pair.getKey());
        System.out.print(pair.getValue());
        sb.append("The sequence was: \n" + text);
        sb.append("\n\nThe collapsed equivalent is:\n");
        for (Transform t : Ngon.collapseSequence(pair.getValue(), SillyModule.gon.sides)) {
            sb.append(t.toString());
            sb.append("\n");
            t.perform(SillyModule.otherGon);
        }
        SillyModule.gon.descr = sb.toString();

        SillyModule.provider.spawnAtNextTick(SillyModule.otherGon);
    }
    @Override
    public void canceled() {
    }
}