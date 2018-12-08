package ua.nure.vorobiov.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;

    private static final String ID = "ID";
    private static final String FIRST_NAME = "Имя";
    private static final String LAST_NAME = "Фамилия";
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

    @Before
    public void setUp() throws Exception {
        super.setUp();
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
        assertEquals(ID,table.getColumnName(0));
        assertEquals(FIRST_NAME,table.getColumnName(1));
        assertEquals(LAST_NAME,table.getColumnName(2));
    }

    @Test
    public void testAddUser() {
        JButton addButton = (JButton) find(JButton.class, ADD_BUTTON);
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
        find(JPanel.class, ADD_PANEL);
        find(JTextField.class, FIRST_NAME_FIELD);
        find(JTextField.class, LAST_NAME_FIELD);
        find(JTextField.class, DATE_OF_BIRTH_FIELD);
        find(JButton.class, OK_BUTTON);
        find(JButton.class, CANCEL_BUTTON);

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
