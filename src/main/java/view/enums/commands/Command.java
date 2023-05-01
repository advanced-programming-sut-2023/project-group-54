package view.enums.commands;

import java.util.ArrayList;
import java.util.Arrays;

public enum Command {
    //common
    BACK("back", new ArrayList<>(
    )),
    EXIT("exit", new ArrayList<>(
    )),
    SHOW_MENU("show\\s+current\\s+menu", new ArrayList<>(
    )),

    //SignupMenu
    REGISTER("user\\s+create", new ArrayList<>(Arrays.asList(
            new Option("u", 1, false),
            new Option("p", 2, false),
            new Option("e", 1, false),
            new Option("n", 1, false),
            new Option("s", 1, false)
    ))),
    REGISTER_WITH_RANDOM_PASSWORD("user\\s+create", new ArrayList<>(Arrays.asList(
            new Option("u", 1, false),
            new Option("p", 1, false),
            new Option("e", 1, false),
            new Option("n", 1, false),
            new Option("s", 1, false)
    ))),
    QUESTION_PICK("question\\s+pick", new ArrayList<>(Arrays.asList(
            new Option("q", 1, false),
            new Option("a", 1, false),
            new Option("c", 1, false)
    ))),

    //LoginMenu
    LOGIN("user\\s+login", new ArrayList<>(Arrays.asList(
            new Option("u", 1, false),
            new Option("p", 1, false),
            new Option("-stay-logged-in", 2, false)
    ))),
    FORGET_PASSWORD("forgot\\s+my\\s+password", new ArrayList<>(Arrays.asList(
            new Option("u", 1, false)
    ))),
    USER_LOGOUT("user\\s+logout", new ArrayList<>(
    )),
    ;

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
