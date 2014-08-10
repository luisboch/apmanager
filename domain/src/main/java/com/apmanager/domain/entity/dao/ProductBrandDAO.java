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
            return search(search, filter.getActive());
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
            return (List<T>) search(search, true);
        }

        return super.genericSearch(clazz, search);
    }

    private List<ProductBrand> search(String search, Boolean active) {

        if (search == null) {
            search = "";
        }

        String jpql = ""
                + "select u \n"
                + "  from ProductBrand u \n"
                + " where (lower(u.name) like lower(:search) \n"
                + "    or lower(u.description) like lower(:search)) \n";
        
        if(active != null){
            jpql = jpql.concat("and u.active = :active");
        }
        
        Query q = em.createQuery(jpql);

        q.setParameter("search", "%" + search + "%");
        q.setHint(QueryHints.RESULT_COLLECTION_TYPE, java.util.ArrayList.class);

        if(active != null ){
            q.setParameter("active", active);
        }
        
        return q.getResultList();
    }
}
