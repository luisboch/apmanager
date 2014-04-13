package com.apmanager.domain.base;

import java.util.List;
import javax.persistence.EntityManager;

public abstract class BasicDAOImpl<E extends BasicEntity , S extends SearchFilter<E> > implements BasicDAO<E,S> {

    private EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(E obj) {
        if (em != null) {
            em.persist(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void update(E obj) {
        if (em != null) {
            em.merge(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void delete(E obj) {
        if (em != null) {
            em.remove(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void save(List<E> objs) {
        if (em != null) {
            for (E e : objs) {
                em.persist(e);
            }
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void update(List<E> objs) {
        
        if (em != null) {
            for (E e : objs) {
                em.merge(e);
            }
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void delete(List<E> objs) {
        
        if (em != null) {
            for (E e : objs) {
                em.remove(e);
            }
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }
}
