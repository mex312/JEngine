package com.mex312.JEngine;

import java.time.Instant;
import java.util.Date;

public class Time {
    private static long lastSeconds;
    private static long lastNanos;
    private static float deltaTime;

    public static float deltaTime() {
        return deltaTime;
    }

    public static long deltaTimeInNanos() {
        return (long)((double)deltaTime * 1000000000.0);
    }

    public static void initialize() {
        lastSeconds = Instant.now().getEpochSecond();
        lastNanos = Instant.now().getNano();
    }

    public static void update() {
        long nowSeconds = Instant.now().getEpochSecond();
        long nowNanos = Instant.now().getNano();

        long deltaSeconds = nowSeconds - lastSeconds;
        long deltaNanos = nowNanos - lastNanos;

        lastSeconds = nowSeconds;
        lastNanos = nowNanos;

        if(deltaNanos < 0) {
            deltaNanos += 1000000000;
            deltaSeconds--;
        }

        deltaTime = (float)((double)deltaSeconds + (double)deltaNanos / 1000000000.0);
    }
}
