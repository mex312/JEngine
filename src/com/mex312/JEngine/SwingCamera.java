package com.mex312.JEngine;


import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
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

    @Override
    public Vector2 fromScreenToWorld(Vector2 positionOnScreen) {
        return positionOnScreen
                .add(1, 1)
                .divideEach(2, -2)
                .multiplyEach(
                        Toolkit.getDefaultToolkit().getScreenSize().width,
                        Toolkit.getDefaultToolkit().getScreenSize().height
                )
                .subtract(
                        cameraPanel.getParent().getLocationOnScreen().x,
                        cameraPanel.getParent().getLocationOnScreen().y
                )
                .divideEach(
                        cameraPanel.getWidth(),
                        cameraPanel.getHeight()
                )
                .multiplyEach(2, -2)
                .subtract(1, -1)
                .multiplyEach(
                        cameraPanel.getWidth(),
                        cameraPanel.getHeight()
                )
                .divideEach(2, 2)
                .multiply(gameObject.transform.getGlobalTransformMatrix())
                ;
    }

    @Override
    public Vector2 fromFrameToWorld(Vector2 positionOnFrame) {
        return null;
    }

    protected float[] toAWTFlatMatrix(Matrix matrix) {
        return new float[]{
                matrix.matrixArray[0][0],
                matrix.matrixArray[1][0],
                matrix.matrixArray[0][1],
                matrix.matrixArray[1][1],
                matrix.matrixArray[0][2],
                matrix.matrixArray[1][2],
        };
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
            Matrix transformMatrix =gameObject.transform.getGlobalTransformMatrix();
            g2.setColor(Color.BLACK);
            g2.fillRect(-cameraPanel.getWidth()/2, -cameraPanel.getHeight()/2, cameraPanel.getWidth(), cameraPanel.getHeight());
            g2.transform(new AffineTransform(toAWTFlatMatrix(transformMatrix.inverse())));
            for(Drawable component : componentsToDraw) {
                /*g2.translate(component.gameObject.transform.getGlobalPosition().x, component.gameObject.transform.getGlobalPosition().y);
                g2.rotate(-component.gameObject.transform.getGlobalRotation());
                g2.scale(component.gameObject.transform.getGlobalSize().x, component.gameObject.transform.getGlobalSize().y);
                component.draw(g2);
                g2.scale(1.0 / component.gameObject.transform.getGlobalSize().x, 1.0 / component.gameObject.transform.getGlobalSize().y);
                g2.rotate(component.gameObject.transform.getGlobalRotation());
                g2.translate(-component.gameObject.transform.getGlobalPosition().x, -component.gameObject.transform.getGlobalPosition().y);*/

                Matrix matrix = component.gameObject.transform.getGlobalTransformMatrix();

                g2.transform(new AffineTransform(toAWTFlatMatrix(matrix)));
                component.draw(g2);
                g2.transform(new AffineTransform(toAWTFlatMatrix(matrix.inverse())));
            }
            g2.transform(new AffineTransform(toAWTFlatMatrix(transformMatrix)));
        }
    }
}
