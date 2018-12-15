package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {
    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        Date date = new Date();
        User newUser = new User(TEST_FIRST_NAME, TEST_LAST_NAME, date);
        User user = new User(1000L, TEST_FIRST_NAME, TEST_LAST_NAME, date);
        getMockUserDao().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testAddWithEmptyFirstName() {
        Date date = new Date();

        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithEmptyLastName() {
        Date date = new Date();

        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithEmptyDate() {
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithIncorrectDate() {
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("okButton", "Ok");
        addRequestParameter("date", "AAAAAAAAA");

        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }
}
