package com.mex312.JEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AWTKeyAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
        Inputs.onKeyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        Inputs.onKeyReleased(e.getKeyCode());
    }
}
