package app.repositories;

import app.HibernateUtil;
import app.entities.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class StudentRepository {
    public static void save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            System.exit(1);
        }
    }

    public static void saveAll(ArrayList<Student> students){
        for (Student student : students) {
           StudentRepository.save(student);
        }
    }
}
