/**
 * ComputerValidator.java
 */

package com.apmanager.service.base.validator;

import com.apmanager.domain.base.Computer;
import com.apmanager.service.annotations.Validator;
import com.apmanager.service.base.ActionType;
import com.apmanager.service.base.EntityValidator;
import com.apmanager.service.base.exception.ValidationException;

/**
 * @author luis
 * @since May 25, 2014
 */
@Validator(target = Computer.class)
public class ComputerValidator implements EntityValidator<Computer>{

    public void validate(Computer newEntity, Computer oldEntity, ActionType type) {
        ValidationException ex =  new ValidationException();
        if(newEntity.getName() == null || newEntity.getName().isEmpty()){
            ex.addError("Nome inválido", "name", "invalid.name");
        }
        
        if(!ex.isEmpty()){
            throw ex;
        }
    }
    
}
