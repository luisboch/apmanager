package com.apmanager.domain.base;

import java.util.List;

/**
 * @author luis
 * @param <E>
 */
public interface BasicManagerDAO extends BasicDAO {

    <E extends BasicEntity> void save(E obj);

    <E extends BasicEntity> void update(E obj);

    <E extends BasicEntity> void delete(E obj);

    <E extends BasicEntity> void save(List<E> objs);

    <E extends BasicEntity> void update(List<E> objs);

    <E extends BasicEntity> void delete(List<E> objs);

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

}
