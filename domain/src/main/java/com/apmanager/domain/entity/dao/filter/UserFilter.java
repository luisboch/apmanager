package com.apmanager.domain.entity.dao.filter;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.base.SimpleSearchFilter;
import com.apmanager.domain.entity.User;

/**
 * @author luis
 */
public final class UserFilter extends SimpleSearchFilter<Integer, User> implements SearchFilter<User> {

    public UserFilter() {
    }

    public UserFilter(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public UserFilter(Integer maxResults, String[] order, Integer firstResult) {
        setMaxResults(maxResults);
        setOrder(order);
        setFirstResult(firstResult);
    }

    public UserFilter(Integer id, Integer maxResults, String[] order, String name, Integer firstResult) {
        setId(id);
        setName(name);
        setMaxResults(maxResults);
        setOrder(order);
        setFirstResult(firstResult);
    }

    @Override
    public String toString() {
        return "UserFilter{" + "name=" + getName() + '}';
    }

}
