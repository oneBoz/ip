package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class StorageTest {

    @Test
    public void testReadAndWrite() {
        Storage s = new Storage("FridayTest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("Natlan exploration", "2024-08-30", "2024-09-30"));
        tasks.add(new Deadline("CS2103T iP", "2024-08-30"));
        tasks.add(new Todo("read"));
        s.write(tasks);
        ArrayList<Task> tasks1 = s.read();
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i), tasks1.get(i));
        }

    }

    @Test
    public void testReadNonExistingFile() {
        Storage s = new Storage("NonExistingFileTest.txt");
        ArrayList<Task> tasks1 = s.read();
        assertEquals(tasks1.size(), 0);
    }


}
