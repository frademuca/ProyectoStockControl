package com.es.stockcontrol.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    // Mapa para almacenar las diferentes EntityManagerFactory según el Persistence Unit
    private static final Map<String, EntityManagerFactory> entityManagerFactories = new ConcurrentHashMap<>();

    // Método para obtener el EntityManagerFactory según el nombre del Persistence Unit
    public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName) {

        return entityManagerFactories.computeIfAbsent(persistenceUnitName, puName -> {
            try {
                return Persistence.createEntityManagerFactory(puName);
            } catch (Throwable ex) {
                logger.log(Level.SEVERE, "Error al crear EntityManagerFactory para: " + puName, ex);
                throw new ExceptionInInitializerError(ex);
            }
        });
    }

    // Método para obtener el EntityManager según el Persistence Unit
    public static EntityManager getEntityManager(String persistenceUnitName) {
        return getEntityManagerFactory(persistenceUnitName).createEntityManager();
    }

    // Método para cerrar todos los EntityManagerFactory
    public static void shutdown() {
        entityManagerFactories.forEach((puName, emf) -> {
            if (emf != null && emf.isOpen()) {
                emf.close();
                //entityManagerFactories.remove(puName);
                logger.log(Level.INFO, "EntityManagerFactory para {0} cerrado correctamente.", puName);
            }
        });
    }

    public static void closeEntityManager(EntityManager em) {
        try {
            if(em != null && em.isOpen()) {
                em.close();
                //logger.log(Level.INFO, "EntityManagerFactory para {0} cerrado correctamente.", puName);
            }
        } catch (IllegalStateException e) {
            e.getStackTrace();
        }
    }
}