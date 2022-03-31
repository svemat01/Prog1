package gui.phonebook;

import javax.swing.*;
import java.util.ArrayList;

public class PhoneBook {

//    UI Elements

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

//    Variables

    private ArrayList<Post> contactList;
    private String filter = "";

//    Constructor
    PhoneBook() {

//        Create collection to store Contacts
        contactList = new ArrayList<Post>();

        JFrame frame = new JFrame("Phonebook");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

//        Contact list
        contactListModel = new DefaultListModel();
        contacts.setModel(contactListModel);

//        Button listeners

        addButton.addActionListener(e -> addContact());
        removeButton.addActionListener(e -> removeContact(frame));
        editButton.addActionListener(e -> updateContact(frame));
        searchButton.addActionListener(e -> searchContacts(frame));
        showAllButton.addActionListener(e -> {
//            Reset filter and reload contacts
            filter = "";
            reloadContacts();
        });

        contacts.addListSelectionListener(e -> {
//            Get selected and check if selected
            int index = contacts.getSelectedIndex();
            if (index < 0) return;

//            Set fields to selected contact
            Post person = contactList.get(index);
            nameField.setText(person.getName());
            numberField.setText(person.getNumber());
        });
    }

    public static void main(String[] args) {
        PhoneBook screen = new PhoneBook();
    }

    public void clearFields() {
//        Set fields to empty
        nameField.setText("");
        numberField.setText("");
    }

    public void addContact() {
//        Grab data from fields
        String name = nameField.getText();
        String number = numberField.getText();

//        Create new contact
        Post person = new Post(name, number);

//        Add to list, clear fields and reload
        contactList.add(person);
        reloadContacts();
        clearFields();
    }

    public void removeContact(JFrame frame) {
//        Get selected and remove it then reload
        int index = contacts.getSelectedIndex();

//        index is -1 if nothing is selected
        if (index < 0) {
            JOptionPane.showMessageDialog(frame, "Please select a contact.");
            return;
        }

        contactList.remove(index);

        reloadContacts();
    }

    public void updateContact(JFrame frame) {
//        Get index of selected contact
        int index = contacts.getSelectedIndex();

        if (index < 0) {
            JOptionPane.showMessageDialog(frame, "Please select a contact.");
            return;
        }

//        Grab data from fields and create new contact
        String name = nameField.getText();
        String number = numberField.getText();
        Post person = new Post(name, number);

//        Overwrite old contact with new one
        contactList.set(index, person);
        reloadContacts();
        clearFields();
    }

    public void searchContacts(JFrame frame) {
//        Ask for filter in dialog, set filter and reload
        String query = JOptionPane.showInputDialog(frame, "Search query:");
        filter = query;
        reloadContacts();
    }

    public void reloadContacts() {
//        Print list for debug
        System.out.println(contactList);

//        Clear list and add all contacts that match filter
        contactListModel.removeAllElements();
        int max = contactList.size();
        int shown = 0;

        for (Post contact : contactList) {
            if (contact.getName().startsWith(filter)) {
                contactListModel.addElement(contact);
                shown++;
            }
        }

//        Set bottom text to number of contacts shown
        contactsShownTextArea.setText(String.format("Showing %d/%d contacts", shown, max));

//        fileManager.save(contactList);
    }
}