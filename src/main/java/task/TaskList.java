package task;

import java.util.ArrayList;

import exception.InvalidIndexException;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     *
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(Task t) {
        this.tasks.add(t);
        String output = " Got it. I've added this task: \n" +
                String.format("  %s\n", t) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
        return output;
    }

    public String deleteTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        Task tmp = tasks.get(loc);
        tasks.remove(loc);
        String output = " Noted. I've removed this task:\n" +
                String.format("    %s\n", tmp) +
                String.format("  Now you have %d tasks in the list.\n", tasks.size());
        return output;
    }

    public String markTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).markAsDone();
        String output = "  Nice! I've marked this task as done:\n" +
                String.format("    %s\n", tasks.get(loc));
        return output;
    }

    public String unMarkTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).unmark();
        String output = "  OK, I've marked this task as not done yet:\n" +
                String.format("    %s\n", tasks.get(loc));
        return output;
    }

    public String findTasks(String text) {

        StringBuilder output = new StringBuilder("  Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(text)) {
                output.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
            }
        }
        return output.toString();

    }

    public String list() {
        StringBuilder response = new StringBuilder("  Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int loc) {
        return tasks.get(loc);
    }

}
