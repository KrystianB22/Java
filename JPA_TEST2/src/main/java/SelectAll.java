import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SelectAll {


    public List<Employee> select_all() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mydata");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(employeeRoot);

        List<Employee> listresult = entityManager.createQuery(query).getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return listresult;
    }
}
