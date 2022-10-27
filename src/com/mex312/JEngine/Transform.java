package com.mex312.JEngine;

import org.w3c.dom.traversal.TreeWalker;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Transform {
    private Transform parent = null;
    private final Collection<Transform> children = new LinkedHashSet<>();
    private final GameObject gameObject;

    private float z = 0;
    private float rotation = 0;
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2(1, 1);

    public void setGlobalZ(float newZ) {
        if(parent == null) {
            z = newZ;
        } else {
            z = newZ - parent.getGlobalZ();
        }
    }
    public void setLocalZ(float newZ) {
        z = newZ;
    }

    public void setGlobalRotation(float newRotation) {
        if(parent == null) {
            rotation = newRotation;
        } else {
            rotation = newRotation - parent.getGlobalRotation();
        }
    }
    public void setLocalRotation(float newRotation) {
        rotation = newRotation;
    }

    public void setGlobalPosition(Vector2 newPosition) {
        if(parent == null) {
            position = newPosition;
        } else {
            position = newPosition.subtract(parent.getGlobalPosition());
        }
    }
    public void setLocalPosition(Vector2 newPosition) {
        position = newPosition;
    }

    public void setGlobalSize(Vector2 newSize) {
        if(parent == null) {
            size = newSize;
        } else {
            size = newSize.divideEach(parent.getGlobalSize());
        }
    }
    public void setLocalSize(Vector2 newSize) {
        size = newSize;
    }


    public float getGlobalZ() {
        if(parent == null) {
            return z;
        } else {
            return z + parent.getGlobalZ();
        }
    }
    public float getLocalZ() {
        return z;
    }

    public float getGlobalRotation() {
        if(parent == null) {
            return rotation;
        } else {
            return parent.getGlobalRotation() + rotation;
        }
    }
    public float getLocalRotation() {
        return rotation;
    }

    public Vector2 getGlobalPosition() {
        if(parent == null) {
            return position;
        } else {
            return parent.getGlobalPosition().add(position.rotate(parent.getLocalRotation()).multiplyEach(parent.getLocalSize()));
        }
    }
    public Vector2 getLocalPosition() {
        return position;
    }

    public Vector2 getGlobalSize() {
        if(parent == null) {
            return size;
        } else {
            return parent.getGlobalSize().multiplyEach(size);
        }
    }
    public Vector2 getLocalSize() {
        return size;
    }


    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void addChild(Transform transform) {
        if(children.add(transform)) {
            transform.setParent(this);
        }
    }

    public void removeChild(Transform transform) {
        children.remove(transform);
    }

    public Transform getChildFromName(String name) {
        for(Transform child : children) {
            if(child.gameObject.name.equals(name)) {
                return child;
            }
        }

        return null;
    }

    public Transform getChildFromId(long id) {
        for(Transform child : children) {
            if(child.gameObject.id == id) {
                return child;
            }
        }

        return null;
    }

    public Transform getParent() {
        return parent;
    }

    public void setParent(Transform parent) {
        this.parent = parent;
        parent.addChild(this);
    }
}
