/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicManagerDAO;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;

/**
 *
 * @author luis
 * @param <E>
 * @param <S>
 */
public interface BasicEntityService extends BasicService<BasicManagerDAO> {

    <E extends BasicEntity> void save(E entity);

    <E extends BasicEntity> void update(E entity);

    <E extends BasicEntity> void delete(E entity);

    <E extends BasicEntity> void save(List<E> entity);

    <E extends BasicEntity> void update(List<E> entity);

    <E extends BasicEntity> void delete(List<E> entity);
    
}
