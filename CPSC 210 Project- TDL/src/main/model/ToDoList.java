package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

// Represents a list of tasks to be completed
// the list can only contain MAX_TASKS, 12
public class ToDoList extends JPanel implements Writable {
    private static int COM_TASKS;
    private int initialNum = 0;
    private LinkedList<Task> initialToDoList;
    private JPanel panels;
    private JPanel comPanel;

    //REQUIRES: the list is empty
    //EFFECTS: Make an empty list called initialToDoList, Set the completed task initially to zero
    public ToDoList() {
        initialToDoList = new LinkedList<>();
        COM_TASKS = 0;
        this.createPanels();
    }

    //EFFECTS: get panels
    public JPanel getPanels() {
        return panels;
    }

    //EFFECTS: create panels
    public void createPanels() {
        GridLayout layout = new GridLayout(18, 1);
        layout.setVgap(5);
        layout.setHgap(5);
        makePanels(layout);
        makeComPanel(layout);
    }

    //EFFECTS: make completed panel
    private void makeComPanel(GridLayout layout) {
        comPanel = new JPanel();
        comPanel.setLayout(layout);
        comPanel.setPreferredSize(new Dimension(300, 50));
        comPanel.setBackground(new Color(255, 255, 255));
    }

    //EFFECTS: make current list panel
    private void makePanels(GridLayout layout) {
        panels = new JPanel();
        panels.setBackground(new Color(255, 255, 255));
        panels.setPreferredSize(new Dimension(800, 700));
        panels.setLayout(layout);
    }

    //As a user, I would like to be able to add a task to my to-do list
    //MODIFIES: this
    //EFFECTS: If the list is not full, add the task to the end of the list and return true. otherwise false
    public boolean addTasks(Task task) {
        ++this.initialNum;
        task.setNum(this.initialNum);
        panels.add(task.getTaskBar());
        EventLog.getInstance().logEvent(new Event("Added Task: " + task.getTaskName()));
        return initialToDoList.add(task);
    }

    //As a user, I would like to be able to delete a task from my to-do list
    //REQUIRES: list is not empty
    //MODIFIES: this
    //EFFECTS: if the list contain the taskNum, remove the task that is equal to the taskNum, otherwise do nothing
    public boolean deleteTask(int n) {
        Task t0;
        for (Task t : initialToDoList) {
            if (t.getTaskNum() == n) {
                t0 = t;
                initialToDoList.remove(t0);
                t.getTaskBar().setVisible(false);
                panels.remove(t.getTaskBar());
                EventLog.getInstance().logEvent(new Event("Deleted Task: " + t.getTaskName()));
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the list of all Tasks in the list so far
    public LinkedList<Task> viewList() {
        return initialToDoList;
    }


    //EFFECTS: mark a task as competed and remove from the list
    public boolean compTask(int n) {
        for (Task task : initialToDoList) {
            if (task.getTaskNum() == n) {
                initialToDoList.remove(task);
                task.completed();
                COM_TASKS++;
                task.getTaskBar().setVisible(false);
                this.panels.remove(task.getTaskBar());
                EventLog.getInstance().logEvent(new Event("Completed Task: " + task.getTaskName()));
                return true;
            }
        }
        return false;
    }

    // As a user, I would like to be able to view the list of tasks on my to-do list
    // MODIFIES: this
    //EFFECTS: print out the tasks within the to-do list
    public String taskNames() {
        String names = "";
        int num;
        for (Task task : initialToDoList) {
            num = task.getTaskNum();
            names = names + " " + task.getTaskName()
                    + " " + num;
        }
        return "Your tasks are: " + names;
    }


    //As a user, I want to be able to see the number of completed tasks on my to-do list
    //EFFECTS: to view the number of completed tasks
    public int completeNum() {
        EventLog.getInstance().logEvent(new Event("Reviewed Completed Tasks"));
        return COM_TASKS;
    }

    //EFFECTS: clear the number of completed tasks
    public int clearCompNum() {
        EventLog.getInstance().logEvent(new Event("Cleared All Tasks"));
        return COM_TASKS = 0;
    }

    //MODIFIES: this
    //EFFECTS: check the size of the to-do list
    public int length() {
        return initialToDoList.size();
    }

    //MODIFIES: this
    //EFFECTS: return true if the size of the to do list is empty
    public boolean isEmpty() {
        return initialToDoList.size() == 0;
    }

    //MODIFIES: this
    //EFFECTS: return true if the to-do list contain a specific task
    public boolean doesContain(Task task) {
        return initialToDoList.contains(task);
    }

    //Json, saving/ loading
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : initialToDoList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}




