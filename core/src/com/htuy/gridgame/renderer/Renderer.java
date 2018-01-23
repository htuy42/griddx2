package com.htuy.gridgame.renderer;

import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.display.View;

public interface Renderer {

    void render(GridProvider provider, Display display);

}
