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
    public boolean create(User user) {
        EntityManager em = getEntityManager();
        boolean isCreated = false; // Variable para indicar si la creación fue exitosa
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            em.persist(user); // Guardar la entidad en la base de datos
            em.getTransaction().commit(); // Confirmar la transacción
            isCreated = true; // Si se llega aquí, la operación fue exitosa
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return isCreated; // Devolver true si la creación fue exitosa, false si hubo error
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

    // U
    // VER SI MEJOR USAR UN BOOLEAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public User update(String id, String newName, String newPassword){
        EntityManager em = getEntityManager();
        User existingUser = null;
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            existingUser = em.find(User.class, id); // Buscar la entidad por su ID

            if (existingUser != null) {
                // Actualizar los campos del usuario
                existingUser.setNombre_usuario(newName); // Cambiar el nombre
                existingUser.setPassword(newPassword); // Cambiar el email

                em.merge(existingUser); // Persistir los cambios en la base de datos
                em.getTransaction().commit(); // Confirmar la transacción
            } else {
                System.out.println("Usuario no encontrado con el ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return existingUser; // Devolver el usuario actualizado o null si no se encontró
    }

    public boolean delete(String id){

    }
}
