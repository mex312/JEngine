package com.mex312.JEngine;

import java.util.Collection;
import java.util.HashSet;

public class GameObject extends EObject{
    private static long nextEmptyId;

    private static long getNewId(){
        nextEmptyId++;
        return nextEmptyId - 1;
    }



    public final long id;
    public final Transform transform;

    public GameObject(String name) {
        super(name);
        this.id = getNewId();
        this.transform = new Transform(this);
    }
    public GameObject(String name, GameObject parent) {
        super(name);
        this.id = getNewId();
        this.transform = new Transform(this);
        transform.setParent(parent.transform);
    }
}
