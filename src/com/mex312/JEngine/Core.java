package com.mex312.JEngine;

import java.awt.*;
import java.util.*;
import java.util.logging.Logger;

public class Core {
    private static class CoreThread extends Thread {
        long time = 0;

        @Override
        public void run() {
            while(true) {
                if(isEngineRunning) {
                    try {
                        Time.update();
                        time += Time.deltaTimeInNanos();
                        for(EObject object : newObjects) {
                            if(object instanceof Behavior) {
                                ((Behavior) object).Start();
                            }
                        }
                        newObjects.clear();
                        if(time >= 20000000) {
                            time %= 20000000;
                            for(Behavior behavior : behaviors) {
                                behavior.FixedUpdate();
                            }
                        }
                        for(Behavior behavior : behaviors) {
                            behavior.Update();
                        }
                        for(Camera camera : cameras) {
                            ArrayList<Drawable> drawablesAsList = new ArrayList<>(drawables);
                            drawablesAsList.sort((Drawable first, Drawable second) -> (int)(first.getGlobalZ() - second.getGlobalZ()));
                            camera.drawAll(drawablesAsList);
                        }

                        Inputs.update();
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

    private static final Collection<Component> components = new LinkedHashSet<>();
    private static final Collection<GameObject> gameObjects = new LinkedHashSet<>();

    private static final Collection<EObject> newObjects = new LinkedHashSet<>();
    private static final Collection<EObject> objects = new LinkedHashSet<>();
    private static boolean isEngineRunning = false;
    private static final CoreThread coreThread = new CoreThread();

    public static void onNewObjectCreated(EObject object) {
        objects.add(object);
        newObjects.add(object);
        if(object instanceof GameObject) {
            gameObjects.add((GameObject) object);
        }
        if(object instanceof Component) {
            components.add((Component) object);
        }
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

    public static GameObject findGameObjectByName(String name) {
        for(GameObject object : gameObjects) {
            if(object.name.equals(name)) {
                return object;
            }
        }

        return null;
    }
    public static Collection<GameObject> findGameObjectsByName(String name) {
        Collection<GameObject> out = new LinkedList<>();

        for(GameObject object : gameObjects) {
            if(object.name.equals(name)) {
                out.add(object);
            }
        }

        return out;
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
