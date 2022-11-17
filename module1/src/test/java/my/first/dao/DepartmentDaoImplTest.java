package my.first.dao;

import lombok.SneakyThrows;
import my.first.MysqlJdbcDataSource;
import my.first.model.Department;
import my.first.model.Employee;
import my.first.model.EmployeeDetail;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoImplTest extends BaseDaoTest{

    DepartmentDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new DepartmentDaoImpl(testSessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
        Connection conn = testMysqlJdbcDataSource.getConnection();
        conn.createStatement().executeUpdate("delete from t_department;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void create() {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from t_department;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        Department department = new Department();
        department.setDepartmentName("testDepName");

        //When
        targetObject.create(department);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from t_department;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
//        conn.createStatement().executeUpdate("delete from t_department;");
//        conn.close();
    }

    @Test
    public void findById() {
        //сделать дата сет на список сотрудников
    }

    @Test
    @SneakyThrows
    public void findAllDepartmentNames() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DepartmentDaoImplTest.class.getResourceAsStream("DepartmentDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        List<String> departmentName = targetObject.findAllDepartmentNames();

        //Then
        assertEquals(1, departmentName.size());
        assertEquals("Hidden", departmentName.get(0));
    }

    @Test
    public void delete() {
    }
}