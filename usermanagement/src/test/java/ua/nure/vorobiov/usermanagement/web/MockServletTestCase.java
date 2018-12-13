package ua.nure.vorobiov.usermanagement.web;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.MockDaoFactory;

import java.util.Properties;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

    private static final String DAO_FACTORY_PROPERTY = "dao.factory";

    private Mock mockUserDao;

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty(DAO_FACTORY_PROPERTY, MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    @Override
    protected void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }
}
