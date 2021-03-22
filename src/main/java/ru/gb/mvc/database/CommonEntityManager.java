package ru.gb.mvc.database;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class CommonEntityManager {

    private final EntityManager em;

    public CommonEntityManager() {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
        em = factory.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    protected <T> T createEntity(T entity) {

        return entity;
    }

    protected <T> T updateEntity(T entity) {

        return entity;
    }

    protected <T> void deleteEntity(T entity, String query) {


    }



}
