package com.apmanager.ui.base.crud;

import com.apmanager.ui.base.pane.BasicAnchorPane;

/**
 *
 * @author luis
 * @param <E>
 */
public abstract class CrudEdit<E> extends BasicAnchorPane{

    public CrudEdit(String fxml) {
        super(fxml);
    }
    
    public abstract E createNewInstance();
    public abstract E buildObject(E obj, boolean newInstance);
    public abstract boolean isValid(E obj, boolean newInstance);
    public abstract void load(E obj);
}
