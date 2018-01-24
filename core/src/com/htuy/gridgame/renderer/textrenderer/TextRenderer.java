package com.htuy.gridgame.renderer.textrenderer;

import com.google.inject.Inject;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.gridprovider.IterTools;
import com.htuy.gridgame.renderer.Renderer;

import java.util.List;

public class TextRenderer implements Renderer {

    private final boolean usePadding;
    private String paddingString;
    private TextOutput output;

    @Inject
    public TextRenderer(TextOutput output) {
        this.usePadding = false;
        this.output = output;
    }

    public TextRenderer(boolean usePadding, String paddingString) {
        this.usePadding = usePadding;
        this.paddingString = paddingString;
    }

    @Override
    public void render(GridProvider provider, Display display) {
        FuncTools.wrap(this::printPadding, () -> IterTools.iterRows(provider, display.getView(), this::outputRow));
    }


    private void printPadding() {
        if (this.usePadding) {
            output.outputln(paddingString);
        }
    }

    private void outputRow(List<Cell> cells) {
        for (Cell c : cells) {
            output.output(c.toString());
        }
        output.outputln("");
    }
}
