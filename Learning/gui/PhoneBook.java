package gui;

import javax.swing.*;
import java.util.ArrayList;

public class PhoneBook {
    private JPanel panel1;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JButton showAllButton;
    private JButton searchButton;
    private JList contacts;
    private JTextField numberField;
    private JTextField nameField;
    private JTextArea contactsShownTextArea;

    private DefaultListModel contactListModel;

    private ArrayList<String> contactList = new ArrayList<String>();
    private String filter = "";

    PhoneBook() {

        JFrame frame = new JFrame("Phonebook");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        contactListModel = new DefaultListModel();
        contacts.setModel(contactListModel);

        addButton.addActionListener(e -> addContact());
        removeButton.addActionListener(e -> removeContact());
        editButton.addActionListener(e -> updateContact());
        searchButton.addActionListener(e -> searchContacts(frame));
        showAllButton.addActionListener(e -> {
            filter = "";
            reloadContacts();
        });
    }

    public static void main(String[] args) {
        PhoneBook screen = new PhoneBook();
    }

    public void clearFields() {
        nameField.setText("");
        numberField.setText("");
    }

    public void addContact() {
        String name = nameField.getText();
        String number = numberField.getText();

        String result = String.format("%s | %s",name,number);

        contactList.add(result);
        reloadContacts();
        clearFields();
    }

    public void removeContact() {
        int index = contacts.getSelectedIndex();
        contactList.remove(index);

        reloadContacts();
    }

    public void updateContact() {
        int index = contacts.getSelectedIndex();
        String name = nameField.getText();
        String number = numberField.getText();
        String result = String.format("%s | %s",name,number);

        contactList.set(index, result);
        reloadContacts();
        clearFields();
    }

    public void searchContacts(JFrame frame) {
        String query = JOptionPane.showInputDialog(frame, "Search query:");
        filter = query;
        reloadContacts();
    }

    public void reloadContacts() {
        System.out.println(contactList);
        contactListModel.removeAllElements();
        int max = contactList.size();
        int shown = 0;

        for (String contact : contactList) {
            if (contact.startsWith(filter)) {
                contactListModel.addElement(contact);
                shown++;
            }
        }

        contactsShownTextArea.setText(String.format("Showing %d/%d contacts", shown, max));
    }
}
