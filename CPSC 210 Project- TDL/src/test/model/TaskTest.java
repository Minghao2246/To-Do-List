package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

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
    void setUp() {
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

//    @Test
//    void getTaskNum() {
//        assertEquals(testTask5.getUrgentLevel(), 5);
//        assertEquals(testTask2.getUrgentLevel(), 2);
//        assertEquals(testTask12.getUrgentLevel(), 12);
//        assertEquals(testTask1.getUrgentLevel(), 1);
//        assertEquals(testTask6.getUrgentLevel(), 6);
//        assertEquals(testTask10.getUrgentLevel(), 10);
//        assertEquals(testTask11.getUrgentLevel(), 11);
//        assertEquals(testTask3.getUrgentLevel(), 3);
//        assertEquals(testTask4.getUrgentLevel(), 4);
//        assertEquals(testTask7.getUrgentLevel(), 7);
//        assertEquals(testTask8.getUrgentLevel(), 8);
//    }


    @Test
    void getTaskName() {
        assertEquals(testTask1.getTaskName(), "Homework");
        assertEquals(testTask12.getTaskName(), "Sleep");
        assertEquals(testTask5.getTaskName(), "Drawing");
        assertEquals(testTask9.getTaskName(), "Wash dishes");
        assertEquals(testTask6.getTaskName(), "Shower");
        assertEquals(testTask3.getTaskName(), "Reading");
        assertEquals(testTask11.getTaskName(), "Make bed");
    }

    @Test
    void isComTasks() {
        testTask12.completed();
        assertTrue(testTask12.getComp());
        assertFalse(testTask2.getComp());
        testTask2.completed();
        assertTrue(testTask2.getComp());
        assertFalse(testTask1.getComp());
        assertFalse(testTask6.getComp());
        testTask4.completed();
        Assertions.assertTrue(testTask4.getComp());
        assertFalse(testTask7.getComp());
        testTask8.completed();
        assertTrue(testTask8.getComp());
        testTask10.completed();
        Assertions.assertTrue(testTask10.getComp());
    }
}

