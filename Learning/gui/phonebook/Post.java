package gui.phonebook;

public class Post {
    private String name;
    private String number;

    public Post (String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName () {
        return name;
    }

    public String getNumber () {
        return number;
    }

    public String toString () {
        return name + ": " + number;
    }
}
