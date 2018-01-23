package com.htuy.gridgame.cell;

import java.util.Random;

public class BasicCell implements Cell {

    private static Random random = new Random();

    private int height;

    public BasicCell(int height) {
        this.height = height;
    }

    public BasicCell() {
        this.height = random.nextInt(10);
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return String.valueOf(this.height);
    }
}
