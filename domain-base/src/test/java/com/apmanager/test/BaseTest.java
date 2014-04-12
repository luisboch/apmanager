package com.apmanager.test;


import com.apmanager.domain.base.EntityManagerProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class.getName());

    private final static List<EntityManagerProvider> providers = new LinkedList<>();

    static {
        
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
        );

        Set<Class<? extends EntityManagerProvider>> types
                = reflections.getSubTypesOf(EntityManagerProvider.class);

        try {

            for (Class<? extends EntityManagerProvider> clazz : types) {
                providers.add(clazz.newInstance());
            }

        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    protected static EntityManager getEntityManager() {
        
        List<EntityManager> ems = getEntityManagers();

        if (ems.isEmpty()) {
            return null;
        }

        return ems.get(0);
    }

    protected static List<EntityManager> getEntityManagers() {
        
        List<EntityManager> managers = new ArrayList<>();
        
        Collections.sort(providers);

        for (EntityManagerProvider entityManagerProvider : providers) {
            managers.add(entityManagerProvider.getFactory().createEntityManager());
        }

        return managers;
    }
}
