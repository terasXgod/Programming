package data;

import commandHandler.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import collections.CollectionKeeper;
import collections.Vehicle;
import commandHandler.Commands.Command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    private Manager manager;
    private CommandParser parser;

    @BeforeEach
    void setup() {
        Vehicle.clearIdRegistry();
        manager = new Manager(new CollectionKeeper(), null);
        parser = new CommandParser(manager);
    }

    private String captureOutput(Command cmd) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            cmd.execute();
        } finally {
            System.setOut(original);
        }
        return out.toString();
    }

    @Test
    void missingArgumentRemoveLower_withSpace() {
        Command cmd = parser.parse("remove_lower ");
        assertNotNull(cmd);
        String output = captureOutput(cmd);
        assertTrue(output.contains("[ERROR] argument expected"));
    }

    @Test
    void missingArgumentRemoveLower_withoutSpace() {
        Command cmd = parser.parse("remove_lower");
        assertNotNull(cmd);
        String output = captureOutput(cmd);
        assertTrue(output.contains("[ERROR] argument expected"));
    }

    @Test
    void unknownCommandResultsError() {
        Command cmd = parser.parse("foobar");
        assertNotNull(cmd);
        String output = captureOutput(cmd);
        assertTrue(output.contains("[ERROR] Unknown command"));
    }

    @Test
    void noArgCommandsProduceExpectedInstances() {
        assertTrue(parser.parse("help") instanceof commandHandler.Commands.HelpCommand);
        assertTrue(parser.parse("info") instanceof commandHandler.Commands.InfoCommand);
        assertTrue(parser.parse("clear") instanceof commandHandler.Commands.ClearCommand);
        assertTrue(parser.parse("show") instanceof commandHandler.Commands.ShowCommand);
        assertTrue(parser.parse("save") instanceof commandHandler.Commands.SaveCommand);
        assertTrue(parser.parse("group_counting_by_creation_date") instanceof commandHandler.Commands.GroupByDateCommand);
        assertTrue(parser.parse("history") instanceof commandHandler.Commands.HistoryCommand);
        assertTrue(parser.parse("print_field_descending_engine_power") instanceof commandHandler.Commands.EnginePowerCommand);
        // exit should not be executed to avoid terminating test
        assertTrue(parser.parse("exit") instanceof commandHandler.Commands.ExitCommand);
    }

    @Test
    void argumentCommandsProduceExpectedInstances() {
        String vehicleStr = "{,T,1,1,,1,1,1,PLANE}";
        assertTrue(parser.parse("add " + vehicleStr) instanceof commandHandler.Commands.AddCommand);
        assertTrue(parser.parse("update 3 " + vehicleStr) instanceof commandHandler.Commands.UpdateCommand);
        assertTrue(parser.parse("remove_by_id 5") instanceof commandHandler.Commands.RemoveByIdCommand);
        assertTrue(parser.parse("execute_script file.txt") instanceof commandHandler.Commands.ExecuteScriptCommand);
        assertTrue(parser.parse("remove_greater " + vehicleStr) instanceof commandHandler.Commands.RemoveGreaterCommand);
        assertTrue(parser.parse("remove_lower " + vehicleStr) instanceof commandHandler.Commands.RemoveLowerCommand);
        assertTrue(parser.parse("filter_less_than_fuel_consumption 2.5") instanceof commandHandler.Commands.FilterLessThanFuelConsumptionCommand);
    }

    @Test
    void executingSomeCommandsCallsManagerOutputs() {
        String helpOut = captureOutput(parser.parse("help"));
        assertTrue(helpOut.contains("help :"));

        String infoOut = captureOutput(parser.parse("info"));
        assertTrue(infoOut.contains("Class type:"));

        // add command should print confirmation
        String vehicleStr = "{,X,1,1,,1,1,1,PLANE}";
        String addOut = captureOutput(parser.parse("add " + vehicleStr));
        assertTrue(addOut.contains("Vehicle has completely added"));

        String removeByIdOut = captureOutput(parser.parse("remove_by_id 1"));
        assertTrue(removeByIdOut.contains("Object has been deleted"));
    }
}
