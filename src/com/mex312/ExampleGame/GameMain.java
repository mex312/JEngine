package com.mex312.ExampleGame;

import com.mex312.JEngine.*;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class GameMain {
    public static class Rectangle extends Drawable {
        private final Color color;

        public Rectangle(String name, GameObject gameObject, Color color) {
            super(name, gameObject);
            this.color = color;
        }

        @Override
        public void draw(Graphics2D graphics) {
            Vector2 size = getGlobalSize();
            Vector2 pos = getGlobalPosition();
            graphics.setColor(color);
            graphics.fillRect((int) (pos.x - size.x/2), (int) (pos.y - size.y/2), (int) size.x, (int) size.y);
        }
    }

    public static class Ellipse extends Drawable {
        private final Color color;

        public Ellipse(String name, GameObject gameObject, Color color) {
            super(name, gameObject);
            this.color = color;
        }

        @Override
        public void draw(Graphics2D graphics) {
            Vector2 size = getGlobalSize();
            Vector2 pos = getGlobalPosition();
            graphics.setColor(color);
            graphics.fillOval((int) (pos.x - size.x/2), (int) (pos.y - size.y/2), (int) size.x, (int) size.y);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("game");

        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_W, KeyEvent.VK_S), "Vertical");
        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_D, KeyEvent.VK_A), "Horizontal");

        GameObject cameraObj = new GameObject("Camera");
        SwingCamera cameraComp = new SwingCamera("Camera Component", cameraObj);
        cameraObj.transform.setGlobalPosition(new Vector2(0, 0));

        GameObject wall = new GameObject("Wall");
        wall.transform.setGlobalSize(new Vector2(100, 100));
        Drawable square = new Rectangle("Square", wall, Color.WHITE);
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
        GameObject head = new GameObject("Head");
        head.transform.setLocalPosition(new Vector2(0, 100));
        Drawable circle = new Ellipse("Circle", head, Color.GRAY);
        head.transform.setParent(wall.transform);

        GameObject wall2 = new GameObject("Wall2");
        wall2.transform.setGlobalSize(new Vector2(200, 100));
        wall2.transform.setGlobalPosition(new Vector2(300, 0));
        wall2.transform.setGlobalZ(1);
        Drawable square2 = new Rectangle("Square", wall2, Color.GREEN);

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
