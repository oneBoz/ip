package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byInDateFormat;
    protected boolean isInDateFormat = false;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            byInDateFormat = LocalDate.parse(by);
            isInDateFormat = true;
        } catch (DateTimeParseException dtpe) {
            isInDateFormat = false;
        }

    }

    @Override
    public String toString() {
        if (isInDateFormat) {
            return "[D]" + super.toString() + " (by: " +
                    byInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return this.toRawString();
    }

    @Override
    public String toRawString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getByMonth() {
        if (isInDateFormat) {
            return byInDateFormat.getMonth().toString();
        }
        return "";
    }

}