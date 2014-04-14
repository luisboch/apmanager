/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.EntityManagerFactoryProvider;
import com.apmanager.service.annotations.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author luis
 */
public class Services {

    private static final Logger log = Logger.getLogger(Services.class.getName());

    private static final List<EntityManagerFactoryProvider> providers = new LinkedList<>();

    private static final EntityManagerProvider provider = new EntityManagerProvider();

    private static Map<Class, Method> actions = new HashMap<>();

    static {

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
        );

        Set<Class<? extends EntityManagerFactoryProvider>> types
                = reflections.getSubTypesOf(EntityManagerFactoryProvider.class);

        try {
            for (Class<? extends EntityManagerFactoryProvider> clazz : types) {
                providers.add(clazz.newInstance());
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    protected static EntityManager getEntityManager() {

        List<EntityManager> ems = getEntityManagers();

        if (ems.isEmpty()) {
            return null;
        }

        return ems.get(0);
    }

    static List<EntityManager> getEntityManagers() {

        List<EntityManager> managers = new ArrayList<>();

        Collections.sort(providers);

        providers.stream().filter((entityManagerProvider) -> (entityManagerProvider.isActive())).forEach((entityManagerProvider) -> {
            managers.add(entityManagerProvider.getFactory().createEntityManager());
        });

        return managers;
    }

    public static <E extends BasicEntityServiceImpl> E getService(Class<E> clazz) {

        if (clazz == null) {
            return null;
        }

        try {
            E service = clazz.newInstance();

            service.setProvider(provider);
            checkPostConstruction(clazz, service);
            return service;
        } catch (Exception ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }

    private static void checkPostConstruction(Class clazz, BasicEntityServiceImpl instance) {

        Method postConstruct = null;

        if (actions.containsKey(clazz)) {
            postConstruct = actions.get(clazz);
        } else {
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getAnnotation(PostConstruct.class) != null) {
                    postConstruct = m;
                    actions.put(clazz, m);
                    break;
                }
            }
        }

        if (postConstruct != null) {
            try {
                postConstruct.invoke(instance);
            } catch (Throwable ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }
}
