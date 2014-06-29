
package com.apmanager.ui.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author luis
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Window {
    String[] menu() default {"records.menu"};
    String title() default "";
    int priority() default 0;
    boolean isDefault() default false;
}
