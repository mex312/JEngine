package com.mex312.JEngine;

public abstract class Behavior extends Component{
    public Behavior(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    public abstract void Update() throws Throwable;
}
