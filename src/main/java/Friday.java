import java.util.Scanner;

/**
 * @author Ding Yi He
 */
public class Friday {
    public static void main(String[] args) {

        String intro = """
                ____________________________________________________________
                Hello! I'm Friday
                What can I do for you?
                ____________________________________________________________
                        
                Commands:
                list
                blah
                bye
                """;

        String byeMsg = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________""";

        String listMsg = """
                ____________________________________________________________
                list
                ____________________________________________________________""";

        String blah = """
                ____________________________________________________________
                blah
                ____________________________________________________________""";


        boolean exited = false;
        System.out.println(intro);
        while (!exited) {
            Scanner scan = new Scanner(System.in);
            switch (scan.nextLine()) {
                case "bye":
                    System.out.println(byeMsg);
                    exited = true;
                    break;
                case "list":
                    System.out.println(listMsg);
                    break;
                case "blah":
                    System.out.println(blah);
                    break;
                default:
                    System.out.println("Invalid command! Please retry!");
                    break;

            }

        }

    }
}
