package com.htuy.gridgame.renderer.display_renderer;

import com.badlogic.gdx.graphics.Color;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class GradientColorProvider implements ColorProvider {

    private final int range;
    private int maxHeight;
    private final int minHeight;
    private final Color lowColor;
    private final Color highColor;

    @Inject
    public GradientColorProvider(@Named("Max Cell Height") int maxHeight, @Named("Min Cell Height") int minHeight,
                                 @Named("High Color") Color highColor, @Named("Low Color") Color lowColor) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.range = maxHeight - minHeight;
        this.lowColor = lowColor;
        this.highColor = highColor;

    }

    @Override
    public Color apply(Integer height) {
        if (height > maxHeight) {
            return highColor;
        }
        if (height < minHeight) {
            return lowColor;
        }
        float locationInRange = ((float) height - (float) minHeight) / (float) range;
        float r = ((highColor.r - lowColor.r) * locationInRange + lowColor.r);
        float g = ((highColor.g - lowColor.g) * locationInRange + lowColor.g);
        float b = ((highColor.b - lowColor.b) * locationInRange + lowColor.b);
        return new Color(r, g, b, 1);
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
}
