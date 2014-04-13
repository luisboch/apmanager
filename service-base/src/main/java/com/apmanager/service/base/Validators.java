package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.service.annotations.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author luis
 */
public class Validators {

    private static final Map<Class<? extends BasicEntity>, EntityValidator> validators
            = new HashMap<>();

    private static boolean initialized = false;

    public static EntityValidator
            getValidator(Class<? extends BasicEntity> clazz) {

        if (!initialized) {
            initialize();
        }

        EntityValidator validator = validators.get(clazz);

        if (validator == null) {
            throw new IllegalStateException("Not found validor for class " + clazz.getCanonicalName());
        }

        return validator;
    }

    private static synchronized void initialize() {

        if (!initialized) {
            Reflections reflections = new Reflections(
                    new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forJavaClassPath())
            );

            Set<Class<?>> types
                    = reflections.getTypesAnnotatedWith(Validator.class);

            for (Class<?> clazz : types) {

                Class<EntityValidator> validatorClass = null;

                try {
                    validatorClass = (Class<EntityValidator>) clazz;
                } catch (Exception ex) {
                    throw new IllegalStateException(
                            "Validator " + clazz.getCanonicalName() + " must implement interface " + EntityValidator.class.getCanonicalName());
                }

                EntityValidator validator = null;

                try {
                    validator = validatorClass.newInstance();
                } catch (Exception ex) {
                    throw new IllegalStateException(
                            "Validator " + clazz.getCanonicalName() + " must have a default constructor");
                }

                validators.put(clazz.getAnnotation(Validator.class).target(), validator);
            }

            initialized = true;
        }
    }
}
