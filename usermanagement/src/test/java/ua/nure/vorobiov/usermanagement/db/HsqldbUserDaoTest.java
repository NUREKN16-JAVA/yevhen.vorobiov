package ua.nure.vorobiov.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;
import ua.nure.vorobiov.usermanagement.User;

import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private UserDao dao;
    private ConnectionFactory connectionFactory;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl();
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(getClass().getClassLoader()
                .getResourceAsStream("usersDataSet.xml"));
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
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