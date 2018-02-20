package com.htuy.gridgame.input;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BasicInputProvider implements InputProvider {
    private final Display display;
    private final List<MouseLocListener> clickListeners;
    private final List<MouseLocListener> moveListeners;
    private final Multimap<Integer, KeyDownListener> keyDownListeners;
    //    private final List<NumberTypedListener> numberTypedListeners;
    private Point mouseGridLoc;

    @Inject
    public BasicInputProvider(Display display) {
        this.mouseGridLoc = new Point(0, 0);
        this.clickListeners = new ArrayList<>();
        this.moveListeners = new ArrayList<>();
        this.display = display;
        keyDownListeners = ArrayListMultimap.create();
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
    public void registerKeyDownListener(int keycode, KeyDownListener listener) {
        keyDownListeners.get(keycode).add(listener);
    }

    @Override
    public boolean keyDown(int keycode) {
        iterateApply(keyDownListeners.get(keycode));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    public void registerNumberTypedListener() {

    }

    @Override
    public boolean keyTyped(char character) {
//        System.out.println(character);
//        iterateApply(keyDownListeners.get(Input.Keys.valueOf(("" + character).toUpperCase())));
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Point realLoc = display.toViewLoc(transformToTopLeft(new Point(screenX, screenY)));
        iterateApply(clickListeners, realLoc);
        return true;
    }

    private Point transformToTopLeft(Point original) {
        return new Point(original.getX(), display.getScreen().getHeight() - original.getY());
    }

    private void iterateApply(Collection<MouseLocListener> listeners, Point p) {
        Iterator<MouseLocListener> i = listeners.iterator();
        while (i.hasNext()) {
            MouseLocListener listener = i.next();
            if (listener.interestedIn(p)) {
                if (listener.trigger(p)) {
                    i.remove();
                }
            }
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Point realLoc = display.toViewLoc(transformToTopLeft(new Point(screenX, screenY)));
        this.mouseGridLoc = realLoc;
        iterateApply(moveListeners, realLoc);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    private void iterateApply(Collection<KeyDownListener> listeners) {
        listeners.removeIf(KeyDownListener::trigger);
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
