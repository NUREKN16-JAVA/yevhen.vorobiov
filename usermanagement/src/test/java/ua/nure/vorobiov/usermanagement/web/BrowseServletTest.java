package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {

    private static final String FIND_ALL_METHOD = "findAll";
    private static final String USERS_ATTRIBUTE = "users";
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";
    private static final long TEST_ID = 1000L;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User expectedUser = new User(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, new Date());
        List<User> expectedUsers = Collections.singletonList(expectedUser);
        getMockUserDao().expectAndReturn(FIND_ALL_METHOD, expectedUsers);
        doGet();
        Collection<User> actualUsers = (Collection<User>) getWebMockObjectFactory()
                .getMockSession()
                .getAttribute(USERS_ATTRIBUTE);
        assertNotNull("Could not find list of users in session", actualUsers);
        assertEquals(expectedUsers, actualUsers);
    }

    public void testEdit() {
        User expectedUser = new User(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, new Date());
        getMockUserDao().expectAndReturn("find", TEST_ID, expectedUser);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User actualUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", actualUser);
        assertEquals(expectedUser, actualUser);
    }

    public void testDetails() {
        User expectedUser = new User(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, new Date());
        getMockUserDao().expectAndReturn("find", TEST_ID, expectedUser);
        addRequestParameter("detailsButton", "Details");
        addRequestParameter("id", "1000");
        doPost();
        User actualUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", actualUser);
        assertEquals(expectedUser, actualUser);
    }

    public void testDelete() {
        User expectedUser = new User(TEST_ID, TEST_FIRST_NAME, TEST_LAST_NAME, new Date());
        getMockUserDao().expectAndReturn("find", TEST_ID, expectedUser);
        getMockUserDao().expect("delete", expectedUser);
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
    }

    public void testDeleteWithIncorrectId() {
        String expectedErrorMessage = "Incorrect id";
        getMockUserDao().expectAndThrow("find", TEST_ID, new DatabaseException(expectedErrorMessage));
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", errorMessage);
        assertTrue(errorMessage.contains(errorMessage));
    }
}
