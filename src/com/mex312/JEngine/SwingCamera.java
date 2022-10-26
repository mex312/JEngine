package com.mex312.JEngine;


import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class SwingCamera extends Camera {
    public final JPanel cameraPanel;

    private Collection<Drawable> componentsToDraw = new ArrayList<>();

    public SwingCamera(String name, GameObject gameObject) {
        super(name, gameObject);
        cameraPanel = new SwingCameraPanel();
    }

    @Override
    public void drawAll(Collection<Drawable> components) {
        componentsToDraw = components;

        BufferedImage screen = new BufferedImage(cameraPanel.getWidth(), cameraPanel.getHeight(), BufferedImage.TYPE_INT_RGB);

        cameraPanel.paint(screen.getGraphics());

        cameraPanel.getGraphics().drawImage(screen, 0, 0, cameraPanel);
    }

    public class SwingCameraPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            if(g2 == null) {
                Logger.getGlobal().warning("Got empty graphics!");
                return;
            }

            AffineTransform transform = g2.getTransform();
            transform.setToScale(1, -1);
            transform.translate( -gameObject.transform.getGlobalPosition().x + (float)cameraPanel.getSize().getWidth()/2, -gameObject.transform.getGlobalPosition().y - (float)cameraPanel.getSize().getHeight()/2);
            g2.setTransform(transform);
            g2.setColor(Color.BLACK);
            g2.fillRect(-cameraPanel.getWidth()/2, -cameraPanel.getHeight()/2, cameraPanel.getWidth(), cameraPanel.getHeight());
            for(Drawable component : componentsToDraw) {
                component.draw(g2);
            }
        }
    }
}
