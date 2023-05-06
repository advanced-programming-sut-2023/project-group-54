package view.enums.commands;

import javax.swing.*;
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
    //game menu
    EXIT_OF_GAME_MENU("exit\\s+from\\s+game\\s+menu",new ArrayList<>()),
    SHOW_MAP("show\\s+map",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    SHOW_DETAILS("show\\s+details",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    MAP_UP_LEFT("map\\s+up\\s+left",new ArrayList<>()),
    MAP_UP_RIGHT("map\\s+up\\s+right",new ArrayList<>()),
    DROP_BUILDING("drop\\s+building",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    SELECT_BUILDING("select\\s+building",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    REPAIR("repair",new ArrayList<>()),
    SET_TEXTURE_FOR_ONE_HOUSE("set\\s+texture",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("t",1,false)
    ))),
    SET_TEXTURE_FOR_RECTANGLE("set\\s+texture",new ArrayList<>(Arrays.asList(
            new Option("x1",1,false),
            new Option("y1",1,false),
            new Option("x2",1,false),
            new Option("y2",1,false),
            new Option("t",1,false)
    ))),
    CLEAR("clear",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    DROP_ROCK("drop\\s+rock",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("d",1,false)
    ))),
    DROP_TREE("drop\\s+tree",new ArrayList<>(Arrays.asList(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("t",1,false)
    ))),
    



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
