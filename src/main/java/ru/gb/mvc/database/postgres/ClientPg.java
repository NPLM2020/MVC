package ru.gb.mvc.database.postgres;

import org.springframework.stereotype.Component;
import ru.gb.mvc.database.ClientDAO;
import ru.gb.mvc.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class ClientPg implements ClientDAO {

    private final EntityManager em;

    public ClientPg(EntityManager em) {
        this.em = em;
    }

    @Override
    public Client addClient(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        return getClientById(client.getId());
    }

    @Override
    public Client getClientById(long id) {
        Query query = em.createQuery("select cl from Client cl where cl.id = :id");
        query.setParameter("id", id);
        return (Client) query.getSingleResult();
    }

    @Override
    public Client updateClient(Client client) {
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
        return getClientById(client.getId());
    }

    @Override
    public void deleteClientById(long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Client cl where cl.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
