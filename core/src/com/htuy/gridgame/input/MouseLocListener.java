package com.htuy.gridgame.input;

import com.htuy.gridgame.geom_tools.Point;

public interface MouseLocListener {

    public boolean trigger(Point loc);

    public boolean interestedIn(Point loc);

}
