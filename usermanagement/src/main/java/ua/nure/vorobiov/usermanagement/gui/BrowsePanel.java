package ua.nure.vorobiov.usermanagement.gui;

import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;
import ua.nure.vorobiov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
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

    private JScrollPane getTablePanel() {
        if (Objects.isNull(tablePanel)) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JPanel getButtonsPanel() {
        if (Objects.isNull(buttonPanel)) {
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton());
            buttonPanel.add(getEditButton());
            buttonPanel.add(getDeleteButton());
            buttonPanel.add(getDetailsButton());
        }
        return buttonPanel;
    }

    private JButton getAddButton() {
        if (Objects.isNull(addButton)) {
            addButton = new JButton();
            addButton.setText(Messages.getString("BrowsePanel.addButton"));
            addButton.setName(ADD_BUTTON);
            addButton.setActionCommand("add");
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JButton getEditButton() {
        if (Objects.isNull(editButton)) {
            editButton = new JButton();
            editButton.setText(Messages.getString("editButton"));
            editButton.setName(EDIT_BUTTON);
            editButton.setActionCommand("edit");
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getDeleteButton() {
        if (Objects.isNull(deleteButton)) {
            deleteButton = new JButton();
            deleteButton.setText(Messages.getString("deleteButton"));
            deleteButton.setName(DELETE_BUTTON);
            deleteButton.setActionCommand("delete");
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getDetailsButton() {
        if (Objects.isNull(detailsButton)) {
            detailsButton = new JButton();
            detailsButton.setText(Messages.getString("detailsButton"));
            detailsButton.setName(DETAILS_BUTTON);
            detailsButton.setActionCommand("details");
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("add".equalsIgnoreCase(actionCommand)) {
            this.setVisible(false);
            parent.showAddPanel();
        }
    }

    private JTable getUserTable() {
        if (Objects.isNull(userTable)) {
            userTable = new JTable();
            userTable.setName(USER_TABLE);
            UserTableModel model = new UserTableModel(Collections.emptyList());
            userTable.setModel(model);
        }
        return userTable;
    }
}
