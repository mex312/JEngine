package com.mex312.JEngine;

public class Vector2 {
    public final float x;
    public final float y;


    public Vector2() {
        x = 0;
        y = 0;
    }
    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getLength() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public Vector2 normalize() {
        float length = getLength();
        if(length != 0) {
            return new Vector2(x / length, y / length);
        } else {
            return new Vector2();
        }
    }

    @Override
    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

    public Vector2 add(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }
    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 subtract(float x, float y) {
        return new Vector2(this.x - x, this.y - y);
    }
    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 multiplyEach(float x, float y) {
        return new Vector2(this.x * x, this.y * y);
    }
    public Vector2 multiplyEach(Vector2 other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }

    public Vector2 divideEach(float x, float y) {
        return new Vector2(this.x / x, this.y / y);
    }
    public Vector2 divideEach(Vector2 other) {
        return new Vector2(this.x / other.x, this.y / other.y);
    }

    public Vector2 multiply(float n) {
        return new Vector2(this.x * n, this.y * n);
    }

    public Vector2 divide(float n) {
        return new Vector2(this.x / n, this.y / n);
    }

    public Vector2 rotate(float angle) {
        double cos = Math.cos(-angle);
        double sin = Math.sin(-angle);
        return new Vector2(
                (float)(x * cos - y * sin),
                (float)(x * sin + y * cos)
        );
    }

    public String toString() {
        return String.format("[%f, %f]", x, y);
    }
}
