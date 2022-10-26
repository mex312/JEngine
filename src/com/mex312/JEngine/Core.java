package com.mex312.JEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.logging.Logger;

public class Core {
    private static class CoreThread extends Thread {
        @Override
        public void run() {
            while(true) {
                if(isEngineRunning) {
                    try {
                        Time.update();
                        for(Behavior behavior : behaviors) {
                            behavior.Update();
                        }
                        for(Camera camera : cameras) {
                            ArrayList<Drawable> drawablesAsList = new ArrayList<>(drawables);
                            drawablesAsList.sort((Drawable first, Drawable second) -> (int)(first.getGlobalZ() - second.getGlobalZ()));
                            camera.drawAll(drawablesAsList);
                        }

                        Inputs.updateKeyDown();
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private static final Collection<Camera> cameras = new LinkedHashSet<>();
    private static final Collection<Drawable> drawables = new LinkedHashSet<>();
    private static final Collection<Behavior> behaviors = new LinkedHashSet<>();

    private static final Collection<EObject> objects = new LinkedHashSet<>();
    private static boolean isEngineRunning = false;
    private static final CoreThread coreThread = new CoreThread();

    public static void onNewObjectCreated(EObject object) {
        objects.add(object);
        if(object instanceof Camera) {
            cameras.add((Camera) object);
        }
        if(object instanceof Drawable) {
            drawables.add((Drawable) object);
        }
        if(object instanceof Behavior) {
            behaviors.add((Behavior) object);
        }
    }

    public static void startEngine() {
        coreThread.start();
        Time.initialize();
    }

    public static void enableEngine() {
        isEngineRunning = true;
    }

    public static void disableEngine() {
        isEngineRunning = false;
    }
}
