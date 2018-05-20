import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class find_name {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(find_name.class);

    public List<Employee> findbyname(String name) {

        EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("mydata");
        EntityManager entityManager1 = entityManagerFactory1.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager1.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);// do jakiej klasy sie odwołać
        Root<Employee> employeeRoot = query.from(Employee.class);// z jakiej klasy

        query.select(employeeRoot).where(employeeRoot.get("name").in(name));
        List<Employee> listresult = entityManager1.createQuery(query).getResultList();
        entityManager1.close();
        entityManagerFactory1.close();

        log.info("Querry ok !jk");
        return listresult;


    }
}
