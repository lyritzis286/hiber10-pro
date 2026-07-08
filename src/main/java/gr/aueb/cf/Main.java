package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

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
//            String query5 = "SELECT t FROM Teacher t " +
//                    "JOIN t.courses c WHERE c.title = :title";
//            List<Teacher> teachers5 = em.createQuery(query5, Teacher.class)
//                    .setParameter("title", "Java" )
//                    .getResultList();
//            teachers5.forEach(System.out::println);

            //Select teacher lastname and the count of his courses
//            String query6 = "SELECT t.id, t.lastname, COUNT(c)" +
//                    "FROM Teacher t JOIN t.courses c GROUP BY t.id";
//            List<Object[]> teachers6 = em.createQuery(query6, Object[].class)
//                            .getResultList();
//
//            for(Object[] teacher : teachers6){
//                Long id = (Long)teacher[0];
//                String lastname = (String)teacher[1];
//                Long count = (Long)teacher[2];
//                System.out.println(id + " " + lastname + " " + count);
//            }

            //Select teachers that do not teach any course
//            String query7 = "SELECT t " +
//                    "FROM Teacher t LEFT JOIN t.courses c WHERE c.id IS NULL";
//            List<Teacher> teachers7 = em.createQuery(query7, Teacher.class)
//                    .getResultList();
//            teachers7.forEach(System.out::println);

            //SELECT TEACHERS AND THEIR COURSES
//            String query8 = "SELECT t, c FROM Teacher t " +
//                    "LEFT JOIN FETCH t.courses c";
            //Native query
//            String query9 = "SELECT * FROM teachers";
//            var teachers9 = em.createNativeQuery(query9, Teacher.class).getResultList();
//            teachers9.forEach(System.out::println);


            //Criteria API
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
//            Root<Teacher> teacher = cq.from(Teacher.class);
            // query build
//            List<Teacher> teachers = em.createQuery(cq).getResultList();

            //Select all teachers
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
//            Root<Teacher> t = cq.from(Teacher.class);
//            cq.select(t.get("lastname"));

//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
//            Root<Teacher> t = cq.from(Teacher.class);
//            cq.select(t).where(cb.equal(t.get("lastname"), "Ανδρούτσος"));

            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
            Root<Teacher> t = cq.from(Teacher.class);
            ParameterExpression<String> lastname = cb.parameter(String.class, "lastname");
            cq.select(t).where(cb.equal(t.get("lastname"), lastname));
            List<Teacher> teachers = em.createQuery(cq)
                    .setParameter("lasname", "Ανδρούτσος").getResultList();


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


