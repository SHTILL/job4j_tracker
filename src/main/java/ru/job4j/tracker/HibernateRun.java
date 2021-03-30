package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            HibernateItem item = create(new HibernateItem("Learn Hibernate"), sf);
            System.out.println(item);
            item.setName("Learn Hibernate 5.");
            update(item, sf);
            System.out.println(item);
            HibernateItem rsl = findById(item.getId(), sf);
            System.out.println(rsl);
            delete(rsl.getId(), sf);
            List<HibernateItem> list = findAll(sf);
            for (HibernateItem it : list) {
                System.out.println(it);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static HibernateItem create(HibernateItem item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(HibernateItem item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        HibernateItem item = new HibernateItem(null);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public static List<HibernateItem> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<HibernateItem> result = (List<HibernateItem>) session.createQuery("from ru.job4j.tracker.HibernateItem").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static HibernateItem findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        HibernateItem result = session.get(HibernateItem.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
