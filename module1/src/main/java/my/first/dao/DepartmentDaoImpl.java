package my.first.dao;

import my.first.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Department department) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(department);
    }

    @Override
    public Department findById(long id) {
        return sessionFactory.getCurrentSession()
                .get(Department.class, id);
    }

    @Override
    public List<String> findAllDepartmentNames() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d.departmentName FROM Department AS d", String.class)
                .list();
    }

    @Override
    public void update(Department department) {
    }

    @Override
    public void delete(Department department) {
        sessionFactory.getCurrentSession()
                .delete(department);
    }

    @Override
    public void delete(long id) {
        delete(sessionFactory.getCurrentSession()
                .load(Department.class, id));
    }

    @Override
    public List<Department> findAll() {
        String query = "FROM Department";
        return sessionFactory.getCurrentSession().createQuery(query, Department.class).list();
    }
}
