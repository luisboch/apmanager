package com.apmanager.domain.base;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author luis
 * @param <E>
 * @param <S>
 */
public interface BasicDAO<E extends BasicEntity, S extends SearchFilter<E>> {

    void setEntityManager(EntityManager em);

    void save(E obj);

    void update(E obj);

    void delete(E obj);

    void save(List<E> objs);

    void update(List<E> objs);

    void delete(List<E> objs);

    List<E> search(S filter);
    
    <T extends BasicEntity> List<T> find(Class<T> clazz, SearchFilter<? extends T> filter  );

    List<E> getAll();

    List<E> getAll(Class clazz);
    
    void beginTransaction();
    void commitTransaction();
    void rollbackTransaction();
}
