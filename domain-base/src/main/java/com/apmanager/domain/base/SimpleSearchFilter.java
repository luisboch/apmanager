/**
 * SimpleSearchFilter.java
 */
package com.apmanager.domain.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luis
 * @param <A> PK type
 * @param <E> Target type
 * @since May 25, 2014
 */
public class SimpleSearchFilter<A extends Number, E extends BasicEntity<A>>
        implements SearchFilter<E> {

    private Integer maxResults;
    private String[] order;
    private Integer firstResult;

    private final Map<String, Object> params = new HashMap<>();

    public A getId() {
        return (A) getParams().get("id");
    }

    public void setId(A id) {
        getParams().put("id", id);
    }

    @Override
    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    @Override
    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }
    public String getSearch(){
        return (String) getParams().get("search");
    }
    
    public void setSearch(String search){
        getParams().put("search", search);
    }
    
    public String getName() {
        return (String) getParams().get("name");
    }

    public void setName(String name) {

        getParams().put("name", name);
    }

    @Override
    public Integer getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(Integer firstResult) {
        this.firstResult = firstResult;
    }

    @Override
    public Boolean getActive() {
        return (Boolean) getParams().get("active");
    }

    public void setActive(Boolean active) {
        getParams().put("active", active);
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

}
