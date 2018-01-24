package com.htuy.gridgame.input;

import com.htuy.gridgame.geom_tools.Point;

interface MouseLocListener {

    boolean trigger(Point loc);

    boolean interestedIn(Point loc);

}
