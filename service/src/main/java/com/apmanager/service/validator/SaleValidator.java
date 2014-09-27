package com.apmanager.service.validator;

import com.apmanager.domain.entity.Sale;
import com.apmanager.service.annotations.Validator;
import com.apmanager.service.base.ActionType;
import com.apmanager.service.base.EntityValidator;
import com.apmanager.service.base.exception.ValidationException;
import java.util.Date;

/**
 *
 * @author luis
 */
@Validator(target = Sale.class)
public class SaleValidator implements EntityValidator<Sale> {

    public void validate(Sale newEntity, Sale oldEntity, ActionType type) {
        
        final ValidationException ex = new ValidationException();

        if (type.equals(ActionType.SAVE)) {
            newEntity.setOpenDate(new Date());
        }

        if (newEntity.getComputer() == null) {
            ex.addError("Can't save Sale without computer", "computer", null);
        }

        if (!ex.isEmpty()) {
            throw ex;
        }
        
    }
}