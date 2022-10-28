package com.mex312.JEngine;

import java.util.HashMap;
import java.util.LinkedList;

public class Inputs {

    private static final HashMap<Integer, Boolean> keyTable = new HashMap<>();
    private static final HashMap<Integer, Boolean> keyDownTable = new HashMap<>();
    private static final HashMap<String, InputAxis> axisTable = new HashMap<>();
    private static final LinkedList<Integer> keysUp = new LinkedList<>();

    private static Vector2 mousePositionLast = new Vector2(0, 0);
    private static Vector2 mousePositionNow = new Vector2(0, 0);
    private static Vector2 mousePositionOnScreenLast = new Vector2(0, 0);
    private static Vector2 mousePositionOnScreenNow = new Vector2(0, 0);

    public static class InputAxis {
        private final int posKey;
        private final int negKey;

        public InputAxis(int posKey, int negKey) {
            this.negKey = negKey;
            this.posKey = posKey;
        }

        public int get() {
            if(getKey(posKey)) {
                return 1;
            } else if(getKey(negKey)) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static boolean getKey(int keyCode) {
        return keyTable.getOrDefault(keyCode, false);
    }

    public static boolean getKeyDown(int keyCode) {
        return false;
    }

    public static int getAxis(String axisName) throws Exception {
        InputAxis axis = axisTable.getOrDefault(axisName, null);
        if(axis == null) {
            throw new Exception("No input axis with name '" + axisName + "'");
        } else {
            return axis.get();
        }
    }

    public static void onKeyPressed(int keyCode) {
        if(!keyTable.getOrDefault(keyCode, false)) {
            keyDownTable.put(keyCode, true);
        }
        keyTable.put(keyCode, true);
    }

    public static void onKeyReleased(int keyCode) {
        keysUp.add(keyCode);
    }

    public static void onMouseMoved(Vector2 mousePositionOnScreen, Vector2 mousePosition) {
        mousePositionNow = mousePosition;
        mousePositionOnScreenNow = mousePositionOnScreen;
    }

    public static Vector2 getDeltaMousePosition() {
        return mousePositionOnScreenNow.subtract(mousePositionOnScreenLast);
    }

    public static Vector2 getMousePositionOnScreen() {
        return mousePositionOnScreenNow;
    }

    public static Vector2 getMousePositionOnFrame() {
        return mousePositionNow;
    }

    public static void registerNewInputAxis(InputAxis axis, String name) {
        axisTable.put(name, axis);
    }

    public static void update() {
        keyDownTable.replaceAll((c, v) -> false);

        for(int keyCode : keysUp) {
            keyTable.put(keyCode, false);
        }

        keysUp.clear();

        mousePositionLast = mousePositionNow;
        mousePositionOnScreenLast = mousePositionOnScreenNow;
    }
}
