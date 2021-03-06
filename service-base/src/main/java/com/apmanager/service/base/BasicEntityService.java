/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicManagerDAO;
import com.apmanager.domain.base.BasicEntity;
import java.util.List;

/**
 *
 * @author luis
 */
public interface BasicEntityService extends BasicService<BasicManagerDAO> {

    <E extends BasicEntity> void save(E entity);

    <E extends BasicEntity> void update(E entity);

    <E extends BasicEntity> void delete(E entity);

    <E extends BasicEntity> void save(List<E> entity);

    <E extends BasicEntity> void update(List<E> entity);

    <E extends BasicEntity> void delete(List<E> entity);

    <E extends BasicEntity> void save(E entity, String context);

    <E extends BasicEntity> void update(E entity, String context);

    <E extends BasicEntity> void delete(E entity, String context);

    <E extends BasicEntity> void save(List<E> entity, String context);

    <E extends BasicEntity> void update(List<E> entity, String context);

    <E extends BasicEntity> void delete(List<E> entity, String context);
    
}
