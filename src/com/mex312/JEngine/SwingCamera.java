package com.mex312.JEngine;


import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.logging.Logger;

public class SwingCamera extends Camera {
    public final JPanel cameraPanel;

    public SwingCamera(String name, GameObject gameObject) {
        super(name, gameObject);
        cameraPanel = new JPanel();
    }

    @Override
    public void drawAll(Collection<Drawable> components) {
        super.drawAll(components);
    }

    private class SwingCameraComponent extends JPanel {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            for(Drawable component : componentsToDraw) {
                component.draw(g2);
            }

            Logger.getGlobal().info("//SWING: drawing square");

            //g2.translate(gameObject.transform.getGlobalPosition().x, gameObject.transform.getGlobalPosition().y);
        }
    }
}
