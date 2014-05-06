package com.apmanager.utils;

import java.lang.annotation.Annotation;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author luis
 */
public class ReflectionUtils {

    private static Reflections reflections;

    private static boolean initialized = false;

    public static synchronized void initialize() {
        if (!initialized) {
            reflections = new Reflections(
                    new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forManifest())
            );

            initialized = true;
        }
    }

    public static Set<Class<?>> getTypesAnnotatedWith(
            Class<? extends Annotation> clazz) {
        initialize();
        return reflections.getTypesAnnotatedWith(clazz);
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> clazz) {
        initialize();
        return reflections.getSubTypesOf(clazz);
    }
}
