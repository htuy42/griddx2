package com.htuy.gridgame.input;

import com.htuy.gridgame.geom_tools.Point;

public interface MouseLocListener {

    boolean trigger(Point loc);

    boolean interestedIn(Point loc);

}
