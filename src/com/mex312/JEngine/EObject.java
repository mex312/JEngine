package com.mex312.JEngine;

public class EObject {
    private static long nextEmptyId;
    private static long getNewId(){
        nextEmptyId++;
        return nextEmptyId - 1;
    }

    public final long id;
    public final String name;

    public EObject(String name) {
        this.name = name;
        id = getNewId();
        Core.onNewObjectCreated(this);
    }
}
