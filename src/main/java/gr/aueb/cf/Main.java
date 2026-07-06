package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.*;

import java.util.List;

/**
 * Hello world!
 */
public class Main {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(
                    "schoolPU");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //Select all teachers
            String query = "SELECT t FROM Teacher t ";
            TypedQuery<Teacher> typedQuery =
                    em.createQuery(query, Teacher.class);
            List<Teacher> teachers = typedQuery.getResultList();
            teachers.forEach(System.out::println);

            //Select all courses
            String query2 = "SELECT c FROM Course c ";
            List<Course> courses =
                    em.createQuery(query2, Course.class).getResultList();
            courses.forEach(System.out::println);





            tx.commit();

//            Teacher alice = new Teacher("Alice", "Smith");
//           em.persist(alice);

//            Teacher alice  = em.find(Teacher.class, 1);
//            Course java = new Course("Java");
//            alice.addCourse(java);alice.setLastname("Wonderland");
//
//           em.persist(java);

        }catch (RuntimeException e){
            if (tx.isActive()) tx.rollback();
            throw e;
        }finally {
            em.close();
            emf.close();

        }

    }
}


