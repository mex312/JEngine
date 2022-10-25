package com.mex312.JEngine;

import java.awt.*;

public abstract class Drawable extends Component{
    private Vector2 position = new Vector2();
    private Vector2 rotation = new Vector2();
    private Vector2 size = new Vector2(1, 1);

    public Vector2 getGlobalPosition() {
        return gameObject.transform.getGlobalPosition().add(position);
    }
    public Vector2 getGlobalRotation() {
        return gameObject.transform.getGlobalRotation().add(rotation);
    }
    public Vector2 getGlobalSize() {
        return gameObject.transform.getGlobalSize().multiplyEach(size);
    }

    public Vector2 getLocalPosition() {
        return position;
    }
    public Vector2 getLocalRotation() {
        return rotation;
    }
    public Vector2 getLocalSize() {
        return size;
    }

    public Drawable(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    abstract public void draw(Graphics2D graphics);
}
