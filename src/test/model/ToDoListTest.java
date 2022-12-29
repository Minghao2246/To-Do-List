package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private ToDoList testList;
    private Task testTask1;
    private Task testTask2;
    private Task testTask3;
    private Task testTask4;
    private Task testTask5;
    private Task testTask6;
    private Task testTask7;
    private Task testTask8;
    private Task testTask9;
    private Task testTask10;
    private Task testTask11;
    private Task testTask12;

    @BeforeEach
    public void Setup() {
        testList = new ToDoList();
        //testList1 = new ToDoList();
        testTask1 = new Task("Homework");
        testTask2 = new Task("Brush Teeth");
        testTask3 = new Task("Reading");
        testTask4 = new Task("Make food");
        testTask5 = new Task("Drawing");
        testTask6 = new Task("Shower");
        testTask7 = new Task("Watch lecture");
        testTask8 = new Task("Clean room");
        testTask9 = new Task("Wash dishes");
        testTask10 = new Task("Do quiz");
        testTask11 = new Task("Make bed");
        testTask12 = new Task("Sleep");
    }

    @Test
    void testAdd() {
        assertEquals(testList.length(), 0);
        assertTrue(testList.isEmpty());
        assertTrue(testList.addTasks(testTask1));
        assertFalse(testList.isEmpty());
        assertEquals(testList.length(), 1);
        assertTrue(testList.addTasks(testTask2));
        assertTrue(testList.addTasks(testTask12));
        assertEquals(testList.length(), 3);
        for (Task task : Arrays.asList(testTask3, testTask4, testTask5, testTask6,
                testTask7, testTask8, testTask9, testTask10, testTask11)) {
            assertTrue(testList.addTasks(task));
        }
        assertTrue(testList.addTasks(testTask1));
        assertEquals(testList.length(), 13);
        assertFalse(testList.isEmpty());
    }

    @Test
    void testDelete() {
        for (Task task : Arrays.asList(testTask1, testTask2, testTask3, testTask4, testTask5, testTask6,
                testTask7, testTask8, testTask9, testTask10, testTask11, testTask12)) {
            assertTrue(testList.addTasks(task));
            assertFalse(testList.deleteTask(13));
        }
        assertEquals(testList.length(), 12);
        assertFalse(testList.deleteTask(-1));
        assertTrue(testList.deleteTask(1));
        assertEquals(testList.length(), 11);
        assertFalse(testList.doesContain(testTask1));
        assertTrue(testList.doesContain(testTask3));
        //assertTrue(testList.deleteTask(2));
        assertTrue(testList.deleteTask(3));
        assertTrue(testList.deleteTask(4));
        assertTrue(testList.deleteTask(5));
        assertTrue(testList.deleteTask(6));
        assertTrue(testList.deleteTask(7));
        assertTrue(testList.deleteTask(8));
        assertTrue(testList.deleteTask(9));
        assertFalse(testList.doesContain(testTask9));
        assertTrue(testList.deleteTask(10));
        assertTrue(testList.deleteTask(11));
        assertTrue(testList.deleteTask(12));
        //assertTrue(testList.isEmpty());
        try {
            assertTrue(testList.deleteTask(2));
            JPanel taskPanel = this.testList.getPanels();
            LinkedList<Task> expected = this.testList.viewList();
            Assertions.assertEquals(0, expected.size());
            Assertions.assertFalse(expected.contains(testTask2));
        } catch (Exception e) {
            Assertions.fail("Unable to find the task to delete");
        }
    }


    @Test
    void testMarkTask() {
        assertFalse(testList.compTask(1));
        assertEquals(testList.completeNum(), 0);
        for (Task task : Arrays.asList(testTask1, testTask2, testTask3, testTask4, testTask5, testTask6,
                testTask7, testTask8, testTask9, testTask10, testTask11, testTask12)) {
            assertTrue(testList.addTasks(task));
            assertFalse(testList.compTask(9999));
        }
        assertEquals(testList.compTask(1), testTask1.getComp());
        assertEquals(testList.completeNum(), 1);
        assertTrue(testTask1.getComp());
        assertEquals(testList.compTask(5), testTask5.getComp());
        assertEquals(testList.compTask(12), testTask12.getComp());
        assertFalse(testTask7.getComp());
        assertEquals(testList.compTask(7), testTask7.getComp());
        assertTrue(testTask7.getComp());
        assertEquals(testList.completeNum(), 4);
        assertFalse(testTask8.getComp());
        assertFalse(testTask9.getComp());
        assertEquals(testList.compTask(9), testTask9.getComp());
        assertTrue(testTask9.getComp());
    }

    @Test
    void testComplete() {
        assertEquals(testList.completeNum(), 0);
        for (Task task : Arrays.asList(testTask1, testTask2, testTask3, testTask4, testTask5, testTask6,
                testTask7, testTask8, testTask9, testTask10, testTask11, testTask12)) {
            assertTrue(testList.addTasks(task));
            assertFalse(testList.compTask(9999));
        }
        assertEquals(testList.compTask(2), testTask2.getComp());
        assertEquals(testList.compTask(1), testTask1.getComp());
        assertEquals(testList.completeNum(), 2);
        assertEquals(testList.compTask(5), testTask5.getComp());
        assertEquals(testList.compTask(12), testTask12.getComp());
        assertEquals(testList.completeNum(), 4);
        assertEquals(testList.clearCompNum(),0);
    }

    @Test
    void testTaskName() {
        assertEquals(testList.taskNames(), "Your tasks are: ");
        assertTrue(testList.addTasks(testTask1));
        assertEquals(testList.taskNames(), "Your tasks are:  Homework 1");
    }


}
