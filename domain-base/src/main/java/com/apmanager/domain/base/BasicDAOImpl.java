package com.apmanager.domain.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BasicDAOImpl<E extends BasicEntity, S extends SearchFilter<E>> implements BasicDAO<E, S> {

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

    @Override
    public List<E> getAll() {
        throw new UnsupportedOperationException("Not supported yet. Use #getAll(Class) "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> getAll(Class clazz) {
        return em.createNamedQuery("selec x from " + clazz.getCanonicalName() + " x ").getResultList();
    }

    @Override
    public List<E> search(S filter) {
        throw new UnsupportedOperationException("Not supported yet. . Use #search(Class, SearchFilter) "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends BasicEntity> List<T> find(Class<T> clazz, SearchFilter<? extends T> filter) {

        final StringBuilder jpql = new StringBuilder("select x " + clazz.getSimpleName() + " x ");

        final WhereBuilder where = getWhere(filter.getParams());

        jpql.append(where.buildWhere());

        if (filter.getOrder() != null && filter.getOrder().length > 0) {
            jpql.append("order by ");
            int i = 0;
            for (final String order : filter.getOrder()) {
                if (i != 0) {
                    jpql.append(", ");
                }
                jpql.append("x.").append(order);
            }

            jpql.append("x.").append("\n");
        }

        Query q = em.createQuery(jpql.toString());

        if (where.resultParams != null && !where.resultParams.isEmpty()) {
            where.resultParams.keySet().stream().forEach((p) -> {
                q.setParameter(p, where.resultParams.get(p));
            });
        }

        if (filter.getFirstResult() != null) {
            q.setFirstResult(filter.getFirstResult().intValue());
        }

        if (filter.getMaxResults() != null) {
            q.setMaxResults(filter.getMaxResults().intValue());
        }
        
        return q.getResultList();
    }

    protected WhereBuilder getWhere(Map<String, Object> filter) {
        return new WhereBuilder(filter);
    }

    protected class WhereBuilder {

        protected StringBuilder where;
        protected Map<String, Object> resultParams;
        protected Map<String, Object> queryParams;

        public WhereBuilder(Map<String, Object> queryParams) {

            this.queryParams = queryParams;

        }

        public void putParam(String paramName, Object expected) {

            if (queryParams == null) {
                queryParams = new HashMap<>();
            }

            queryParams.put(paramName, expected);
        }

        public void putParam(Map<String, Object> param) {

            if (queryParams == null) {
                queryParams = new HashMap<>();
            }

            queryParams.putAll(param);
        }

        public String buildWhere() {

            resultParams = new HashMap<>();

            if (queryParams != null && !queryParams.isEmpty()) {

                where = new StringBuilder("where ");

                int i = 0;

                for (String k : queryParams.keySet()) {

                    if (i > 0) {
                        where.append("and ");
                    }

                    final String paramName = ":param" + i;

                    where.append("x.").append(k).append(paramName).append("\n");

                    resultParams.put(paramName, queryParams.get(k));

                    i++;

                }
            }

            return where == null ? "" : where.toString();
        }
    }
}
