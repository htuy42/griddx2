package com.htuy.gridgame.renderer.textrenderer;

public class SystemOutput implements TextOutput {
    @Override
    public void output(String text) {
        System.out.print(text);
    }

    @Override
    public void outputln(String text) {
        System.out.println(text);
    }
}
