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
//            String query = "SELECT t FROM Teacher t ";
//            TypedQuery<Teacher> typedQuery =
//                    em.createQuery(query, Teacher.class);
//            List<Teacher> teachers = typedQuery.getResultList();
//            teachers.forEach(System.out::println);
//
//            //Select all courses
//            String query2 = "SELECT c FROM Course c ";
//            List<Course> courses =
//                    em.createQuery(query2, Course.class).getResultList();
//            courses.forEach(System.out::println);

            //Select all courses of a teacher SQL Injection free
//            String query4 = "SELECT c FROM Course c WHERE c.teacher" +
//                    ".lastname = :lastname";
//            List<Course> courses4 =
//                    em.createQuery(query4, Course.class)
//                            .setParameter("lastname", "Καπέτης")
//                            .getResultList();
//            courses4.forEach(System.out::println);

            //Select teacher that teach Java
            String query5 = "SELECT t FROM Teacher t " +
                    "JOIN t.courses c WHERE c.title = :title";
            List<Teacher> teachers5 = em.createQuery(query5, Teacher.class)
                    .setParameter("title", "Java" )
                    .getResultList();
            teachers5.forEach(System.out::println);

            //Select teacher lastname and the count of his courses
            String query6 = "SELECT t.id, t.lastname, COUNT(c)" +
                    "FROM Teacher t JOIN t.courses c GROUP BY t.id";
            List<Object[]> teachers6 = em.createQuery(query6, Object[].class)
                            .getResultList();

            for(Object[] teacher : teachers6){
                Long id = (Long)teacher[0];
                String lastname = (String)teacher[1];
                Long count = (Long)teacher[2];
                System.out.println(id + " " + lastname + " " + count);
            }




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


