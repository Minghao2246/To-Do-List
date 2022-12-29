
package ui.panels;

import ui.ToDoListGUI;

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel {
    JButton add;
    JButton delete;
    JButton comp;
    JButton checkComp;
    JButton clearComp;

    //EFFECTS: make the menu panel
    public MenuPanel() {
        setBackground(new Color(169, 168, 168));
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(5, 85));
        addButton();
        deleteButton();
        completeButton();
        checkButton();
        clearButton();
    }

    //EFFECTS: make clear button
    private void clearButton() {
        clearComp = new JButton("Clear # of Completed Tasks");
        clearComp.setOpaque(true);
        clearComp.setBackground(new Color(0, 0, 0));
        clearComp.setForeground(new Color(142, 123, 204, 194));
        clearComp.setActionCommand("Clear");
        add(this.clearComp);
    }

    //EFFECTS: make check button
    private void checkButton() {
        checkComp = new JButton("Check # of Completed Tasks");
        checkComp.setOpaque(true);
        checkComp.setBackground(new Color(0, 0, 0));
        checkComp.setForeground(new Color(255, 128, 0));
        checkComp.setActionCommand("Check");
        add(this.checkComp);
    }

    //EFFECTS: make complete button
    private void completeButton() {
        comp = new JButton("Complete Task");
        comp.setOpaque(true);
        comp.setBackground(new Color(0, 0, 0));
        comp.setForeground(new Color(0, 204, 0));
        comp.setActionCommand("Complete");
        add(this.comp);
    }

    //EFFECTS: make delete button
    private void deleteButton() {
        delete = new JButton("Delete Task");
        delete.setOpaque(true);
        delete.setBackground(new Color(0, 0, 0));
        delete.setForeground(new Color(204, 0, 0));
        delete.setActionCommand("Delete");
        add(this.delete);
    }

    //EFFECTS: make add button
    private void addButton() {
        add = new JButton("Add Task");
        add.setOpaque(true);
        add.setBackground(new Color(0, 0, 0));
        add.setForeground(new Color(0, 128, 255));
        add.setActionCommand("Add");
        add(this.add);
    }

    //EFFECTS: get the Add button
    public JButton getAdd() {
        return add;
    }

    //EFFECTS: get the Delete button
    public JButton getDelete() {
        return delete;
    }

    //EFFECTS: get the Complete button
    public JButton getComp() {
        return comp;
    }

    //EFFECTS: get the check # of completed tasks button
    public JButton getCheckComp() {
        return checkComp;
    }

    //EFFECTS: get the clear # of completed tasks button
    public JButton getClearComp() {
        return clearComp;
    }

}
