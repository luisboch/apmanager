package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicDAOImpl;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.filter.UserFilter;
import java.util.List;

/**
 *
 * @author luis
 */
public class UserDAO extends BasicDAOImpl<User, UserFilter>{

    @Override
    public List<User> search(UserFilter filter) {
        
        if(filter.getId() != null ){
            filter.getParams().put("id", filter.getId());
        }
        
        if(filter.getName() != null ){
            filter.getParams().put("name", filter.getName());
        }
        
        return super.find(User.class, filter);
    }
    
    @Override
    public List<User> getAll() {
        UserFilter filter = new UserFilter();
        
        filter.setOrder(new String[]{"name"});
        
        return search(filter);
    }
}
