package com.apmanager.ui.base;

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

    void addEditField(String label, Node input);

    void addSearchField(String label, Node input);

    List<E> search(Map<String, Object> params);
    
    void setMode(CrudMode mode);
    
    void setState(CrudState state);
    
}
