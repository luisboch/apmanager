/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base.exception;

/**
 *
 * @author luis
 */
public class ValidationError {

    private String field;
    private final String error;
    private Object instance;
    private String i18n;

    public ValidationError(String field, String error, Object instance, String i18n) {
        this.field = field;
        this.error = error;
        this.instance = instance;
        this.i18n = i18n;
    }
    
    public ValidationError(String error) {
        this.error = error;
    }

    public ValidationError(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    
    public String getError() {
        return error;
    }
    
    @Override
    public String toString() {
        return "ValidationError{" + "field=" + field + ", error=" + error + ", i18n=" + i18n + '}';
    }

}
