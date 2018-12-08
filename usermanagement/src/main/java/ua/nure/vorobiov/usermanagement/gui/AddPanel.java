package ua.nure.vorobiov.usermanagement.gui;

import ua.nure.vorobiov.usermanagement.User;
import ua.nure.vorobiov.usermanagement.db.DatabaseException;
import ua.nure.vorobiov.usermanagement.gui.main.MainFrame;
import ua.nure.vorobiov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
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
    private static final Color backColor = Color.WHITE;

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
        if ("ok".equalsIgnoreCase(e.getActionCommand())) {
            User user = new User();
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            try {
                parent.getUserDao().create(user);
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        clearFields();
        setVisible(false);
        parent.showBrowsePanel();
    }

    private void clearFields() {
        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(backColor);

        getFirstNameField().setText("");
        getFirstNameField().setBackground(backColor);

        getLastNameField().setText("");
        getLastNameField().setBackground(backColor);
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
