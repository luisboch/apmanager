package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.filter.UserFilter;
import java.util.List;
import javax.persistence.Query;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author luis
 */
public class UserDAO extends BasicSearchDAOImpl {

    public List<User> search(UserFilter filter) {

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

        return super.find(User.class, filter);
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {
        
        if(clazz.equals(User.class)){
            return (List<T>) search(search);
        }
        
        return super.genericSearch(clazz, search);
    }

    
    private List<User> search(String search) {

        if (search == null) {
            search = "";
        }
        
        String jqpl = ""
                + "select u "
                + "  from User u "
                + " where lower(u.name) like lower(:search) "
                + "    or lower(u.username) like lower(:search) ";

        Query q = em.createQuery(jqpl);

        q.setParameter("search", "%" + search + "%");
        q.setHint(QueryHints.RESULT_COLLECTION_TYPE, java.util.ArrayList.class);

        return q.getResultList();
    }

}
