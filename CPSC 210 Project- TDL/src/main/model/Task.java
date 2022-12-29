package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class Task extends JPanel implements Writable {
    private String taskName;
    private boolean comTasks;
    private int taskNum;
    private JLabel index;
    private JLabel title;
    private JPanel bar;

    //EFFECTS: generate a task with corresponding fields
    public Task(String taskName) {
        this.taskName = taskName;
        comTasks = false;
        this.createBar(taskName, false);
    }


    //EFFECTS: create task bar
    public void createBar(String taskName, boolean comTasks) {
        makeBar();
        makeIndex();
        bar.add(this.index, "West");
        makeTitle(taskName);
        bar.add(this.title, "Center");
    }

    //EFFECTS: create bar
    private void makeBar() {
        bar = new JPanel();
        bar.setPreferredSize(new Dimension(100, 30));
        bar.setLayout(new BorderLayout());
        bar.setBackground(new Color(255, 255, 255));
    }

//    EFFECTS: create index
    private void makeIndex() {
        index = new JLabel();
        index.setPreferredSize(new Dimension(100, 20));
        index.setHorizontalAlignment(0);
    }

    //EFFECTS: create title
    private void makeTitle(String taskName) {
        title = new JLabel(taskName);
        title.setPreferredSize(new Dimension(100, 20));
        title.setHorizontalAlignment(0);
    }

    //EFFECTS: returns true if the task is completed, false otherwise
    public boolean getComp() {
        return comTasks;
    }

    //EFFECTS: returns the task name
    public String getTaskName() {
        return taskName;
    }

    //EFFECTS: return the task number of the task
    public int getTaskNum() {
        return taskNum;
    }

    //EFFECTS: return the task bar
    public JPanel getTaskBar() {
        return bar;
    }

//    EFFECTS: set the task number
    public void setNum(int n) {
        taskNum = n;
        index.setText(String.valueOf(n));
    }

    //MODIFIES: this
    //EFFECTS: task is completed
    public void completed() {
        comTasks = true;
    }


    //Json, saving/ loading
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskName", this.taskName);
        json.put("comTasks", this.comTasks);
        return json;
    }

}



