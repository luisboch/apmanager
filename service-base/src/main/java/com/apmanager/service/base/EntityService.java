/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;

/**
 *
 * @author luis
 * @param <E>
 * @param <S>
 */
public interface EntityService<E extends BasicEntity, S extends SearchFilter<E>> extends BasicService<E, S> {

    void save(E entity);

    void update(E entity);

    void delete(E entity);

    void save(List<E> entity);

    void update(List<E> entity);

    void delete(List<E> entity);

}
