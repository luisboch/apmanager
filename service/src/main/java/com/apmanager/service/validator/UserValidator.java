package com.apmanager.service.validator;

import com.apmanager.domain.entity.User;
import com.apmanager.service.annotations.Validator;
import com.apmanager.service.base.ActionType;
import com.apmanager.service.base.EntityValidator;
import com.apmanager.service.base.exception.ValidationException;

/**
 *
 * @author luis
 */
@Validator(target = User.class)
public class UserValidator implements EntityValidator<User> {

    public void validate(User newEntity, User oldEntity, ActionType type) {

        ValidationException ex = new ValidationException(User.class);

        if (newEntity.getName() == null || newEntity.getName().isEmpty()) {
            ex.addError("Nome inválido", "name", null);
        }

        if (newEntity.getPasswd() == null || newEntity.getPasswd().isEmpty()) {
            ex.addError("Senha inválida", "passwd", null);
        }

        if (newEntity.getUsername() == null || newEntity.getUsername().isEmpty()) {
            ex.addError("Login inválido", "username", null);
        }

        if (!ex.isEmpty()) {
            throw ex;
        }
    }

}
