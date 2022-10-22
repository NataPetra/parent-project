package my.first.dao;

import my.first.model.Department;

public interface DepartmentDao {

    void create(Department department);

    Department findById(long id);

    void update(Department department);

    void delete(Department department);

    void delete(long id);
}
