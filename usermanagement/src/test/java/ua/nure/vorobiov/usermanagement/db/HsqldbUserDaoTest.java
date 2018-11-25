package ua.nure.vorobiov.usermanagement.db;

import org.junit.Before;
import org.junit.Test;
import ua.nure.vorobiov.usermanagement.User;

import java.util.Date;

import static org.junit.Assert.*;

public class HsqldbUserDaoTest {

    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private UserDao dao;
    private ConnectionFactory connectionFactory;

    @Before
    public void setUp() {
        connectionFactory = new ConnectionFactoryImpl();
        dao = new HsqldbUserDao(connectionFactory);
    }

    @Test
    public void testCreate() {
        try {
            User testUser = new User();
            testUser.setFirstName(FIRST_NAME);
            testUser.setLastName(LAST_NAME);
            testUser.setDateOfBirth(new Date());

            assertNull(testUser.getId());
            testUser = dao.create(testUser);

            assertNotNull(testUser);
            assertNotNull(testUser.getId());
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }
    }
}