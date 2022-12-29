package ui;

import model.Event;
import model.EventLog;
import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JPanel;
import java.net.MalformedURLException;
import java.net.URL;


public class ToDoListGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/ToDoList.json";
    private static int WIDTH = 800;
    private static int HEIGHT = 1000;
    private ImageIcon image;
    private ToDoList todolist;
    private JTextField field;
    private JPanel cpanel;
    private JPanel panels;
    private JLabel nameOfTask;
    private JLabel numberOfTask;
    private JLabel instruction;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: allows user to interact and provide visualization
    public ToDoListGUI() {
        super("My To-Do List!");
        initField();
        initGraphics();
    }

    //EFFECTS: set current panel
    public void setCpanel(JPanel list) {
        panels = list;
    }

    //EFFECTS: returns the todolist
    public ToDoList getTodolist() {
        return todolist;
    }

    //EFFECTS: initialize the fields
    public void initField() {
        todolist = new ToDoList();
        jsonWriter = new JsonWriter("./data/ToDoList.json");
        jsonReader = new JsonReader("./data/ToDoList.json");
        image = new ImageIcon("./data/todolist.icon.png");
    }

    //EFFECTS: allow user to load tasks in the applet
    private void loadTasks(int i) {
        if (i == 0) {
            try {
                todolist = jsonReader.read();
                todolist.getPanels();
                todolist.setVisible(true);
                add(todolist.getPanels(), "Center");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //EFFECTS: allow user to save tasks in the applet
    private void saveTasks(int i) {
        if (i == 0) {
            try {
                jsonWriter.open();
                jsonWriter.write(this.getTodolist());
                jsonWriter.close();
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (i == 1) {
            for (Event event : EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
    }


    //EFFECTS: pops up when opening up the applet and ask if user wants to load
    private int createOpen() {
        return JOptionPane.showConfirmDialog(this, "Ready to load your list?", "Load",
                1);
    }

    //EFFECTS: pops up when closing the applet and ask if user wants to save
    private int createClose() {
        return JOptionPane.showConfirmDialog(this, "Ready to save your list?", "Save",
                0);
    }

    //EFFECTS: initialize the graphics
    public void initGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setIconImage(image.getImage());
        setLayout(new BorderLayout());
        splashWindow();
        windows();
        repaint();
        makeMenu();
        makeInsType();
        makeList();
        setVisible(true);
        checkWindow();
        setVisible(true);
    }

    //EFFECTS: make opening and closing pop-up windows to save and load
    private void windows() {
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                int n = ToDoListGUI.this.createOpen();
                ToDoListGUI.this.loadTasks(n);
                ToDoListGUI.this.revalidate();
            }

            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int n = ToDoListGUI.this.createClose();
                ToDoListGUI.this.saveTasks(n);
                ToDoListGUI.this.revalidate();
            }
        });
    }

    //EFFECTS: display cool gif when run
    private void splashWindow() {
        JWindow window = new JWindow();
        try {
            window.getContentPane().add(
                    new JLabel("MY TO-DO LIST", new ImageIcon(
                            new URL("https://i.pinimg.com/originals/7c/af/a3/7cafa33a607df89cc065d516a8c9f1df.gif")), SwingConstants.CENTER));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        window.setBounds(500, 150, 630, 500);
        window.setVisible(true);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(true);
        window.dispose();
    }

    //EFFECTS: check the number of completed task when button is pressed
    private void checkWindow() {
        JFrame checkFrame = new JFrame();
        checkFrame.getContentPane().add(
                new JLabel("Here are your completed tasks:" + todolist.completeNum(), SwingConstants.CENTER));
        checkFrame.setBounds(500, 150, 300, 200);
        checkFrame.setVisible(true);
    }

    //EFFECTS: make the typing area
    private void makeInsType() {
        InstructionPanel ins = new InstructionPanel();
        instruction = new JLabel("Please enter the name of the task you would like to add or "
                + "the # of the task you would like to delete/complete");
        instruction.setFont(new Font("Verdana", 1, 12));
        ins.add(instruction);
        TypePanel type = new TypePanel();
        field = type.getTextField();
        GridLayout gridLayout3 = new GridLayout(2, 0);
        JPanel insTypePanel = new JPanel(gridLayout3);
        insTypePanel.add(ins);
        insTypePanel.add(type);
        add(insTypePanel, "South");
    }

    //EFFECTS: make the list showing
    private void makeList() {
        panels = todolist.getPanels();
        setCpanel(panels);
        add(this.panels, "Center");
    }


    //EFFECTS: make the menu panel
    private void makeMenu() {
        JPanel nameIDPanel = nameIDPanel();
        MenuPanel main = new MenuPanel();
        JButton add = main.getAdd();
        JButton delete = main.getDelete();
        JButton complete = main.getComp();
        JButton checkComp = main.getCheckComp();
        JButton clearComp = main.getClearComp();
        add.addActionListener(this);
        delete.addActionListener(this);
        complete.addActionListener(this);
        checkComp.addActionListener(this);
        clearComp.addActionListener(this);
        GridLayout gridLayout2 = new GridLayout(2, 0);
        JPanel middlePanel = new JPanel(gridLayout2);
        middlePanel.add(main);
        middlePanel.add(nameIDPanel);
        add(middlePanel, "First");
    }

    //EFFECTS: adding the name and number panels
    private JPanel nameIDPanel() {
        TaskNumPanel id = new TaskNumPanel();
        numberOfTask = new JLabel("Task Number");
        numberOfTask.setFont(new Font("Verdana", 1, 20));
        id.add(numberOfTask);
        TaskNamePanel name = new TaskNamePanel();
        nameOfTask = new JLabel("Task Name");
        nameOfTask.setFont(new Font("Verdana", 1, 20));
        name.add(nameOfTask);
        GridLayout gridLayout1 = new GridLayout(0, 2);
        gridLayout1.setHgap(10);
        JPanel nameIDPanel = new JPanel(gridLayout1);
        nameIDPanel.add(id);
        nameIDPanel.add(name);
        return nameIDPanel;
    }

    //EFFECTS: allows users to do certain actions
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Complete") && field.getText().length() > 0) {
            completeFunction();
        } else if (e.getActionCommand().equals("Add") && field.getText().length() > 0) {
            addFunction();
        } else if (e.getActionCommand().equals("Delete") && field.getText().length() > 0) {
            deleteFunction();
        } else if (e.getActionCommand().equals("Clear")) {
            todolist.clearCompNum();
        } else if (e.getActionCommand().equals("Check")) {
            checkWindow();
        }
    }

    //EFFECT: action performed: to delete the task
    private void deleteFunction() {
        try {
            int i = Integer.parseInt(field.getText());
            todolist.deleteTask(i);
            revalidate();
        } catch (Exception e1) {
            field.setText("Please try again!");
        }
    }

    //EFFECT: action performed: to complete the task
    private void completeFunction() {
        try {
            int n = Integer.parseInt(field.getText());
            todolist.compTask(n);
            revalidate();
        } catch (Exception e2) {
            field.setText("Please try again!");
        }
    }

    //EFFECT: action performed: to add the task
    private void addFunction() {
        Task task = new Task(field.getText());
        todolist.addTasks(task);
        revalidate();
    }
}

