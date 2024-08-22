import java.util.Scanner;

/**
 * @author Ding Yi He
 */
public class Friday {

    private static void addTask(String s, String[] tasks, int loc) {
        tasks[loc] = s;
        System.out.println(String.format("""
        ____________________________________________________________
        added: %s
        ____________________________________________________________""", s));
    }

    private static void listTasks(String[] tasks, int size) {

        System.out.println("""
        ____________________________________________________________""");
        for (int i = 0; i < size; i++) {
            System.out.println(String.format("%d. %s", i, tasks[i]));
        }

        System.out.println("""
        ____________________________________________________________""");
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
        String[] tasks = new String[100];
        int loc = 0;
        System.out.println(intro);
        while (!exited) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            switch (input) {
                case "bye":
                    System.out.println(byeMsg);
                    exited = true;
                    break;

                case "list":
                    listTasks(tasks, loc);
                    break;

                default:
                    addTask(input, tasks, loc);
                    loc++;
                    break;

            }

        }

    }
}
