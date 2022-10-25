package com.mex312.JEngine;

import java.util.Collection;
import java.util.logging.Logger;

public class Camera extends Component{
    public Camera(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    protected Collection<Drawable> componentsToDraw = null;

    public void drawAll(Collection<Drawable> components) {
        componentsToDraw = components;
    }
}
