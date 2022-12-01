package my.first.dao;

import lombok.SneakyThrows;
import my.first.model.Department;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest extends BaseDaoTest{

    @Autowired
    DepartmentDao targetObject;

    @Before
    public void setUp() throws Exception {
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