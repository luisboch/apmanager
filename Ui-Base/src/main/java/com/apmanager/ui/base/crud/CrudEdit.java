package com.apmanager.ui.base.crud;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author luis
 * @param <E>
 */
public abstract class CrudEdit<E> extends AnchorPane{
    public abstract E buildObject(E obj, boolean newInstance);
    public abstract boolean isValid(E obj, boolean newInstance);
    public abstract void load(E obj);
}
