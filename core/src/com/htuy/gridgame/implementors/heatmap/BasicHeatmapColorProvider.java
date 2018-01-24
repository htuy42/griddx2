package com.htuy.gridgame.implementors.heatmap;

import com.badlogic.gdx.graphics.Color;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;

public class BasicHeatmapColorProvider implements ColorProvider {

    private final int range;
    private final int maxHeight;
    private final int minHeight;

    @Inject
    public BasicHeatmapColorProvider(@Named("Max Cell Height") int maxHeight, @Named("Min Cell Height") int minHeight) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.range = maxHeight - minHeight;

    }

    @Override
    public Color apply(Integer height) {
        if (height > maxHeight) {
            return new Color(248.0f / 255, 40.0f / 255, 20.0f / 255, 1);
        }
        if (height < minHeight) {
            return new Color(241.0f / 255, 230.0f / 255, 10.0f / 255, 1);
        }
        float locationInRange = ((float) height - (float) minHeight) / (float) range;
        float r = (int) (7 * locationInRange + 241);
        float g = (int) (-190 * locationInRange + 230);
        float b = (int) (10 * locationInRange + 10);
        return new Color(r / 255, g / 255, b / 255, 1);
    }

}
