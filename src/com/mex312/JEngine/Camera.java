package com.mex312.JEngine;

import java.util.Collection;
import java.util.logging.Logger;

public abstract class Camera extends Component{
    public Camera(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    abstract void drawAll(Collection<Drawable> components);
}
