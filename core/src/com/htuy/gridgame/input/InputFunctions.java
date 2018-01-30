package com.htuy.gridgame.input;

import com.htuy.gridgame.world.GridWorld;

public interface InputFunctions {

    default void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {

    }
}
