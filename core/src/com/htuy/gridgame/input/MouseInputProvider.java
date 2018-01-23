package com.htuy.gridgame.input;


import com.badlogic.gdx.InputProcessor;
import com.htuy.gridgame.geom_tools.Point;

import java.util.List;

public interface MouseInputProvider extends InputProcessor{
    
    public void registerClickListener(MouseLocListener listener);
    
    public void registerMouseLocListener(MouseLocListener listener);

    public Point getCurrentMouseScreenLocation();
    
    
}
