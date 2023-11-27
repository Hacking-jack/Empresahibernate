package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.Getter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class HibernateManager {
    private static HibernateManager controller;

    // Creamos las EntityManagerFactory para manejar las entidades y transacciones
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateManager() {
    }

    public static HibernateManager getInstance() {
        if (controller == null)
            controller = new HibernateManager();
        return controller;
    }

    public void open() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
      // manager= Persistence.createEntityManagerFactory("default").createEntityManager();
       manager = entityManagerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }

}
