package ua.nure.vorobiov.usermanagement.gui;

import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;
import ua.nure.vorobiov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddPanel extends JPanel implements ActionListener {

    private static final String ADD_PANEL = "addPanel";
    private static final String OK_BUTTON = "okButton";
    private static final String CANCEL_BUTTON = "cancelButton";
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final String DATE_OF_BIRTH_FIELD = "dateOfBirthField";
    private static final String FIRST_NAME_FIELD = "firstNameField";
    private static final String LAST_NAME_FIELD = "lastNameField";

    private final MainFrame parent;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;

    public AddPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void initialize() {
        setLayout(new BorderLayout());
        this.setName(ADD_PANEL);
        add(getFieldPanel(), BorderLayout.NORTH);
        add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getFieldPanel() {
        if (Objects.isNull(fieldPanel)) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(ROWS, COLS));
            addLabeledField(fieldPanel, Messages.getString("name"), getFirstNameField());
            addLabeledField(fieldPanel, Messages.getString("lastName"), getLastNameField());
            addLabeledField(fieldPanel, Messages.getString("dateOfBirth"), getDateOfBirthField());
        }
        return fieldPanel;
    }

    private void addLabeledField(JPanel fieldPanel, String name, JTextField textField) {
        JLabel label = new JLabel(name);
        label.setLabelFor(textField);
        fieldPanel.add(label);
        fieldPanel.add(textField);
    }

    private JPanel getButtonPanel() {
        if (Objects.isNull(buttonPanel)) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton());
            buttonPanel.add(getCancelButton());
        }
        return buttonPanel;
    }

    private JButton getOkButton() {
        if (Objects.isNull(okButton)) {
            okButton = new JButton();
            okButton.setText(Messages.getString("addButton"));
            okButton.setName(OK_BUTTON);
            okButton.setActionCommand("cancel");
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JButton getCancelButton() {
        if (Objects.isNull(cancelButton)) {
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("cancelButton"));
            cancelButton.setName(CANCEL_BUTTON);
            cancelButton.setActionCommand("ok");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JTextField getFirstNameField() {
        if (Objects.isNull(firstNameField)) {
            firstNameField = new JTextField();
            firstNameField.setName(FIRST_NAME_FIELD);
        }
        return firstNameField;
    }

    private JTextField getLastNameField() {
        if (Objects.isNull(lastNameField)) {
            lastNameField = new JTextField();
            lastNameField.setName(LAST_NAME_FIELD);
        }
        return lastNameField;
    }

    private JTextField getDateOfBirthField() {
        if (Objects.isNull(dateOfBirthField)) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName(DATE_OF_BIRTH_FIELD);
        }
        return dateOfBirthField;
    }
}
