package com.mex312.JEngine;

public class RigidBody extends Behavior{
    public RigidBody(String name, GameObject gameObject) {
        super(name, gameObject);
    }

    public Vector2 gravity = new Vector2(0, -500);
    public Vector2 speed = new Vector2();
    public float drag = 0.1f;
    public float angularSpeed = 0f;
    public float angularDrag = 0.1f;

    @Override
    public void Update() {

    }

    @Override
    public void FixedUpdate() {
        speed = speed.subtract(speed.multiply(drag * 0.02f)).add(gravity.multiply(0.02f));
        angularSpeed -= angularSpeed * angularDrag * 0.02f;
        gameObject.transform.setLocalPosition(gameObject.transform.getLocalPosition().add(speed.multiply(0.02f)));
        gameObject.transform.setLocalRotation(gameObject.transform.getLocalRotation() + angularSpeed * 0.02f);
    }
}
