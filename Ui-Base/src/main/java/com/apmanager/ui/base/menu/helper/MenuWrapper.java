/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.base.menu.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.MenuItem;

/**
 *
 * @author luis
 */
public class MenuWrapper {

    private String name;
    private MenuWrapper parent;
    private MenuItem node;
    private final List<MenuWrapper> children = new MenuArrayList(this);

    private Class targetClass;
    private Integer priority;
    private boolean isDefault;
    
    public MenuWrapper() {
    }

    public List<MenuWrapper> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuWrapper getParent() {
        return parent;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public void setParent(MenuWrapper parent) {
        this.parent = parent;
    }

    public MenuItem getNode() {
        return node;
    }

    public void setNode(MenuItem node) {
        this.node = node;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.parent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuWrapper other = (MenuWrapper) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        return true;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
    
    

    @Override
    public String toString() {
        return "MenuWrapper{" + "name=" + name + '}';
    }

    private static class MenuArrayList extends ArrayList<MenuWrapper> {

        public MenuArrayList(MenuWrapper owner) {
            this.owner = owner;
        }

        MenuWrapper owner;

        @Override
        public boolean add(MenuWrapper e) {

            if (e.getParent() == null || !e.getParent().equals(owner)) {
                e.setParent(owner);
            }

            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends MenuWrapper> c) {
            forEach((n) -> {
                add(n);
            });
            return true;
        }

        @Override
        public boolean remove(Object e) {

            if (e instanceof MenuWrapper) {
                MenuWrapper m = (MenuWrapper) e;
                if (m.getParent() != null && m.getParent().equals(owner)) {
                    m.setParent(null);
                }
            }

            return super.remove(e);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            forEach((n) -> {
                remove(n);
            });
            return true;

        }

    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {

        if (priority == null) {
            priority = 999;
        }

        this.priority = priority;
    }
}
