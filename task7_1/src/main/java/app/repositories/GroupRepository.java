package app.repositories;

import app.HibernateUtil;
import app.entities.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class GroupRepository {
//    public static void getByName(String name) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.get(group);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            System.exit(1);
//        }
//    }

    public static void save(Group group) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            System.exit(1);
        }
    }

    public static void saveAll(ArrayList<Group> groups){
        for (Group group : groups) {
           GroupRepository.save(group);
        }
    }
}
