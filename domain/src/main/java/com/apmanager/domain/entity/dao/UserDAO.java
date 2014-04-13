package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicDAOImpl;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.filter.UserFilter;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luis
 */
public class UserDAO extends BasicDAOImpl<User, UserFilter>{

    @Override
    public List<User> search(UserFilter filter) {
        return null;
    }
    
    @Override
    public List<User> getAll() {
        return Collections.emptyList();
    }
}
