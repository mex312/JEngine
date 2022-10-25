package com.mex312.JEngine;

public class Component extends EObject{
    public final GameObject gameObject;

    public Component (String name, GameObject gameObject) {
        super(name);
        this.gameObject = gameObject;
    }
}
