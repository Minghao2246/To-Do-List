//package persistence;
//
//
//import com.sun.tools.javac.comp.To-do;
//import model.ToDoList;
//import model.Task;
//
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import org.json.*;
//
//// JsonReader functionality and methods are implemented from JsonSerialization linked below:
//// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//
//public class JsonReader {
//    // Represents a reader that reads ToDoList from the JSON data stored
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads ToDoList from file and returns it;
//    public ToDoList read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseToDoList(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return  contentBuilder.toString();
//    }
//
//    // EFFECTS: parses to-do list from JSON object and returns it
//    private ToDoList parseToDoList(JSONObject jsonObject) {
//        ToDoList td = new ToDoList();
//        addTasks(td, jsonObject);
//        return td;
//    }
//
//    // MODIFIES: td
//    // EFFECTS: parses thingies from JSON object and adds them to user's to-do list
//    private void addTasks(ToDoList td, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
//        for (Object json : jsonArray) {
//            JSONObject nextTask = (JSONObject) json;
//            addTask(td, nextTask);
//        }
//    }
//
//    // MODIFIES: wr
//    // EFFECTS: parses thingy from JSON object and adds it to to-do list
//    private void addTask(ToDoList td, JSONObject jsonObject) {
//        String name = jsonObject.getString("taskName");
//        int urgentLevel = jsonObject.getInt("level");
//        Task task = new Task(urgentLevel, name);
//        td.addTasks(task);
//    }
//}

package persistence;

import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// JsonReader functionality and methods are implemented from JsonSerialization linked below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents a reader that reads todolist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TodoList from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList td = new ToDoList();
        addTasks(td, jsonObject);
        return td;
    }

    // MODIFIES: td
    // EFFECTS: parses tasks from JSON object and adds them to todolist
    private void addTasks(ToDoList td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(td, nextTask);
        }
    }

    // MODIFIES: td
    // EFFECTS: parses task from JSON object and adds it to todolist
    private void addTask(ToDoList td, JSONObject jsonObject) {
        String name = jsonObject.getString("taskName");
        Task task = new Task(name);
        td.addTasks(task);
    }
}