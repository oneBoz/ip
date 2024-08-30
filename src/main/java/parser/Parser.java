package parser;

import exception.InvalidInputFormatException;

import enumeration.CommandType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import task.Task;
import task.Deadline;
import task.Todo;
import task.Event;

/**
 * Parser class that handles user inputs
 */
public class Parser {

    public Parser(){

    }



    /**
     * reads input lines from a file
     * @return ArrayList<String>
     */
    public static ArrayList<String> scan(String filepath) {
        try {
            Scanner sc = new Scanner(new File(filepath));
            ArrayList<String> out = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                out.add(line);
            }
            return out;

        } catch (FileNotFoundException fnfe) {
            System.out.println("""
                ____________________________________________________________
                Error reading from input file!""");

        }
        return new ArrayList<>();
    }

    /**
     * reads input lines from cli
     * @return ArrayList<String>
     */
    public static String scan() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;

    }

    private static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static String getDesc(String input) {
        return input.replaceFirst(getCommand(input), "").trim();
    }

    /**
     * Creates Task based each input line
     * @param input
     * @return Task
     * @throws InvalidInputFormatException
     */
    public static CommandType createCommandFromInput(String input) throws InvalidInputFormatException{
        String command = getCommand(input);
        if (Objects.equals(command, CommandType.BYE.toString())) {
            return CommandType.BYE;
        } else if (Objects.equals(command, CommandType.LIST.toString())) {
            return CommandType.LIST;
        } else if (Objects.equals(command, CommandType.MARK.toString())) {
            return CommandType.MARK;
        } else if (Objects.equals(command, CommandType.UNMARK.toString())) {
            return CommandType.UNMARK;
        } else if (Objects.equals(command, CommandType.DEADLINE.toString())) {
            return CommandType.DEADLINE;
        } else if (Objects.equals(command, CommandType.TODO.toString())) {
            return CommandType.TODO;
        } else if (Objects.equals(command, CommandType.EVENT.toString())) {
            return CommandType.EVENT;
        } else if (Objects.equals(command, CommandType.DELETE.toString())) {
            return CommandType.DELETE;
        } else if (Objects.equals(command, CommandType.GETDATE.toString())) {
            return CommandType.GETDATE;
        } else {
            throw new InvalidInputFormatException("Please enter a valid command!");
        }
    }

    /**
     * Creates Task from input line
     * @param input
     * @return Task
     * @throws InvalidInputFormatException
     */
    public static Task createTaskFromInput(String input) throws InvalidInputFormatException {
        CommandType command = createCommandFromInput(input);
        String desc = getDesc(input);
        if (command == CommandType.TODO) {
            return new Todo(desc);
        } else if (command == CommandType.EVENT) {
            String[] e = desc.split("/from");
            String[] timing = e[1].split("/to");
            return new Event(e[0].trim(), timing[0].trim(), timing[1].trim());
        } else if (command == CommandType.DEADLINE) {
            String[] dl = desc.split("/by");
            return new Deadline(dl[0].trim(), dl[1].trim());
        } else {
            throw new InvalidInputFormatException("Error creating Task!");
        }
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date.replace("/", "-"));
    }







}
