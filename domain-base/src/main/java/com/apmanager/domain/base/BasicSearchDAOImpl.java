package com.apmanager.domain.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 * @author luis
 */
public class BasicSearchDAOImpl extends BasicDAOImpl implements BasicSearchDAO {

    public BasicSearchDAOImpl() {
    }
    
    @Override
    public <T extends BasicEntity> List<T> getAll(Class<T> clazz) {
        return em.createNamedQuery("selec x from " + clazz.getCanonicalName() + " x ").getResultList();
    }

    @Override
    public <T extends BasicEntity> List<T> find(
            Class<T> clazz, SearchFilter<? extends T> filter) {

        final StringBuilder jpql = new StringBuilder("select x from " + clazz.getSimpleName() + " x ");

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
            q.setFirstResult(filter.getFirstResult());
        }

        if (filter.getMaxResults() != null) {
            q.setMaxResults(filter.getMaxResults());
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

                    final String paramName = "param" + i;

                    where.append("x.").append(k).append("=:").append(paramName).append("\n");

                    resultParams.put(paramName, queryParams.get(k));

                    i++;

                }
            }

            return where == null ? "" : where.toString();
        }
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
