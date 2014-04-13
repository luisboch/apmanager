package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;

/**
 *
 * @author luis
 * @param <E>
 */
public interface EntityValidator<E extends BasicEntity>{
    void validate(E newEntity, E oldEntity, ActionType type);
}
