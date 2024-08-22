import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import task.Task;
import task.Deadline;
import task.Todo;
import task.Event;
import enumeration.CommandType;
import exception.InvalidInputFormatException;

/**
 * @author A0272287W
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

    private static void markTask(ArrayList<Task> tasks, int loc) {
        tasks.get(loc).markAsDone();
        System.out.println(String.format("""
        ____________________________________________________________
         Nice! I've marked this task as done:
            %s""", tasks.get(loc)));
    }

    private static void unMarkTask(ArrayList<Task> tasks, int loc) {
        tasks.get(loc).unmark();
        System.out.println(String.format("""
        ____________________________________________________________
         OK, I've marked this task as not done yet:
           %s""", tasks.get(loc)));
    }

    private static void deleteTask(ArrayList<Task> tasks, int loc) {
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
        ArrayList<Task> tasks = new ArrayList<>();
        int loc = 0;
        System.out.println(intro);
        while (!exited) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String command = getCommand(input);
            String desc = getDesc(input);

            if (Objects.equals(command, CommandType.BYE.toString())) {
                System.out.println(byeMsg);
                exited = true;

            } else if (Objects.equals(command, CommandType.LIST.toString())) {

                listTasks(tasks);

            } else if (Objects.equals(command, CommandType.MARK.toString())) {

                try {
                    markTask(tasks, Integer.valueOf(desc) - 1);
                } catch (Exception e) {
                    System.out.println("Please enter a valid index!");
                }

            } else if (Objects.equals(command, CommandType.UNMARK.toString())) {

                try {
                    unMarkTask(tasks, Integer.valueOf(desc) - 1);
                } catch (Exception e) {
                    System.out.println("Please enter a valid index!");
                }

            } else if (Objects.equals(command, CommandType.DEADLINE.toString())) {

                try {
                    String[] dd = desc.split("/by");
                    addTask(new Deadline(dd[0].trim(), dd[1].trim()), tasks);
                    loc++;
                } catch (Exception e) {
                    System.out.println("Please enter a valid description!");
                }

            } else if (Objects.equals(command, CommandType.TODO.toString())) {

                if (desc.isEmpty()) {
                    System.out.println("Please enter a valid description!");
                } else {
                    addTask(new Todo(desc), tasks);
                }

            } else if (Objects.equals(command, CommandType.EVENT.toString())) {

                try {
                    String[] e = desc.split("/from");
                    String[] timing = e[1].split("/to");
                    addTask(new Event(e[0].trim(), timing[0].trim(), timing[1].trim()), tasks);
                } catch (Exception e) {
                    System.out.println("Please enter a valid description!");
                }

            } else if (Objects.equals(command, CommandType.DELETE.toString())) {

                try {
                    deleteTask(tasks, Integer.valueOf(desc) - 1);

                } catch (Exception e) {
                    System.out.println("Please enter a valid index!");
                }

            } else {

                System.out.println("Please enter a valid command!");

            }


            System.out.println("""
                    ____________________________________________________________""");

        }

    }
}
