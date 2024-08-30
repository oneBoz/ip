import java.util.ArrayList;


import exception.InvalidIndexException;
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
        System.out.println(String.format("""
        ____________________________________________________________
         Got it. I've added this task: 
          %s
         Now you have %d tasks in the list.""", t, tasks.size()));
    }

    private static void listTasks(ArrayList<Task> tasks) {

        System.out.println("""
        ____________________________________________________________
         Here are the tasks in your list: """);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, tasks.get(i)));
        }

    }

    private static void markTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).markAsDone();
        System.out.println(String.format("""
        ____________________________________________________________
         Nice! I've marked this task as done:
            %s""", tasks.get(loc)));
    }

    private static void unMarkTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException{
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).unmark();
        System.out.println(String.format("""
        ____________________________________________________________
         OK, I've marked this task as not done yet:
           %s""", tasks.get(loc)));
    }

    private static void deleteTask(ArrayList<Task> tasks, int loc) throws InvalidIndexException{
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        Task tmp = tasks.get(loc);
        tasks.remove(loc);
        System.out.println(String.format("""
        ____________________________________________________________
         Noted. I've removed this task:
           %s
         Now you have %d tasks in the list.""", tmp, tasks.size()));
    }

    private static String getCommand(String input) {
        return input.split(" ")[0];
    }

    private static String getDesc(String input) {
        return input.replace(getCommand(input), "").trim();
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

    public static void main(String[] args) {
        start();
    }

    private static void start() {

        String intro = """
        ____________________________________________________________
         Hello! I'm Friday
         What can I do for you?
        ____________________________________________________________
        
         Commands:
         list
         bye
         todo
         deadline
         event
        ____________________________________________________________""";

        String byeMsg = """
        ____________________________________________________________
        Bye. Hope to see you again soon!""";




        boolean exited = false;
        ArrayList<Task> tasks = read("Friday.txt");
//        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(intro);


        while (!exited) {
            String input = Parser.scan();
            CommandType command;
            try {
                command = Parser.createCommandFromInput(input);
            } catch (InvalidInputFormatException iife) {
                System.out.println(String.format("""
                ____________________________________________________________
                  %s
                ____________________________________________________________""", iife.getMessage()));
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
                    System.out.println(String.format("""
                    ____________________________________________________________
                      %s""", iie.getMessage()));
                }

            } else if (command == CommandType.UNMARK) {

                try {
                    unMarkTask(tasks, Integer.valueOf(desc) - 1);
                } catch (InvalidIndexException iie) {
                    System.out.println(String.format("""
                    ____________________________________________________________
                      %s""", iie.getMessage()));
                }

            } else if (command == CommandType.DEADLINE ||
                    command == CommandType.TODO ||
                    command == CommandType.EVENT) {

                try {
                    addTask(Parser.createTaskFromInput(input), tasks);
                } catch (InvalidInputFormatException iife) {
                    System.out.println(String.format("""
                    ____________________________________________________________
                      %s""", iife.getMessage()));
                }


            } else if (command == CommandType.DELETE) {

                try {
                    deleteTask(tasks, Integer.valueOf(desc) - 1);
                } catch (InvalidIndexException iie) {
                    System.out.println(String.format("""
                    ____________________________________________________________
                      %s""", iie.getMessage()));
                }

            }


            System.out.println("""
                    ____________________________________________________________""");

        }

    }
}
