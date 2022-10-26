package com.mex312.JEngine;

import java.util.HashMap;

public class Inputs {

    private static final HashMap<Integer, Boolean> keyTable = new HashMap<>();
    private static final HashMap<Integer, Boolean> keyDownTable = new HashMap<>();
    private static final HashMap<String, InputAxis> axisTable = new HashMap<>();

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
        keyTable.put(keyCode, false);
    }

    public static void registerNewInputAxis(InputAxis axis, String name) {
        axisTable.put(name, axis);
    }

    public static void updateKeyDown() {
        keyDownTable.replaceAll((c, v) -> false);
    }
}
