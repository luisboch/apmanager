package com.apmanager.ui.base.crud;

import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;

/**
 * @author luis
 * @param <E>
 */
public interface Crud<E> {

    void addResultColumn(TableColumn column);
    
    void setMode(CrudMode mode);
    
    void setState(CrudState state);
    
}
