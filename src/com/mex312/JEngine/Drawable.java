package com.mex312.JEngine;

import com.sun.istack.internal.NotNull;

import java.awt.*;

public abstract class Drawable extends Component{
    private float z = 0;
    private float rotation = 0;
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2(1, 1);

    public float getGlobalZ() {
        return gameObject.transform.getGlobalZ() + z;
    }
    public float getGlobalRotation() {
        return gameObject.transform.getGlobalRotation() + rotation;
    }
    public Vector2 getGlobalPosition() {
        return gameObject.transform.getGlobalPosition().add(position);
    }
    public Vector2 getGlobalSize() {
        return gameObject.transform.getGlobalSize().multiplyEach(size);
    }

    public float getLocalZ() {
        return z;
    }
    public float getLocalRotation() {
        return rotation;
    }
    public Vector2 getLocalPosition() {
        return position;
    }
    public Vector2 getLocalSize() {
        return size;
    }


    public void setGlobalZ(int newZ) {
        z = newZ - gameObject.transform.getGlobalZ();
    }
    public void setGlobalRotation(int newRotation) {
        rotation = newRotation - gameObject.transform.getGlobalRotation();
    }
    public void setGlobalPosition(Vector2 newPosition) {
        position = newPosition.subtract(gameObject.transform.getGlobalPosition());
    }
    public void setGlobalSize(Vector2 newSize) {
        size = newSize.subtract(gameObject.transform.getGlobalSize());
    }

    public void setLocalZ(int newZ) {
        z = newZ;
    }
    public void setLocalRotation(int newRotation) {
        rotation = newRotation;
    }
    public void setLocalPosition(Vector2 newPosition) {
        position = newPosition;
    }
    public void setLocalSize(Vector2 newSize) {
        size = newSize;
    }


    public Drawable(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    abstract public void draw(@NotNull Graphics2D graphics);
}
