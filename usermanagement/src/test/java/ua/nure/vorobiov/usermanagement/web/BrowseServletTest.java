package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {

    private static final String FIND_ALL_METHOD = "findAll";
    private static final String USERS_ATTRIBUTE = "users";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User testUser = new User(1000L, "John", "Doe", new Date());
        List<User> expectedUsers = Collections.singletonList(testUser);
        getMockUserDao().expectAndReturn(FIND_ALL_METHOD, expectedUsers);
        doGet();
        Collection<User> actualUsers = (Collection<User>) getWebMockObjectFactory()
                .getMockSession()
                .getAttribute(USERS_ATTRIBUTE);
        assertNotNull("Could not find list of users in session", actualUsers);
        assertEquals(expectedUsers, actualUsers);
    }
}
