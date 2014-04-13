package com.apmanager.service.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class ValidationException extends RuntimeException {

    private final List<ValidationError> _errors = new ArrayList<>();
    
    private Class classFor;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
    
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
    }

    public ValidationException(Class classFor, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException(Class classFor, Throwable cause) {
        super(cause);
    }

    public ValidationException(Class classFor, String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Class classFor, String message) {
        super(message);
    }

    public ValidationException(Class classFor) {
    }

    public void addError(String error) {
        _errors.add(new ValidationError(error));
    }

    public void addError(String error, String field, String i18n) {
        _errors.add(new ValidationError(field, error, classFor, i18n));
    }

    public void addError(String error, String field, Class classFor, String i18n) {
        _errors.add(new ValidationError(field, error, classFor, i18n));
    }

    public boolean isEmpty() {
        return _errors.isEmpty();
    }

    public void remove(ValidationError error) {
        _errors.remove(error);
    }

    public void clear() {
        _errors.clear();
    }

    public List<String> getErros() {
        List<String> es = new ArrayList<>();
        _errors.stream().forEach((e) -> {
            es.add(e.getError());
        });
        return es;
    }

    public List<ValidationError> getErrors() {
        return _errors;
    }

    public Class getClassFor() {
        return classFor;
    }

    public void setClassFor(Class classFor) {
        this.classFor = classFor;
    }

}
