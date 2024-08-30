import java.util.ArrayList;


import exception.InvalidIndexException;
import parser.Command;
import task.Task;

import storage.Storage;
import enumeration.CommandType;
import exception.InvalidInputFormatException;
import parser.Parser;

/**
 * @author A0272287W
 * Chatbot
 */
public class Friday {


    private static void addTask(Task t, ArrayList<Task> tasks) {
        tasks.add(t);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task: ");
        System.out.println(String.format("  %s", t));
        System.out.println(String.format(" Now you have %d tasks in the list.",tasks.size()));
    }

    private static void listTasks(ArrayList<Task> tasks) {

        System.out.println("____________________________________________________________\nHere are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, tasks.get(i)));
        }

    }

    private static void findTasks(ArrayList<Task> tasks, String text) {

        System.out.println("____________________________________________________________\nHere are the matching tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(text)) {
                System.out.println(String.format(" %d. %s", i + 1, tasks.get(i)));
            }
        }

    }

    private static void markTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(String.format("    %s", tasks.get(loc)));
    }

    private static void unMarkTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException{
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).unmark();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(String.format("   %s", tasks.get(loc)));
    }

    private static void deleteTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException{
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        Task tmp = tasks.get(loc);
        tasks.remove(loc);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println(String.format("   %s", tmp));
        System.out.println(String.format(" Now you have %d tasks in the list.", tasks.size()));
    }


    private static void save(String filepath, ArrayList<Task> tasks) {
        Storage storage = new Storage(filepath);
        storage.write(tasks);

    }
    
    private static ArrayList<Task> read(String filepath) {
        Storage storage = new Storage(filepath);
        ArrayList<Task> data = storage.read();
        return data;
    }

    /**
     * The main method serves as the entry point for the application.
     * It initiates the program by calling the {@code start} method.
     *
     * @param args command-line arguments passed to the program (not used in this implementation).
     */
    public static void main(String[] args) {
        start();
    }

    private static void start() {

        String intro = "____________________________________________________________\n" +
                "Hello! I'm Friday\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n\n" +
                "Commands:\n" + "list\n" + "bye\n" + "todo\n" + "deadline\n" + "event\n" +
                "____________________________________________________________\n";

        String byeMsg = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!";




        boolean exited = false;
        ArrayList<Task> tasks = read("Friday.txt");
        System.out.println(intro);


        while (!exited) {
            String input = Parser.scan();
            CommandType command;
            try {
                command = Parser.createCommandFromInput(input);
            } catch (InvalidInputFormatException iife) {
                System.out.println("____________________________________________________________");
                System.out.println(String.format("  %s", iife.getMessage()));
                System.out.println("____________________________________________________________");

                continue;
            }

            System.out.println(command);
            String desc = Parser.getDesc(input);

            if (command == CommandType.BYE) {
                System.out.println(byeMsg);
                save("Friday.txt", tasks);
                exited = true;

            } else if (command == CommandType.LIST) {

                listTasks(tasks);

            } else if (command == CommandType.MARK) {

                try {
                    markTask(tasks, Integer.valueOf(desc) - 1);
                } catch (InvalidIndexException iie) {

                    System.out.println("____________________________________________________________");
                    System.out.println(String.format("  %s", iie.getMessage()));
                }

            } else if (command == CommandType.UNMARK) {

                try {
                    unMarkTask(tasks, Integer.valueOf(desc) - 1);
                } catch (InvalidIndexException iie) {
                    System.out.println("____________________________________________________________");
                    System.out.println(String.format("  %s", iie.getMessage()));
                }

            } else if (command == CommandType.DEADLINE ||
                    command == CommandType.TODO ||
                    command == CommandType.EVENT) {

                try {
                    addTask(Parser.createTaskFromInput(input), tasks);
                } catch (InvalidInputFormatException iife) {
                    System.out.println("____________________________________________________________");
                    System.out.println(String.format("  %s", iife.getMessage()));
                }


            } else if (command == CommandType.DELETE) {

                try {
                    deleteTask(tasks, Integer.valueOf(desc) - 1);
                } catch (InvalidIndexException iie) {
                    System.out.println("____________________________________________________________");
                    System.out.println(String.format("  %s", iie.getMessage()));
                }

            } else if (command == CommandType.FIND) {
                findTasks(tasks, desc);
            }


            System.out.println("____________________________________________________________");

        }

    }
}
