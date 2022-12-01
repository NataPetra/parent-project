package my.first.dao;

import lombok.SneakyThrows;
import my.first.model.Meeting;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MeetingDaoImplTest extends BaseDaoTest{

    @Autowired
    MeetingDao targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @SneakyThrows
    public void findAll() {
        //Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("MeetingDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        //When
        List<Meeting> meetingList = targetObject.findAll();

        //Then
        assertEquals(2, meetingList.size());
        for(Meeting meeting: meetingList) {
            assertTrue(meeting.getEmployees().size() > 0);
        }
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }
}