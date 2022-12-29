package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList td = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals(0, td.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList td = reader.read();
            LinkedList<Task> tasks = td.viewList();
            assertEquals(2, td.length());
            checkTask(1, "Homework", tasks.get(0));
            checkTask(2, "Sleep", tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
