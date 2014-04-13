package com.apmanager.domain.entity.dao.filter;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.User;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luis
 */
public class UserFilter implements SearchFilter<User>{
    
    private Integer id;
    private Integer maxResults;
    private String[] order;
    private String name;
    private Integer firstResult;
    
    private Boolean active;
    
    private Map<String, Object> params = new HashMap<String, Object>();


    public UserFilter() {
    }

    public UserFilter(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserFilter(Integer maxResults, String[] order, Integer firstResult) {
        this.maxResults = maxResults;
        this.order = order;
        this.firstResult = firstResult;
    }

    public UserFilter(Integer id, Integer maxResults, String[] order, String name, Integer firstResult) {
        this.id = id;
        this.maxResults = maxResults;
        this.order = order;
        this.name = name;
        this.firstResult = firstResult;
    }
    
    @Override
    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    @Override
    
    public Boolean getActive() {
        return active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
    @Override
    public String toString() {
        return "UserFilter{" + "name=" + name + '}';
    }

    
    
    
}
