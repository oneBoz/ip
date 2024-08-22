import java.util.Arrays;
import java.util.Scanner;
/**
 * @author Ding Yi He
 */
public class Friday {

    private static void addTask(String s, Task[] tasks, int loc) {
        tasks[loc] = new Task(s);
        System.out.println(String.format("""
        ____________________________________________________________
        added: %s
        ____________________________________________________________""", s));
    }

    private static void listTasks(Task[] tasks, int size) {

        System.out.println("""
        ____________________________________________________________
        Here are the tasks in your list: """);
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i]));
        }

        System.out.println("""
        ____________________________________________________________""");
    }

    private static void markTask(Task[] tasks, int loc) {
        tasks[loc].markAsDone();
        System.out.println(String.format("""
        ____________________________________________________________
        Nice! I've marked this task as done:
          %s
        ____________________________________________________________""", tasks[loc]));
    }

    private static void unMarkTask(Task[] tasks, int loc) {
        tasks[loc].unmark();
        System.out.println(String.format("""
        ____________________________________________________________
        OK, I've marked this task as not done yet:
          %s
        ____________________________________________________________""", tasks[loc]));
    }

    public static void main(String[] args) {

        String intro = """
        ____________________________________________________________
        Hello! I'm Friday
        What can I do for you?
        ____________________________________________________________
        
        Commands:
        list
        bye
        """;

        String byeMsg = """
        ____________________________________________________________
        Bye. Hope to see you again soon!
        ____________________________________________________________""";




        boolean exited = false;
        Task[] tasks = new Task[100];
        int loc = 0;
        System.out.println(intro);
        while (!exited) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "bye":
                    System.out.println(byeMsg);
                    exited = true;
                    break;

                case "list":
                    listTasks(tasks, loc);
                    break;

                case "mark":
                    markTask(tasks, Integer.valueOf(command[1]) - 1);
                    break;

                case "unmark":
                    unMarkTask(tasks, Integer.valueOf(command[1]) - 1);
                    break;

                default:
                    addTask(input, tasks, loc);
                    loc++;
                    break;

            }

        }

    }
}
