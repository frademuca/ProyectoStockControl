package com.es.stockcontrol.repository;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductoRepository {
    public EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager("NAME_PERSISTENCE_UNIT");
    }
    public void closeEntityManager(EntityManager em) {
        HibernateUtil.closeEntityManager(em);
    }

    // C
    // ya se encargaran de mandarmelo con los datos desde otras capas
    public Producto create(Producto producto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            em.persist(producto); // Guardar la entidad en la base de datos
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
            return null;
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return producto; // Devolver true si la creación fue exitosa, false si hubo error
    }

    // R
    public Producto read(String id) {
        EntityManager em = getEntityManager();
        Producto producto = null;
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            producto = em.find(Producto.class, id); // Buscar la entidad por su ID
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return producto; // Devolver el objeto Producto encontrado, o null si no se encontró
    }

    // U
    public Producto update (Producto producto){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin(); // Iniciar la transacción
            em.merge(producto); // Persistir los cambios en la base de datos
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
            return null;
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return producto;
    }

    // D
    public boolean delete(String id) {
        EntityManager em = getEntityManager();
        boolean isDeleted = false;
        try {
            em.getTransaction().begin(); // Iniciar la transacción

            Producto existingProducto = read(id);

            if (existingProducto != null) {
                em.remove(existingProducto); // Eliminar la entidad si se encontró
                em.getTransaction().commit(); // Confirmar la transacción
                isDeleted = true; // Marcar como eliminado si la transacción es exitosa
            } else {
                System.out.println("Producto no encontrado con el ID: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback(); // Revertir la transacción si ocurre un error
            e.printStackTrace(); // Mostrar el error en la consola para depuración
        } finally {
            em.close(); // Cerrar el EntityManager
        }
        return isDeleted; // Devolver true si la eliminación fue exitosa, false si hubo error
    }

    public List<Producto> findAll() {
        EntityManager em = getEntityManager();
        List<Producto> productos = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
            productos = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return productos;
    }
}
