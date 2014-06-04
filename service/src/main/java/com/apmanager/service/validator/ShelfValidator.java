/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.validator;

import com.apmanager.domain.entity.Shelf;
import com.apmanager.service.annotations.Validator;
import com.apmanager.service.base.ActionType;
import com.apmanager.service.base.EntityValidator;

/**
 *
 * @author felipe
 */

@Validator(target = Shelf.class)
public class ShelfValidator implements EntityValidator<Shelf>{

    public void validate(Shelf newEntity, Shelf oldEntity, ActionType type) {
        
    }
    
}
