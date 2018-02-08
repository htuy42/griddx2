package com.htuy.gridgame.implementors.orbitz;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import com.htuy.gridgame.cell.CellGenerator;
import com.htuy.gridgame.cell.CopyGenerator;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.DrawCircleEntity;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.physics_entity.Physics;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.input.InputFunctions;
import com.htuy.gridgame.input.InputProvider;
import com.htuy.gridgame.input.KeyDownListener;
import com.htuy.gridgame.input.MouseLocListener;
import com.htuy.gridgame.modules.BasicDisplayModule;
import com.htuy.gridgame.renderer.display_renderer.ColorProvider;
import com.htuy.gridgame.renderer.display_renderer.GradientColorProvider;
import com.htuy.gridgame.world.GridWorld;
import com.htuy.gridgame.world.WorldBuilder;

public class OrbitzModule extends AbstractModule {
    public static Module getInstance() {
        return Modules.override(BasicDisplayModule.getInstance()).with(new OrbitzModule());
    }

    @Override
    protected void configure() {
        bind(WorldBuilder.class).to(OrbitzWorldBuilder.class);
        bind(CellGenerator.class).toInstance(new CopyGenerator(EntityDepthCell.class));
        GradientColorProvider gcp = new GradientColorProvider(20, 0, Color.YELLOW, Color.RED);
        bind(ColorProvider.class).toInstance(gcp);
        gcp.setMaxHeight(20);
        bind(Integer.class).annotatedWith(Names.named("View Width, Tiles")).toInstance(500);
        bind(Integer.class).annotatedWith(Names.named("View Height, Tiles")).toInstance(500);
        Point gravityMouse = new Point(0, 0);
        bind(InputFunctions.class).toInstance(new InputFunctions() {
            @Override
            public void bindInitialInputFunctions(InputProvider inputProvider, GridWorld world) {
                inputProvider.registerClickListener(new MouseLocListener() {
                    @Override
                    public boolean trigger(Point loc) {
                        Entity e = new DrawCircleEntity(new BaseEntity(loc), Color.BLUE, 1.0f);

                        Physics.GRAVITY_CENTERS.add(e);
                        world.getEntityProvider().spawnAtNextTick(e);
                        return false;
                    }

                    @Override
                    public boolean interestedIn(Point loc) {
                        return true;
                    }
                });
//                inputProvider.registerKeyDownListener(Input.Keys.SPACE, new KeyDownListener() {
//                    @Override
//                    public boolean trigger() {
//                        world.getGridProvider().iterCells(new BiConsumer<Point, Cell>() {
//                            @Override
//                            public void accept(Point point, Cell cell) {
//                                if(cell instanceof EntityDepthCell){
//                                    EntityDepthCell edc = (EntityDepthCell) cell;
//                                    edc.heights.clear();
//                                    edc.setHeight(0);
//                                }
//                            }
//                        });
//                        return false;
//                    }
//                });
//                inputProvider.registerKeyDownListener(Input.Keys.A, new KeyDownListener() {
//                    @Override
//                    public boolean trigger() {
//                        final int[] mxHeight = {0};
//                        world.getGridProvider().iterCells(new BiConsumer<Point, Cell>() {
//                            @Override
//                            public void accept(Point point, Cell cell) {
//                                mxHeight[0] = Math.max(mxHeight[0],cell.getHeight());
//                            }
//                        });
//                        gcp.setMaxHeight(mxHeight[0]);
//                        Renderer r = world.getRenderer();
//                        if(r instanceof SimpleHeightmapRenderer){
//                            SimpleHeightmapRenderer shr = (SimpleHeightmapRenderer) r;
//                            shr.clearMap();
//                        }
//                        return false;
//                    }
//                });
                inputProvider.registerKeyDownListener(Input.Keys.P, new KeyDownListener() {
                    @Override
                    public boolean trigger() {
                        if (Physics.getGravity() == 0) {
                            Physics.setGravity(.04f);
                        } else {
                            Physics.setGravity(0.0f);
                        }
                        return false;
                    }
                });
            }
        });
    }
}
