package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromInDateFormat;
    protected LocalDate toInDateFormat;
    protected boolean isInDateFormat = false;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        try {
            fromInDateFormat = LocalDate.parse(from);
            toInDateFormat = LocalDate.parse(to);
            isInDateFormat = true;
        } catch (DateTimeParseException dtpe) {
            isInDateFormat = false;
        }
    }

    @Override
    public String toString() {
        if (isInDateFormat) {
            return "[E]" + super.toString() + " (from: " +
                    fromInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                    toInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return this.toRawString();
    }

    @Override
    public String toRawString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
    
}