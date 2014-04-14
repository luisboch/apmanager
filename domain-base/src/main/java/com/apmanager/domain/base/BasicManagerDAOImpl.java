package com.apmanager.domain.base;

import java.util.List;

public class BasicManagerDAOImpl
        extends BasicDAOImpl
        implements BasicManagerDAO {

    @Override
    public <E extends BasicEntity> void save(E obj) {
        if (em != null) {
            em.persist(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public <E extends BasicEntity> void update(E obj) {
        if (em != null) {
            em.merge(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public <E extends BasicEntity> void delete(E obj) {
        if (em != null) {
            em.remove(obj);
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public <E extends BasicEntity> void save(List<E> objs) {
        if (em != null) {
            objs.stream().forEach((e) -> {
                em.persist(e);
            });
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public <E extends BasicEntity> void update(List<E> objs) {

        if (em != null) {
            objs.stream().forEach((e) -> {
                em.merge(e);
            });
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public <E extends BasicEntity> void delete(List<E> objs) {

        if (em != null) {
            objs.stream().forEach((e) -> {
                em.remove(e);
            });
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void beginTransaction() {
        if (em != null) {
            em.getTransaction().begin();
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void commitTransaction() {
        if (em != null) {
            em.getTransaction().commit();
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

    @Override
    public void rollbackTransaction() {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        } else {
            throw new RuntimeException("Entity manager not found");
        }
    }

}
