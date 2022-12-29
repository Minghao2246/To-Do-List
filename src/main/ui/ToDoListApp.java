package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ToDoListApp {
    private static final String JSON_STORE = "./data/ToDoList.json";
    private ToDoList list;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: make the to-do list application functional
    public ToDoListApp() throws FileNotFoundException {
        init();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runToDoList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runToDoList() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes to-do list
    private void init() {
        list = new ToDoList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> add task");
//        System.out.println("\td -> delete task");
//        System.out.println("\tc -> complete task");
//        System.out.println("\tk -> check tasks");
//        System.out.println("\to -> check completed tasks");
//        System.out.println("\ts -> save todo list to file");
//        System.out.println("\tl -> load todo list from file");
//        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else if (command.equals("d")) {
            doDelete();
        } else if (command.equals("c")) {
            doComplete();
        } else if (command.equals("k")) {
            doCheck();
        } else if (command.equals("o")) {
            doComNum();
        } else if (command.equals("s")) {
            doSaveToDoList();
        } else if (command.equals("l")) {
            doLoadToDoList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add tasks
    private void doAdd() {
        System.out.print("Enter the task that you would like to add:");
        Task task = new Task(this.input.next());
        list.addTasks(task);
        System.out.println("Task successfully added");
    }

    // MODIFIES: this
    // EFFECTS: delete tasks
    private void doDelete() {
        if (list.length() == 0) {
            System.out.println("The list is empty, no task can be removed");
        } else {
            System.out.println("Enter the ID of the task that you completed:");
            list.deleteTask(Integer.parseInt(this.input.next()));
            System.out.println("Task deleted successfully");
        }
    }

    // MODIFIES: this
    // EFFECTS: mark the task as complete
    private void doComplete() {
        if (list.length() == 0) {
            System.out.println("The list is empty, no tasks are there to complete");
        } else {
            System.out.println("Please enter the task ID of the task to complete");
            list.compTask(Integer.parseInt(this.input.next()));
            System.out.println("Good Job!");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the tasks on the to-do list
    private void doCheck() {
        System.out.println(list.taskNames());
    }

    // MODIFIES: this
    // EFFECTS: print out the number of completed tasks
    private void doComNum() {
        int com = list.completeNum();
        if (com == 0) {
            System.out.println("There are not complete tasks!");
        } else {
            System.out.println("\nHere are your completed tasks:" + list.completeNum());
        }
    }

    // EFFECTS: saves the to-do list to file
    private void doSaveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    private void doLoadToDoList() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new ToDoListApp();
    }
}
