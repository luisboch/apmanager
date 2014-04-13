/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.domain.base;

import java.util.Map;

/**
 *
 * @author luis
 * @param <E>
 */
public interface SearchFilter <E extends BasicEntity> {
    Integer getFirstResult();
    Integer getMaxResults();
    String[] getOrder();
    Map<String, Object> getParams();
    Boolean getActive();
}
