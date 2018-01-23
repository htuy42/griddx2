package com.htuy.gridgame.renderer.textrenderer;

public class StringAppenderOutput implements TextOutput {

    private String currentText = "";

    @Override
    public void output(String text) {
        currentText += text;
    }

    @Override
    public void outputln(String text) {
        currentText += "\n";
    }

    public String getInternalString() {
        return currentText;
    }
}
