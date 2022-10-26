package com.mex312.ExampleGame;

import com.mex312.JEngine.*;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class GameMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("game");

        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_W, KeyEvent.VK_S), "Vertical");
        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_D, KeyEvent.VK_A), "Horizontal");

        GameObject cameraObj = new GameObject("Camera");
        SwingCamera cameraComp = new SwingCamera("Camera Component", cameraObj);
        cameraObj.transform.setGlobalPosition(new Vector2(0, 0));

        GameObject wall = new GameObject("Wall");
        wall.transform.setGlobalSize(new Vector2(100, 100));
        Drawable square = new Drawable("Square", wall) {
            @Override
            public void draw(@NotNull Graphics2D graphics) {
                Vector2 size = getGlobalSize();
                Vector2 pos = getGlobalPosition();
                graphics.setColor(Color.WHITE);
                graphics.fillRect((int) (pos.x - size.x/2), (int) (pos.y - size.y/2), (int) size.x, (int) size.y);
            }
        };
        wall.transform.setGlobalPosition(new Vector2(0, 0));
        Behavior controller = new Behavior("Player Controller", wall) {
            @Override
            public void Update() throws Throwable{
                Vector2 pos = gameObject.transform.getLocalPosition();
                gameObject.transform.setLocalPosition(pos.add(new Vector2(
                        Inputs.getAxis("Horizontal"),
                        Inputs.getAxis("Vertical")
                ).normalized().multiply(400 * Time.deltaTime())));
            }
        };

        GameObject wall2 = new GameObject("Wall2");
        wall2.transform.setGlobalSize(new Vector2(200, 100));
        wall2.transform.setGlobalPosition(new Vector2(300, 0));
        wall2.transform.setGlobalZ(1);
        Drawable square2 = new Drawable("Square", wall2) {
            @Override
            public void draw(@NotNull Graphics2D graphics) {
                Vector2 size = getGlobalSize();
                Vector2 pos = getGlobalPosition();
                graphics.setColor(Color.GREEN);
                graphics.fillRect((int) (pos.x - size.x/2), (int) (pos.y - size.y/2), (int) size.x, (int) size.y);
            }
        };

        frame.addKeyListener(new AWTKeyAdapter());
        frame.setContentPane(cameraComp.cameraPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cameraComp.cameraPanel.setPreferredSize(new Dimension(800, 600));
        frame.pack();

        frame.setVisible(true);
        Core.enableEngine();
        Core.startEngine();
    }
}
