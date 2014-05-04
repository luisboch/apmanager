package com.apmanager.domain.base;

import java.util.List;

/**
 *
 * @author luis
 */
public interface BasicSearchDAO extends BasicDAO {

    <T extends BasicEntity> List<T> getAll(Class<T> clazz);

    <T extends BasicEntity> List<T> find(
            Class<T> clazz, SearchFilter<? extends T> filter);

    <T extends BasicEntity> List<T> genericSearch(Class<T> clazz,
            String search);

}
