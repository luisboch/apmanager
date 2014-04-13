package com.apmanager.domain.base.test;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.EntityManagerFactoryProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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
public class BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class.getName());

    private final static List<EntityManagerFactoryProvider> providers = new LinkedList<>();

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

        providers.stream().filter((entityManagerProvider) -> (entityManagerProvider.isActive())).forEach((entityManagerProvider) -> {
            managers.add(entityManagerProvider.getFactory().createEntityManager());
        });

        return managers;
    }

    protected <E extends BasicDAO> E getDAO(Class<E> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            E dao = clazz.newInstance();
            dao.setEntityManager(getEntityManager());
            return dao;
        } catch (Exception ex) {
            Logger.getLogger(BaseTest.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }
}
