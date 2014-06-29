/**
 * ComputerFilter.java
 */
package com.apmanager.domain.base.filter;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.base.SimpleSearchFilter;
import com.apmanager.domain.base.Computer;

/**
 * @author luis
 * @since May 25, 2014
 */
public final class ComputerFilter
        extends SimpleSearchFilter<Integer, Computer>
        implements SearchFilter<Computer> {

    public ComputerFilter() {
    }

    public ComputerFilter(Integer id) {
        setId(id);
    }

    public ComputerFilter(String name) {
        setName(name);
    }

    public ComputerFilter(String[] order, String name, Boolean active) {
        this.setOrder(order);
        setName(name);
        setActive(active);
    }

}
