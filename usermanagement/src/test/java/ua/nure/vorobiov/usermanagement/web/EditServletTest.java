package ua.nure.vorobiov.usermanagement.web;

import ua.nure.vorobiov.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {

    private static final String TEST_FIRST_NAME = "John";
    private static final String TEST_LAST_NAME = "Doe";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit() {
        Date date = new Date();
        User user = new User(1000L, TEST_FIRST_NAME, TEST_LAST_NAME, date);
        getMockUserDao().expect("update", user);

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testEditWithEmptyFirstName() {
        Date date = new Date();

        addRequestParameter("id", "1000");
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testEditWithEmptyLastName() {
        Date date = new Date();

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testEditWithEmptyDate() {
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

    public void testEditWithIncorrectDate() {
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", TEST_FIRST_NAME);
        addRequestParameter("lastName", TEST_LAST_NAME);
        addRequestParameter("okButton", "Ok");
        addRequestParameter("date", "AAAAAAAAA");

        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not ind error message", errorMessage);
    }

}
