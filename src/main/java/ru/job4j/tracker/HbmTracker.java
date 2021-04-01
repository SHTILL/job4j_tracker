package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.OptimisticLockException;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        Session session = sf.openSession();
        try (session) {
            session.beginTransaction();
            item.setId(id);
            session.update(item);
            session.getTransaction().commit();
        } catch (OptimisticLockException e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item(null);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        TransactionStatus status = session.getTransaction().getStatus();
        session.close();
        return status == TransactionStatus.COMMITTED;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = (List<Item>) session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Item> q = session.createQuery("from Item where name = :paramName ");
        q.setParameter("paramName", key);
        List<Item> result = q.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
