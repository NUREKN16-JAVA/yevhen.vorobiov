package ua.nure.vorobiov.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.After;
import org.junit.Before;
import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;

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

    public void testBrowseControls() {
        find(JPanel.class, BROWSE_PANEL);
        find(JTable.class, USER_TABLE);
        find(JButton.class, ADD_BUTTON);
        find(JButton.class, EDIT_BUTTON);
        find(JButton.class, DELETE_BUTTON);
        find(JButton.class, DETAILS_BUTTON);
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
