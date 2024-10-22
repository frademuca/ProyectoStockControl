package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.User;
import com.es.stockcontrol.utils.HibernateUtil;
import jakarta.persistence.EntityManager;

public class ProveedorRespository {
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
    // no hace falta el user, de todas formas se haría pasando un usuario nuevo por parametros y ya esta
    // de la logica se encargaria el service (el que lee, comprueba, llama al update del repository)
//    public boolean update(String id, String newName, String newPassword){
//        EntityManager em = getEntityManager();
//        boolean isUpdated = false;
//        try {
//            em.getTransaction().begin(); // Iniciar la transacción
//            User existingUser = read(id);
//
//            if (existingUser != null) {
//                // Actualizar los campos del usuario
//                existingUser.setNombreUsuario(newName); // Cambiar el nombre
//                existingUser.setPassword(newPassword); // Cambiar la password
//
//                em.merge(existingUser); // Persistir los cambios en la base de datos
//                em.getTransaction().commit(); // Confirmar la transacción
//                isUpdated = true;
//            } else {
//                System.out.println("Usuario no encontrado con el ID: " + id);
//            }
//        } catch (Exception e) {
//            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
//            e.printStackTrace(); // Mostrar el error en la consola para depuración
//        } finally {
//            em.close(); // Cerrar el EntityManager
//        }
//        return isUpdated;
//    }

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
