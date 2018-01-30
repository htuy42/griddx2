package com.htuy.gridgame.input;


import com.badlogic.gdx.InputProcessor;
import com.htuy.gridgame.geom_tools.Point;

public interface InputProvider extends InputProcessor {

    void registerClickListener(MouseLocListener listener);

    void registerMouseLocListener(MouseLocListener listener);

    Point getCurrentMouseScreenLocation();

    void registerKeyDownListener(int keycode, KeyDownListener listener);


}
