package com.mex312.JEngine;

public class RigidBody extends Behavior{
    public RigidBody(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    public Vector2 gravity = new Vector2(0, -1);
    public Vector2 speed = new Vector2();
    public float drag = 1;
    public float angularSpeed = 0;
    public float angularDrag = 0.1f;

    @Override
    public void Update() throws Throwable {
        gameObject.transform.setLocalPosition(gameObject.transform.getLocalPosition().add(speed));
        gameObject.transform.setLocalRotation(gameObject.transform.getLocalRotation() + angularSpeed);
        speed = speed.subtract(speed.multiply(drag * Time.deltaTime())).add(gravity.multiply(Time.deltaTime()));
        angularSpeed -= angularSpeed * angularDrag * Time.deltaTime();
    }
}
