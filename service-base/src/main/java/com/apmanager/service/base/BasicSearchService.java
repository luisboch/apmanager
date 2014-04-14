package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAO;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;

/**
 *
 * @author luis
 */
public interface BasicSearchService extends BasicService<BasicSearchDAO>{

    <T extends BasicEntity> List<T> getAll(Class<T> clazz);

    <T extends BasicEntity> List<T> find(
            Class<T> clazz, SearchFilter<? extends T> filter);
}
