package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.service.annotations.Validator;
import com.apmanager.utils.ReflectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Validators {

    private static final Logger log = Logger.getLogger(Validators.class.getName());

    private static final Map< Class<? extends BasicEntity>, Map<String, EntityValidator>> validators
            = new HashMap<>();

    private static boolean initialized = false;

    public static EntityValidator
            getDefaultValidators(Class<BasicEntity> clazz) {
        return getValidators(clazz, "ALL").get(0);
    }

    public static List<EntityValidator> getValidators(
            Class<BasicEntity> clazz, String context) {

        if (!initialized) {
            initialize();
        }

        final List<EntityValidator> result = new ArrayList<>();

        if (validators.get(clazz) != null) {

            final EntityValidator all = validators.get(clazz).get("ALL");

            if (all != null) {
                result.add(all);
            } else {
                log.log(Level.WARNING,
                        "Not found Validator for context ALL, this might a problem!");
            }

            if (context != null && !context.equals("ALL")) {

                final EntityValidator request = validators.get(clazz).get(context);

                if (request == null) {
                    result.add(request);
                } else {
                    log.log(Level.WARNING,
                            "Not found Validator for context {0}, this might a problem!",
                            context);
                }
            }
        }

        if (result.isEmpty()) {
            throw new IllegalStateException("Not found valitor for class " + clazz.getCanonicalName() + " context " + context);
        }

        return result;
    }

    public static synchronized void initialize() {

        if (!initialized) {

            ReflectionUtils.initialize();

            Set<Class<?>> types
                    = ReflectionUtils.getTypesAnnotatedWith(Validator.class);

            System.out.println("Found " + types.size() + " validators...");

            for (Class<?> clazz : types) {

                Class<EntityValidator> validatorClass = null;

                try {
                    validatorClass = (Class<EntityValidator>) clazz;
                } catch (Exception ex) {
                    throw new IllegalStateException(
                            "Validator " + clazz.getCanonicalName()
                            + " must implement interface "
                            + EntityValidator.class.getCanonicalName());
                }

                EntityValidator validator = null;

                try {
                    validator = validatorClass.newInstance();
                } catch (Exception ex) {
                    throw new IllegalStateException(
                            "Validator " + clazz.getCanonicalName()
                            + " must have a default constructor");
                }

                Validator annotation = validatorClass.getAnnotation(Validator.class);

                Map<String, EntityValidator> contexts;

                Class<? extends BasicEntity> target = annotation.target();

                if (validators.get(target) == null) {
                    contexts = new HashMap<>();
                    validators.put(target, contexts);
                } else {
                    contexts = validators.get(target);
                }

                contexts.put(annotation.context(), validator);

            }

            initialized = true;
        }
    }
}
