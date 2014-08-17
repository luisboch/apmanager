/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author luis
 * @param <E>
 */
public interface BasicEntity<E extends Number> extends Serializable, Comparable<BasicEntity> {

    E getId();

    void setId(E id);

    void setLastUpdate(Date date);

    void setCreationDate(Date date);

    String getDisplayName();

    @Override
    public default int compareTo(BasicEntity o) {
        
        if(o == null){
            return 1;
        }
        
        return o.getDisplayName().compareTo(this.getDisplayName());
    }
}
