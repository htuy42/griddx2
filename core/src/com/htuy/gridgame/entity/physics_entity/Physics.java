package com.htuy.gridgame.entity.physics_entity;

import com.badlogic.gdx.math.Vector2;
import com.htuy.gridgame.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Physics {

    public static final Vector2 GRAVITY = new Vector2(0, -.05f);
    public static final float FRICTION_COEFFICIENT = 0.001f;
    public static float GRAVITY_SPEED = 0.0f;
    private static final float TERMINAL_VELOCITY = 10.0f;
    public static final float TERMINAL_VELOCITY_SQ = TERMINAL_VELOCITY * TERMINAL_VELOCITY;
    public static List<Entity> GRAVITY_CENTERS = new ArrayList<>();
    public static int FORCE_LAWS = 2;

    public static float getGravity() {
        return GRAVITY_SPEED;
    }

    public static void setGravity(float g) {
        Physics.GRAVITY_SPEED = g;
    }

}
