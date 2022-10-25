package com.mex312.JEngine;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class GameObject extends EObject{
    private final Collection<Component> components = new LinkedHashSet<>();

    public final Transform transform;

    public void addComponent(Component component) {
        components.add(component);
    }
    public void addComponents(Collection<Component> components) {
        this.components.addAll(components);
    }

    public <T extends Component> Component getComponentByType(Class<T> type) {
        for(Component component : components) {
            if(component.getClass().isAssignableFrom(type)) {
                return component;
            }
        }
        return null;
    }
    public <T extends Component> Collection<T> getComponentsByType(Class<T> type) {
        LinkedList<T> out = new LinkedList<>();
        for(Component component : components) {
            if(component.getClass().isAssignableFrom(type)) {
                out.add((T)component);
            }
        }
        return out;
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }
    public void removeComponents(Collection<Component> components) {
        this.components.removeAll(components);
    }

    public GameObject(String name) {
        super(name);
        this.transform = new Transform(this);
    }
    public GameObject(String name, GameObject parent) {
        super(name);
        this.transform = new Transform(this);
        transform.setParent(parent.transform);
    }
}
