package ua.nure.vorobiov.usermanagement.gui.main;

import ua.nure.vorobiov.usermanagement.db.DaoFactory;
import ua.nure.vorobiov.usermanagement.db.UserDao;
import ua.nure.vorobiov.usermanagement.gui.AddPanel;
import ua.nure.vorobiov.usermanagement.gui.BrowsePanel;
import ua.nure.vorobiov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;
    private UserDao userDao;

    public MainFrame() throws HeadlessException {
        super();
        userDao = DaoFactory.getInstance().getUserDao();
        initialize();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("userManagement"));
        this.setContentPane(getContentPanel());
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private JPanel getContentPanel() {
        if (Objects.isNull(contentPanel)) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (Objects.isNull(browsePanel)) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    private AddPanel getAddPanel() {
        if (Objects.isNull(addPanel)) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }
}
