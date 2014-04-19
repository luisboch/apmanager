package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.service.annotations.Validator;
import java.util.HashMap;
import java.util.HashSet;
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

    private static final Map<Class<? extends BasicEntity>, Set<EntityValidator>> validators
            = new HashMap<>();

    private static boolean initialized = false;

    public static Set<EntityValidator>
            getValidators(Class<? extends BasicEntity> clazz) {
        return getDefaultValidator(clazz, "DEFAULT");
    }

    public static Set<EntityValidator>
            getDefaultValidator(Class<? extends BasicEntity> clazz, String context) {

        if (!initialized) {
            initialize();
        }
        Set<EntityValidator> entityValidators
                = validators.get(clazz);

        if (entityValidators == null || entityValidators.isEmpty()) {
            throw new IllegalStateException("Not found validor for class " + clazz.getCanonicalName());
        }

        return entityValidators;
    }

    private static synchronized void initialize() {

        if (!initialized) {

            final Map<Class<?>, Map<String, EntityValidator>> entityValidators
                    = new HashMap<>();

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

                Validator annotation = validatorClass.getAnnotation(Validator.class);

                Map<String, EntityValidator> contexts;

                Class<? extends BasicEntity> target = clazz.getAnnotation(Validator.class).target();

                if (entityValidators.get(target) == null) {
                    contexts = new HashMap<>();
                    entityValidators.put(target, contexts);
                } else {
                    contexts = entityValidators.get(target);
                }

                for (String context : annotation.context()) {
                    contexts.put(context, validator);
                }

            }

            configureValidators(types, entityValidators);

            initialized = true;
        }
    }

    private static void searchParents(
            EntityValidator validator, Set<EntityValidator> validators,
            Map<String, EntityValidator> contexts) {

        Validator annotation = validator.getClass().getAnnotation(Validator.class);

        if (annotation.extendContext() != null && annotation.extendContext().length > 0) {
            for (String context : annotation.extendContext()) {
                final EntityValidator parent = contexts.get(context);
                validators.add(parent);
                searchParents(parent, validators, contexts);
            }
        }

    }

    private static void configureValidators(Set<Class<?>> types,
            Map<Class<?>, Map<String, EntityValidator>> entityValidators) {

        for (Class<?> clazz : types) {

            Map<String, EntityValidator> contexts = entityValidators.get(clazz);

            final Set<EntityValidator> validatorsList = new HashSet<>();

            final EntityValidator allValidator = contexts.get("ALL");

            if (allValidator != null) {
                validatorsList.add(allValidator);
                searchParents(allValidator, validatorsList, contexts);
            }

            for (String context : contexts.keySet()) {
                
                
                Set<EntityValidator> currentValidators = new HashSet<>();
                currentValidators.addAll(validatorsList);
                
                EntityValidator currentValidator = contexts.get(context);

                if (currentValidator == null) {
                    currentValidator = contexts.get("DEFAULT");
                }
                

                if (currentValidator != null) {
                    validatorsList.add(currentValidator);
                    searchParents(currentValidator, validatorsList, contexts);
                }
                
                
                Set<EntityValidator> loaded 
                        = validators.get(clazz);
                
                if(loaded == null){
                    loaded = new HashSet<>();
                    validators.put((Class<? extends BasicEntity>) clazz, loaded);
                }
                loaded.addAll(currentValidators);
                
            }
        }

    }
}
