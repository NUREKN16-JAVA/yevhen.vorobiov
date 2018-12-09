package ua.nure.vorobiov.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.DaoFactoryImpl;
import ua.nure.vorobiov.usermanagement.db.MockUserDao;
import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;
import ua.nure.vorobiov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;

    private static final String FULL_NAME_FIELD = "fullNameField";
    private static final String TEST_FULL_NAME = "Ivanov, Ivan";
    private static final String TEST_FIRST_NAME = "Ivan";
    private static final String TEST_LAST_NAME = "Ivanov";
    private static final String DATE_PATTERN = "dd.mm.yyyy";
    private static final String DAO_IMPL_PROPERTY = "dao.ua.nure.vorobiov.usermanagement.db.UserDao";
    private static final String DAO_FACTORY_PROPERTY = "dao.factory";
    private static final String ID = Messages.getString("id");
    private static final String FIRST_NAME = Messages.getString("name");
    private static final String LAST_NAME = Messages.getString("lastName");
    private static final String ADD_PANEL = "addPanel";
    private static final String FIRST_NAME_FIELD = "firstNameField";
    private static final String LAST_NAME_FIELD = "lastNameField";
    private static final String DATE_OF_BIRTH_FIELD = "dateOfBirthField";
    private static final String OK_BUTTON = "okButton";
    private static final String CANCEL_BUTTON = "cancelButton";
    private static final String BROWSE_PANEL = "browsePanel";
    private static final String USER_TABLE = "userTable";
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_BUTTON = "editButton";
    private static final String DELETE_BUTTON = "deleteButton";
    private static final String DETAILS_BUTTON = "detailsButton";
    private static final String DETAILS_PANEL = "detailsPanel";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty(DAO_IMPL_PROPERTY, MockUserDao.class.getName());
        properties.setProperty(DAO_FACTORY_PROPERTY, DaoFactoryImpl.class.getName());
        DaoFactory.init(properties);
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    @Test
    public void testBrowseControls() {
        find(JPanel.class, BROWSE_PANEL);
        find(JTable.class, USER_TABLE);
        find(JButton.class, ADD_BUTTON);
        find(JButton.class, EDIT_BUTTON);
        find(JButton.class, DELETE_BUTTON);
        find(JButton.class, DETAILS_BUTTON);

        JTable table = (JTable) find(JTable.class, USER_TABLE);
        assertEquals(3, table.getColumnCount());
        assertEquals(ID, table.getColumnName(0));
        assertEquals(FIRST_NAME, table.getColumnName(1));
        assertEquals(LAST_NAME, table.getColumnName(2));
    }

    @Test
    public void testAddUser() {
        int expectedRowsCountBefore = 0;
        int expectedRowsCountAfter = 1;
        String testFirstName = "Mike";
        String testLastName = "Mikovich";
        DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        String testDate = format.format(new Date());

        JTable table = (JTable) find(JTable.class, USER_TABLE);
        assertEquals(expectedRowsCountBefore, table.getRowCount());

        JButton addButton = (JButton) find(JButton.class, ADD_BUTTON);
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
        find(JPanel.class, ADD_PANEL);

        JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD);
        JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD);
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD);
        JButton okButton = (JButton) find(JButton.class, OK_BUTTON);
        find(JButton.class, CANCEL_BUTTON);

        getHelper().sendString(new StringEventData(this, firstNameField, testFirstName));
        getHelper().sendString(new StringEventData(this, lastNameField, testLastName));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, testDate));
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, BROWSE_PANEL);
        table = (JTable) find(JTable.class, USER_TABLE);
        assertEquals(expectedRowsCountAfter, table.getRowCount());
    }

    @Test
    public void testGetUserDetails() {
        int rowIndex = 1;
        int columnIndex = 0;
        int numberOfClicks = 1;

        JTable table = (JTable) find(JTable.class, USER_TABLE);
        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, rowIndex, columnIndex, numberOfClicks));

        JButton detailsButton = (JButton) find(JButton.class, DETAILS_BUTTON);
        getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));
        find(JPanel.class, DETAILS_PANEL);

        JTextField fullNameField = (JTextField) find(JTextField.class, FULL_NAME_FIELD);
        JButton closeButton = (JButton) find(JButton.class, "closeButton");
        String actualFullName = fullNameField.getText();

        getHelper().enterClickAndLeave(new MouseEventData(this, closeButton));
        find(JPanel.class, BROWSE_PANEL);

        assertEquals(TEST_FULL_NAME, actualFullName);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        mainFrame.setVisible(false);
        TestHelper.cleanUp(this);
    }

    private Component find(Class componentClass, String name) {
        NamedComponentFinder finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component ‘" + name + "’", component);
        return component;
    }
}
