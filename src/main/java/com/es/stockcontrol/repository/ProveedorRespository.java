package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Proveedor;
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
    public Proveedor create(Proveedor proveedor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            em.persist(proveedor); // Guardar la entidad en la base de datos
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
            return null;
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return proveedor; // Devolver true si la creación fue exitosa, false si hubo error
    }

    // R x id
    public Proveedor read(long id) {
        EntityManager em = getEntityManager();
        Proveedor proveedor = null;
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            proveedor = em.find(Proveedor.class, id); // Buscar la entidad por su ID
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return proveedor; // Devolver el objeto Proveedor encontrado, o null si no se encontró
    }
    // R x nombre
    public Proveedor read(String nombre) {
        EntityManager em = getEntityManager();
        Proveedor proveedor = null;
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            proveedor = em.createQuery("SELECT p FROM Proveedor p WHERE p.nombre = :nombre", Proveedor.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult(); // Buscar el proveedor por nombre
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return proveedor; // Devolver el objeto Proveedor encontrado, o null si no se encontró
    }

    // U
    public Proveedor update(Proveedor proveedor) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(proveedor); // Actualizar la entidad en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return proveedor;
    }

    // D
    public boolean delete(long id) {
        EntityManager em = getEntityManager();
        boolean isDeleted = false;
        try {
            em.getTransaction().begin(); // Iniciar la transacción

            Proveedor existingProveedor = read(id);

            if (existingProveedor != null) {
                em.remove(existingProveedor); // Eliminar la entidad si se encontró
                em.getTransaction().commit(); // Confirmar la transacción
                isDeleted = true; // Marcar como eliminado si la transacción es exitosa
            } else {
                System.out.println("Proveedor no encontrado con el ID: " + id);
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
