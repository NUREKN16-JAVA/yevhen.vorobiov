package ua.nure.vorobiov.usermanagement.gui;

import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BrowsePanel extends JPanel implements ActionListener {

    private static final String BROWSE_PANEL = "browsePanel";
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_BUTTON = "editButton";
    private static final String DELETE_BUTTON = "deleteButton";
    private static final String DETAILS_BUTTON = "detailsButton";
    private static final String USER_TABLE = "userTable";

    private MainFrame parent;
    private JScrollPane tablePanel;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton detailsButton;
    private JTable userTable;

    public BrowsePanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        this.setName(BROWSE_PANEL);
        add(getTablePanel(), BorderLayout.CENTER);
        add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    public JScrollPane getTablePanel() {
        if (Objects.isNull(tablePanel)) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    public JPanel getButtonsPanel() {
        if (Objects.isNull(buttonPanel)) {
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton());
            buttonPanel.add(getEditButton());
            buttonPanel.add(getDeleteButton());
            buttonPanel.add(getDetailsButton());
        }
        return buttonPanel;
    }

    public JButton getAddButton() {
        if (Objects.isNull(addButton)) {
            addButton = new JButton();
            addButton.setText("Добаваить");
            addButton.setName(ADD_BUTTON);
            addButton.addActionListener(this);
        }
        return addButton;
    }

    public JButton getEditButton() {
        if (Objects.isNull(editButton)) {
            editButton = new JButton();
            editButton.setText("Изменить");
            editButton.setName(EDIT_BUTTON);
            editButton.addActionListener(this);
        }
        return editButton;
    }

    public JButton getDeleteButton() {
        if (Objects.isNull(deleteButton)) {
            deleteButton = new JButton();
            deleteButton.setText("Удалить");
            deleteButton.setName(DELETE_BUTTON);
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    public JButton getDetailsButton() {
        if (Objects.isNull(detailsButton)) {
            detailsButton = new JButton();
            detailsButton.setText("Детали");
            detailsButton.setName(DETAILS_BUTTON);
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JTable getUserTable() {
        if (Objects.isNull(userTable)) {
            userTable = new JTable();
            userTable.setName(USER_TABLE);
        }
        return userTable;
    }
}
