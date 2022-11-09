package com.mex312.JEngine;

public abstract class Behavior extends Component{
    public Behavior(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    public void Start() throws  Throwable {}

    public void Update() throws Throwable {}

    public void FixedUpdate() throws Throwable {}
}
