package my.first.dao;

import my.first.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePhotoDao {

    void create(Department department);

}
