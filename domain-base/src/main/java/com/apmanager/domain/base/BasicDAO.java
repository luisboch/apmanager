package com.apmanager.domain.base;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author luis
 * @param <E>
 */
public interface BasicDAO<E extends BasicEntity>{
    
    void setEntityManager(EntityManager em);
    
    void save(E obj);
    void update(E obj);
    void delete(E obj);
    
    void save(List<E> objs);
    void update(List<E> objs);
    void delete(List<E> objs);
    
    <F extends SearchFilter<E>> List<E> search(F filter);
    
}
