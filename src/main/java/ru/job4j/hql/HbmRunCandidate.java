package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRunCandidate {
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate_hql_examples.cfg.xml").build();
    private static final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static void insertCandidates() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", 3, 100_000);
            Candidate two = Candidate.of("Nikolay", 4, 70_000);
            Candidate three = Candidate.of("Nikita", 5, 12_000);

            session.save(one);
            session.save(two);
            session.save(three);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectAllCandidates() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from ru.job4j.hql.Candidate");
            for (Object c : query.list()) {
                System.out.println(c);
            }

            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectCandidatesById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Candidate c where c.id = :fId");
            query.setParameter("fId", id);
            System.out.println(query.uniqueResult());

            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateCandidate(int id, int experience, float salary) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery(
                    "update Candidate c set c.experience = :newExperience, c.salary = :newSalary where c.id = :fId"
            );
            query.setParameter("newExperience", experience);
            query.setParameter("newSalary", salary);
            query.setParameter("fId", id);
            query.executeUpdate();

            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteCandidate(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();

            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertCandidates();
        System.out.println("Candidates:");
        selectAllCandidates();
        System.out.println("Candidate by id:");
        selectCandidatesById(2);
        updateCandidate(3, 6, 22_000);
        System.out.println("Candidates:");
        selectAllCandidates();
        deleteCandidate(3);
        System.out.println("Candidates:");
        selectAllCandidates();
    }
}
