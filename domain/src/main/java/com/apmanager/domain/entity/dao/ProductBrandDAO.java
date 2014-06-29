package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.ProductBrand;
import com.apmanager.domain.entity.dao.filter.ProductBrandFilter;
import java.util.List;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author Luis
 */
public class ProductBrandDAO extends BasicSearchDAOImpl{
    
    public List<ProductBrand> search(ProductBrandFilter filter) {

        if (filter.getParams().get("search") != null) {
            final String search = (String) filter.getParams().get("search");
            return search(search);
        }

        if (filter.getId() != null) {
            filter.getParams().put("id", filter.getId());
        }

        if (filter.getName() != null) {
            filter.getParams().put("name", filter.getName());
        }

        return super.find(ProductBrand.class, filter);
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {

        if (clazz.equals(ProductBrand.class)) {
            return (List<T>) search(search);
        }

        return super.genericSearch(clazz, search);
    }

    private List<ProductBrand> search(String search) {

        if (search == null) {
            search = "";
        }

        String jqpl = ""
                + "select u "
                + "  from ProductBrand u "
                + " where lower(u.name) like lower(:search) "
                + "    or lower(u.description) like lower(:search) ";

        Query q = em.createQuery(jqpl);

        q.setParameter("search", "%" + search + "%");
        q.setHint(QueryHints.RESULT_COLLECTION_TYPE, java.util.ArrayList.class);

        return q.getResultList();
    }
}
