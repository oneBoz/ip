package gui;


import enumeration.CommandType;
import exception.InvalidIndexException;
import exception.InvalidInputFormatException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * @author A0272287W
 * Chatbot
 */
public class Friday {

    private static void save(String filepath, TaskList tasks) {
        Storage storage = new Storage(filepath);
        storage.write(tasks);
    }

    private static TaskList read(String filepath) {
        Storage storage = new Storage(filepath);
        return storage.read();
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
        String intro = "____________________________________________________________\n"
                + "Hello! I'm Friday\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n\n"
                + "Commands:\n" + "list\n" + "bye\n" + "todo\n" + "deadline\n" + "event\n"
                + "____________________________________________________________\n";

        boolean exited = false;
        TaskList tasks = read("Friday.txt");
        System.out.println(intro);


        while (!exited) {
            System.out.println("____________________________________________________________\n");
            String input = Parser.scan();
            try {
                System.out.println("____________________________________________________________\n");
                System.out.println(Parser.performActionOnTaskList(input, tasks));
                if (Parser.createCommandFromInput(input) == CommandType.BYE) {
                    exited = true;
                    save("Friday.txt", tasks);
                }
            } catch (InvalidIndexException | InvalidInputFormatException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________\n");

        }

    }

    public String getResponse(String input) {
        TaskList tasks = read("Friday.txt");

        try {
            String response = Parser.performActionOnTaskList(input, tasks);
            save("Friday.txt", tasks);
            return response;
        } catch (InvalidIndexException | InvalidInputFormatException e) {
            return e.getMessage();
        }

    }

}
