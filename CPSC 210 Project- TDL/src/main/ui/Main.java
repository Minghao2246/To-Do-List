package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ToDoListGUI();
            new ToDoListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the application: file does not exist");
        }
    }
}
