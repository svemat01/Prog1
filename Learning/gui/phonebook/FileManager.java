package gui.phonebook;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(ArrayList<Post> contacts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);
            objectOutputStream.writeObject(contacts);
            objectOutputStream.close();
            objectOutputStream.flush();
        } catch(IOException e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Failed to write to file");
        }
    }

    public ArrayList<Post> load() {
        File file = new File(fileName);

        if(file.exists()) {
            ArrayList<Post> contacts;
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                contacts = (ArrayList<Post>)objectInputStream.readObject();
                objectInputStream.close();
            } catch(IOException e ) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Failed to read from file");

                return new ArrayList<Post>();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Failed to read from file");

                return new ArrayList<Post>();
            }
            return contacts;
        }

        System.out.println("File does not exist");
        return new ArrayList<Post>();
    }
}
