package com.htuy.gridgame.renderer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.htuy.gridgame.FuncTools;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.display.Screen;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.modules.TestTextModule;
import com.htuy.gridgame.renderer.textrenderer.StringAppenderOutput;
import com.htuy.gridgame.renderer.textrenderer.TextRenderer;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.junit.Assert.*;

public class TextRendererTest {

    private GridProvider provider;
    private Renderer renderer;
    private StringAppenderOutput sao;

    @Before
    public void setup(){
        Injector inject = Guice.createInjector(TestTextModule.getInstance());
        provider = inject.getInstance(GridProvider.class);
        sao = new StringAppenderOutput();
        renderer = new TextRenderer(sao);
    }

    @Test
    public void render() throws Exception {
        View view = provider.getFullView();
        Display d = new Display(view,new Screen(100,100));
        renderer.render(provider,d);
        final String[] expected = {""};
        final int[] i = {0};
        FuncTools.rectIter(TestTextModule.VIEW_WIDTH, TestTextModule.VIEW_HEIGHT, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer x, Integer y) {
                expected[0] += String.valueOf(i[0]);
                i[0] += 1;
                if(x == TestTextModule.VIEW_WIDTH - 1){
                    expected[0] += "\n";
                }
            }
        });
        assertEquals(expected[0],sao.getInternalString());
    }

}