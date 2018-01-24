package com.htuy.gridgame.input;

import com.google.inject.Inject;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicInputProvider implements MouseInputProvider {
    private final Display display;
    private final List<MouseLocListener> clickListeners;
    private final List<MouseLocListener> moveListeners;
    private Point mouseGridLoc;

    @Inject
    public BasicInputProvider(Display display) {
        this.mouseGridLoc = new Point(0, 0);
        this.clickListeners = new ArrayList<>();
        this.moveListeners = new ArrayList<>();
        this.display = display;
    }

    @Override
    public void registerClickListener(MouseLocListener listener) {
        this.clickListeners.add(listener);
    }

    @Override
    public void registerMouseLocListener(MouseLocListener listener) {
        this.moveListeners.add(listener);
    }

    @Override
    public Point getCurrentMouseScreenLocation() {
        return mouseGridLoc;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Point realLoc = transformToTopLeft(new Point(screenX, screenY));
        iterateApply(clickListeners, realLoc);
        return true;
    }

    private Point transformToTopLeft(Point original) {
        return new Point(original.getX(), display.getScreen().getHeight() - original.getY());
    }

    private void iterateApply(List<MouseLocListener> listeners, Point p) {
        Iterator<MouseLocListener> i = listeners.iterator();
        while (i.hasNext()) {
            MouseLocListener listener = i.next();
            if (listener.interestedIn(p)) {
                if (!listener.trigger(p)) {
                    i.remove();
                }
            }
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Point realLoc = transformToTopLeft(new Point(screenX, screenY));
        this.mouseGridLoc = display.toViewLoc(realLoc);
        iterateApply(moveListeners, realLoc);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
