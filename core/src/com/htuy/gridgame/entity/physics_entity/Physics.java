package com.htuy.gridgame.entity.physics_entity;

import com.badlogic.gdx.math.Vector2;
import com.htuy.gridgame.geom_tools.Point;

public class Physics {

    public static final Vector2 GRAVITY = new Vector2(0, -.05f);
    public static final float GRAVITY_SPEED = .003f;
    public static final float FRICTION_COEFFICIENT = .001f;
    private static final float TERMINAL_VELOCITY = 10.0f;
    public static final float TERMINAL_VELOCITY_SQ = TERMINAL_VELOCITY * TERMINAL_VELOCITY;
    public static Point GRAVITY_CENTER = new Point(0, 0);
}
