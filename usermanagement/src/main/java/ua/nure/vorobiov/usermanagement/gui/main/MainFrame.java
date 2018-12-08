package ua.nure.vorobiov.usermanagement.gui.main;

import ua.nure.vorobiov.usermanagement.gui.BrowsePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private JPanel contentPanel;
    private JPanel browsePanel;

    public MainFrame() throws HeadlessException {
        super();
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Управление пользователями");
        this.setContentPane(getContentPanel());
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public JPanel getContentPanel() {
        if (Objects.isNull(contentPanel)) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    public JPanel getBrowsePanel() {
        if (Objects.isNull(browsePanel)) {
            browsePanel = new BrowsePanel(this);
        }
        return browsePanel;
    }
}
