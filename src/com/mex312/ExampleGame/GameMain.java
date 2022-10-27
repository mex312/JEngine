package com.mex312.ExampleGame;

import com.mex312.JEngine.*;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class GameMain {
    public static class Rectangle extends Drawable {
        private Color color;

        public Rectangle(String name, GameObject gameObject, Color color) {
            super(name, gameObject);
            this.color = color;
        }

        @Override
        public void draw(Graphics2D graphics) {
            Vector2 size = getLocalSize();
            Vector2 pos = getLocalPosition();
            graphics.setColor(color);
            graphics.fillRect((int) (pos.x - size.x/2), (int) (pos.y - size.y/2), (int) size.x, (int) size.y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("game");

        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_W, KeyEvent.VK_S), "WS");
        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_D, KeyEvent.VK_A), "AD");
        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_UP, KeyEvent.VK_DOWN), "Vertical");
        Inputs.registerNewInputAxis(new Inputs.InputAxis(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT), "Horizontal");

        GameObject cameraObj = new GameObject("Camera");
        SwingCamera cameraComp = new SwingCamera("Camera Component", cameraObj);
        cameraObj.transform.setGlobalPosition(new Vector2(0, 0));

        GameObject wall = new GameObject("Wall");
        Drawable square = new Rectangle("Rectangle", wall, Color.WHITE);
        square.setGlobalSize(new Vector2(100, 100));
        GameObject head = new GameObject("Head");
        head.transform.setParent(wall.transform);
        Drawable squareHead = new Rectangle("Rectangle", head, Color.BLUE);
        squareHead.setLocalSize(new Vector2(100, 100));
        head.transform.setLocalPosition(new Vector2(0, 50));
        wall.transform.setGlobalPosition(new Vector2(0, 0));
        wall.transform.setGlobalRotation((float)(Math.PI / 4));
        wall.transform.setLocalSize(new Vector2(3, 1));
        Behavior controller = new Behavior("Player Controller", wall) {
            float time = 0;

            @Override
            public void Update() throws Throwable{
                Vector2 pos = gameObject.transform.getLocalPosition();
                float rot = gameObject.transform.getLocalRotation();
                gameObject.transform.setLocalRotation(rot + (float)(Time.deltaTime() * Math.PI * Inputs.getAxis("Horizontal")));
                gameObject.transform.setLocalPosition(pos.add(new Vector2(0, Inputs.getAxis("WS"))
                        .rotate(gameObject.transform.getLocalRotation()).multiply(400 * Time.deltaTime())));
                GameObject head = gameObject.transform.getChildFromName("Head").gameObject;
                time += Time.deltaTime();
                head.transform.setLocalRotation(time * (float)Math.PI);
            }
        };

        GameObject wall2 = new GameObject("Wall2");
        wall2.transform.setGlobalSize(new Vector2(200, 100));
        wall2.transform.setGlobalPosition(new Vector2(200, 0));
        wall2.transform.setGlobalZ(1);
        Drawable square2 = new Rectangle("Rectangle", wall2, Color.GREEN);

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
