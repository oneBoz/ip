import java.util.Arrays;
import java.util.Scanner;
/**
 * @author Ding Yi He
 */
public class Friday {

    private static void addTask(Task t, Task[] tasks, int loc) {
        tasks[loc] = t;
        System.out.println(String.format("""
        ____________________________________________________________
         Got it. I've added this task: 
          %s
         Now you have %d tasks in the list.""", t, loc + 1));
    }

    private static void listTasks(Task[] tasks, int size) {

        System.out.println("""
        ____________________________________________________________
         Here are the tasks in your list: """);
        for (int i = 0; i < size; i++) {
            System.out.println(String.format(" %d. %s", i + 1, tasks[i]));
        }

    }

    private static void markTask(Task[] tasks, int loc) {
        tasks[loc].markAsDone();
        System.out.println(String.format("""
        ____________________________________________________________
         Nice! I've marked this task as done:
            %s""", tasks[loc]));
    }

    private static void unMarkTask(Task[] tasks, int loc) {
        tasks[loc].unmark();
        System.out.println(String.format("""
        ____________________________________________________________
         OK, I've marked this task as not done yet:
           %s""", tasks[loc]));
    }

    private static String getCommand(String input) {
        return input.split(" ")[0];
    }

    private static String getDesc(String input) {
        return input.replace(getCommand(input) + " ", "");
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
         todo
         deadline
         event
        ____________________________________________________________""";

        String byeMsg = """
        ____________________________________________________________
        Bye. Hope to see you again soon!""";




        boolean exited = false;
        Task[] tasks = new Task[100];
        int loc = 0;
        System.out.println(intro);
        while (!exited) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String command = getCommand(input);
            String desc = getDesc(input);
            switch (command) {
                case "bye":
                    System.out.println(byeMsg);
                    exited = true;
                    break;

                case "list":
                    listTasks(tasks, loc);
                    break;

                case "mark":
                    markTask(tasks, Integer.valueOf(desc) - 1);
                    break;

                case "unmark":
                    unMarkTask(tasks, Integer.valueOf(desc) - 1);
                    break;

                case "deadline":
                    String[] dd = desc.split(" /by ");
                    addTask(new Deadline(dd[0], dd[1]), tasks, loc);
                    loc++;
                    break;

                case "todo":
                    addTask(new Todo(desc), tasks, loc);
                    loc++;
                    break;

                case "event":
                    String[] e = desc.split(" /from ");
                    String[] timing = e[1].split(" /to ");
                    addTask(new Event(e[0], timing[0], timing[1]), tasks, loc);
                    loc++;
                    break;

                default:
                    System.out.println("Please enter a valid command!");
                    break;

            }
            System.out.println("""
                    ____________________________________________________________""");

        }

    }
}
