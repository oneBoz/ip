package parser;

import enumeration.CommandType;
import exception.InvalidInputFormatException;
import task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testCreateTaskFromInput_Event() throws InvalidInputFormatException{
        Task e, e1, e2;

        e = Parser.createTaskFromInput("event natlan exploration /from 1900-01-01 /to 2025-01-01");
        e1 = Parser.createTaskFromInput("event natlan exploration/from1900-01-01/to2025-01-01");
        e2 = Parser.createTaskFromInput("event natlan exploration/fromtues/towed");


        e1.markAsDone();
        assertEquals("[E][ ] natlan exploration (from: 1900-01-01 to: 2025-01-01)", e.toRawString());
        assertEquals("[E][X] natlan exploration (from: 1900-01-01 to: 2025-01-01)", e1.toRawString());
        assertEquals("[E][X] natlan exploration (from: Jan 1 1900 to: Jan 1 2025)", e1.toString());
        assertEquals("[E][ ] natlan exploration (from: tues to: wed)", e2.toString());
    }

    @Test
    public void testCreateTaskFromInput_Deadline() throws InvalidInputFormatException{
        Task d, d1, d2 = null;

        d = Parser.createTaskFromInput("deadline CS2103T iP /by 2024-08-30");
        d1 = Parser.createTaskFromInput("deadline CS2103T iP/by2024-08-30");
        d2 = Parser.createTaskFromInput("deadline CS2103T iP/byFriday");

        d1.markAsDone();
        assertEquals("[D][ ] CS2103T iP (by: 2024-08-30)", d.toRawString());
        assertEquals("[D][X] CS2103T iP (by: 2024-08-30)", d1.toRawString());
        assertEquals("[D][X] CS2103T iP (by: Aug 30 2024)", d1.toString());
        assertEquals("[D][ ] CS2103T iP (by: Friday)", d2.toString());
    }

    @Test
    public void testCreateTaskFromInput_Todo() throws InvalidInputFormatException {
        Task t, t1, t2 = null;

        t = Parser.createTaskFromInput("todo CS2103T iP ");
        t1 = Parser.createTaskFromInput("todo CS2103T iP");

        t1.markAsDone();
        assertEquals("[T][ ] CS2103T iP", t.toRawString());
        assertEquals("[T][X] CS2103T iP", t1.toString());

    }

    @Test
    public void testCreateCommandFromInput() throws InvalidInputFormatException {
        assertEquals(CommandType.BYE, Parser.createCommandFromInput("bye  "));
        assertEquals(CommandType.LIST, Parser.createCommandFromInput("list"));
        assertEquals(CommandType.MARK, Parser.createCommandFromInput("mark 1"));
        assertEquals(CommandType.UNMARK, Parser.createCommandFromInput("unmark 2"));
        assertEquals(CommandType.TODO, Parser.createCommandFromInput("todo hi"));
        assertEquals(CommandType.DEADLINE, Parser.createCommandFromInput("deadline"));
        assertEquals(CommandType.EVENT, Parser.createCommandFromInput("event  "));
        assertEquals(CommandType.DELETE, Parser.createCommandFromInput("delete  "));

    }
}