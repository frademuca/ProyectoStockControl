package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.User;
import com.es.stockcontrol.utils.HibernateUtil;
import jakarta.persistence.EntityManager;

public class UserRepository {
    public EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager("NAME_PERSISTENCE_UNIT");
    }
    public void closeEntityManager(EntityManager em) {
        HibernateUtil.closeEntityManager(em);
    }

    // C
    public User create(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            em.persist(user); // Guardar la entidad en la base de datos
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
            return null;
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return user;
    }

    // R
    public User read(String id) {
        EntityManager em = getEntityManager();
        User user = null;
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            user = em.find(User.class, id); // Buscar la entidad por su ID
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return user; // Devolver el objeto User encontrado, o null si no se encontró
    }

    //U
    public User update(User user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user); // Actualizar la entidad en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return user;
    }

    // D
    public boolean delete(String id) {
        EntityManager em = getEntityManager();
        boolean isDeleted = false;
        try {
            em.getTransaction().begin(); // Iniciar la transacción

            User existingUser = read(id);

            if (existingUser != null) {
                em.remove(existingUser); // Eliminar la entidad si se encontró
                em.getTransaction().commit(); // Confirmar la transacción
                isDeleted = true; // Marcar como eliminado si la transacción es exitosa
            } else {
                System.out.println("Usuario no encontrado con el ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return isDeleted; // Devolver true si la eliminación fue exitosa, false si hubo error
    }

}
