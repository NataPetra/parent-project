package my.first.dao;

import my.first.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Department department) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()){
            tx = sess.beginTransaction();
            sess.saveOrUpdate(department);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }

    }

    @Override
    public Department findById(long id) {
        return sessionFactory.openSession().get(Department.class, id);
    }

    @Override
    public List<String> findAllDepartmentNames() {
        try (Session session = sessionFactory.openSession()) {
            String query = "SELECT d.departmentName FROM Department AS d";
            return session.createQuery(query, String.class).list();
        }
    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void delete(Department department) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.delete(department);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(long id) {
        Department department = sessionFactory.openSession()
                .load(Department.class, id);
        delete(department);
    }
}
