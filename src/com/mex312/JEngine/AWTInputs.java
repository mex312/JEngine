package com.mex312.JEngine;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AWTInputs {
    public static class KeyAdapter extends java.awt.event.KeyAdapter {
        public void keyPressed(KeyEvent e) {
            Inputs.onKeyPressed(e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            Inputs.onKeyReleased(e.getKeyCode());
        }
    }

    public static class MouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
            Vector2 windowSize = new Vector2(e.getComponent().getWidth(), e.getComponent().getHeight());
            Vector2 mousePosOnScreen = new Vector2(e.getXOnScreen(), e.getYOnScreen());
            Vector2 mousePos = new Vector2(e.getX(), e.getY());
            Inputs.onMouseMoved(
                    mousePosOnScreen
                            .divideEach(new Vector2(
                                    Toolkit.getDefaultToolkit().getScreenSize().width,
                                    Toolkit.getDefaultToolkit().getScreenSize().height
                            ))
                            .multiplyEach(2, -2)
                            .subtract(1, 1),
                    mousePos
                            .divideEach(windowSize)
                            .multiplyEach(2, -2)
                            .subtract(1, 1)
            );
        }
    }
}
