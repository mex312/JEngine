package com.mex312.JEngine;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        x = 0;
        y = 0;
    }
    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
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
}
