/*
 * OutOfDiscountException. 
 */
package com.apmanager.domain.entity.exception;

/**
 *
 * @author luis
 */
public class OutOfDiscountException extends Exception {

    private float minValue = 0;
    private Integer maxDiscountPercent;
    private Integer currentDiscoutPercent;

    public OutOfDiscountException() {
    }

    public OutOfDiscountException(String message) {
        super(message);
    }

    public OutOfDiscountException(Throwable cause) {
        super(cause);
    }

    public OutOfDiscountException(String message, Throwable cause) {
        super(message, cause);
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxDiscountPercent() {
        return maxDiscountPercent;
    }

    public void setMaxDiscountPercent(Integer maxDiscountPercent) {
        this.maxDiscountPercent = maxDiscountPercent;
    }

    public Integer getCurrentDiscoutPercent() {
        return currentDiscoutPercent;
    }

    public void setCurrentDiscoutPercent(Integer currentDiscoutPercent) {
        this.currentDiscoutPercent = currentDiscoutPercent;
    }
}
