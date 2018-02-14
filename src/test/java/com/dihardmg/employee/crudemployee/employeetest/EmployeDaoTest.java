package com.dihardmg.employee.crudemployee.employeetest;

import com.dihardmg.employee.crudemployee.dao.EmployeeDao;
import com.dihardmg.employee.crudemployee.entity.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author : Otorus
 * @since : 2/14/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/employee.sql"
)
public class EmployeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DataSource dataSource;


    @After
    public void hapusData() throws Exception {
        String sql = "delete from employee where email = 'naruto@gmail.com'";
        try (Connection c = dataSource.getConnection()) {
            c.createStatement().executeUpdate(sql);
        }
    }


    @Test
    public void testInsert() throws SQLException {
        Employee employee = new Employee();

        employee.setEmail("naruto@gmail.com");
        employee.setNama("naruto");
        employee.setId("aa1");
        employeeDao.save(employee);

        String sql = "select count(*) as jumlah "
                + "from employee "
                + "where email = 'naruto@gmail.com'";

        try (Connection c = dataSource.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }

    }

    @Test
    public void testHitung() {
        Long jumlah = employeeDao.count();
        Assert.assertEquals(3L, jumlah.longValue());
    }

    @Test
    public void testCariById() {
        Employee employee = employeeDao.findOne("aa1");
        Assert.assertNotNull(employee);
        Assert.assertEquals("naruto", employee.getNama());
        Assert.assertEquals("naruto@gmail.com", employee.getEmail());

        Employee px = employeeDao.findOne("jgjgjgj");
        Assert.assertNull(px);
    }

}
