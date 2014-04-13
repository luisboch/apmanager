/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public List<User> search(UserFilter filter) {
        return null;
    }
    
    public List<User> getAll() {
        return Collections.emptyList();
    }
}
