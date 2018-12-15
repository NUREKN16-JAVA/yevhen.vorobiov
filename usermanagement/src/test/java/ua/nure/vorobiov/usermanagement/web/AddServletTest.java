package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {

    private static final String DATE_PATTERN = "dd.mm.yyyy";
    private static final DateFormat FORMATER = new SimpleDateFormat(DATE_PATTERN);

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

        addRequestParameter(FIRST_NAME_ATTRIBUTE, TEST_FIRST_NAME);
        addRequestParameter(LAST_NAME_ATTRIBUTE, TEST_LAST_NAME);
        addRequestParameter(DATE_ATTRIBUTE, FORMATER.format(date));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
    }

    public void testAddWithEmptyFirstName() {
        Date date = new Date();

        addRequestParameter(LAST_NAME_ATTRIBUTE, TEST_LAST_NAME);
        addRequestParameter(DATE_ATTRIBUTE, DateFormat.getDateInstance().format(date));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithEmptyLastName() {
        Date date = new Date();

        addRequestParameter(FIRST_NAME_ATTRIBUTE, TEST_FIRST_NAME);
        addRequestParameter(DATE_ATTRIBUTE, DateFormat.getDateInstance().format(date));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithEmptyDate() {
        addRequestParameter(FIRST_NAME_ATTRIBUTE, TEST_FIRST_NAME);
        addRequestParameter(LAST_NAME_ATTRIBUTE, TEST_LAST_NAME);
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testAddWithIncorrectDate() {
        addRequestParameter(FIRST_NAME_ATTRIBUTE, TEST_FIRST_NAME);
        addRequestParameter(LAST_NAME_ATTRIBUTE, TEST_LAST_NAME);
        addRequestParameter(OK_BUTTON, "Ok");
        addRequestParameter(DATE_ATTRIBUTE, "AAAAAAAAA");

        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }
}
