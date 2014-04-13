/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;

/**
 *
 * @author luis
 * @param <E>
 * @param <S>
 */
public interface BasicService<E extends BasicEntity, S extends SearchFilter<E>> {

    List<E> getAll();

    List<E> search(S filter);
    
}
