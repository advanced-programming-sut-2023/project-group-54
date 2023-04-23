package view.enums.commands;

import java.util.ArrayList;
import java.util.Arrays;

public enum Command {
    REGISTER("user\\s+create", new ArrayList<>(Arrays.asList(
            new Option("u", 1, true),
            new Option("p", 2, true),
            new Option("e", 1, true),
            new Option("n", 1, true),
            new Option("s", 1, false)
    )));

    private String commandRegex;
    private ArrayList<Option> options;

    Command(String commandRegex, ArrayList<Option> options) {
        this.commandRegex = commandRegex;
        this.options = options;
    }

    public String getCommandRegex() {
        return commandRegex;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }
}
