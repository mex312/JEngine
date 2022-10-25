package com.mex312.ExampleGame;

import com.mex312.JEngine.*;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class GameMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("game");

        GameObject cameraObj = new GameObject("Camera");
        SwingCamera cameraComp = new SwingCamera("Camera Component", cameraObj);

        GameObject wall = new GameObject("Wall");
        wall.transform.setGlobalSize(new Vector2(100, 100));
        Drawable square = new Drawable("Square", wall) {
            @Override
            public void draw(Graphics2D graphics) {
                Vector2 size = getGlobalSize();
                Vector2 pos = getGlobalPosition();
                graphics.drawRect((int) pos.x, (int) pos.y, (int) size.x, (int) size.y);
            }
        };

        frame.setContentPane(cameraComp.cameraPanel);
        frame.setSize(800, 600);

        Core.enableEngine();
        Core.startEngine();
        frame.setVisible(true);
    }
}
