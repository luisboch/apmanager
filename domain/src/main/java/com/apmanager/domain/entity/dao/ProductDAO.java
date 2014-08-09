package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.dao.filter.ProductFilter;
import java.util.List;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author Luis
 */
public class ProductDAO extends BasicSearchDAOImpl {

    public List<Product> search(ProductFilter filter) {

        if (filter.getSearch() != null) {
            final String search = filter.getSearch();
            return search(search, filter.getActive(), filter.getOrder());
        }

        if (filter.getId() != null) {
            filter.getParams().put("id", filter.getId());
        }

        if (filter.getName() != null) {
            filter.getParams().put("name", filter.getName());
        }

        return super.find(Product.class, filter);
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {

        if (clazz.equals(Product.class)) {
            return (List<T>) search(search, true, new String[]{"name"});
        }

        return super.genericSearch(clazz, search);
    }

    private List<Product> search(String search, Boolean active, String[] order) {

        if (search == null) {
            search = "";
        }

        final String[] words = search.split(" ");

        final StringBuilder jpql = new StringBuilder();

        jpql.append("select u \n")
                .append("  from Product u \n");

        for (int i = 0; i < words.length; i++) {
            jpql.append("join u.keyworkds k").append(i + 1).append("\n");
        }

        if (words.length > 0) {
            jpql.append(" where \n");
            for (int i = 0; i < words.length; i++) {
                if (i != 0) {
                    jpql.append("and ");
                }

                jpql.append("lower(k").append(i + 1).
                        append(".keyWord) like lower(").
                        append(":p").append(i).append(")\n");
            }
        }

        if (active != null) {
            if (words.length > 0) {
                jpql.append("and ");
            }
            jpql.append("u.active = :active ");
        }

        if (order != null && order.length > 0) {
            jpql.append("order by ");
            for (int i = 0; i < order.length; i++) {
                if (i != 0) {
                    jpql.append(", ");
                }
                jpql.append("u.").append(order[i]).append(" ");
            }
        }

        final Query q = em.createQuery(jpql.toString());

        if (words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                q.setParameter("p" + i, "%" + words[i] + "%");
            }
        }
        q.setHint(QueryHints.RESULT_COLLECTION_TYPE, java.util.ArrayList.class);

        if (active != null) {
            q.setParameter("active", active);
        }

        return q.getResultList();
    }
}
