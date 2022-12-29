package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(int taskNum, String taskNam, Task task) {
        assertEquals(taskNam, task.getTaskName());
        assertEquals(taskNum, task.getTaskNum());
    }
}

//public class JsonTest {
//    protected void checkTask(String taskName, boolean comTasks, Task task) {
//        assertEquals(taskName, task.getTaskName());
//        assertEquals(comTasks, task.getComp());
//    }
//}
