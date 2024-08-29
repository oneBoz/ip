package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {

    private File file;

    /**
     * Constructor for Storage
     * @param filepath
     */
    public Storage(String filepath) {
        file = new File(filepath);
    }

    /**
     * reads the data from the file
     *
     * @return a Task array containing all the tasks
     */
    public ArrayList<Task> read() {
        try {
            System.out.println("""
                    ____________________________________________________________
                    Retrieving inputs ...""");
            Scanner sc = new Scanner(file);
            ArrayList<Task> out = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                switch (line.charAt(1)) {
                    case 'T':
                        Todo t = new Todo(line.substring(7));
                        if (line.charAt(4) == 'X') {
                            t.markAsDone();
                        }
                        out.add(t);
                        break;
                    case 'D':
                        String[] dtmp = line.split(" \\(by: ");
                        String by = dtmp[1].split("\\)")[0];
                        Deadline d = new Deadline(dtmp[0].substring(7), by);
                        if (line.charAt(4) == 'X') {
                            d.markAsDone();
                        }
                        out.add(d);
                        break;
                    case 'E':
                        String[] etmp = line.split(" \\(from: ");
                        String[] tmp2 = etmp[1].split(" to: ");
                        String from = tmp2[0];
                        String to = tmp2[1].split("\\)")[0];
                        Event e = new Event(etmp[0].substring(7), from, to);
                        if (line.charAt(4) == 'X') {
                            e.markAsDone();
                        }
                        out.add(e);
                        break;
                    default:
                        break;
                }

            }
            sc.close();


            return out;
        } catch (FileNotFoundException fnfe) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
            } catch (IOException ioe) {
                System.out.println("""
                    ____________________________________________________________
                    Error creating storage ...""");
            }


        }
        return new ArrayList<>();

    }

    /**
     * Writes data into file
     *
     */
    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(tasks.get(i).toString());
                sb.append(System.lineSeparator());
            }
            fw.write(sb.toString());
            fw.close();
            System.out.println("""
                ____________________________________________________________
                Data saved""");
        } catch (IOException ioe) {
            System.out.println("""
                ____________________________________________________________
                Error saving!""");
        }
    }


}
