package view.enums.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Command {
    //common
    BACK("back", new ArrayList<>(
    )),
    EXIT("exit", new ArrayList<>(
    )),
    SHOW_MENU("show\\s+current\\s+menu", new ArrayList<>(
    )),

    //SignupMenu
    REGISTER("user\\s+create", new ArrayList<>(List.of(
            new Option("u", 1, false),
            new Option("p", 2, false),
            new Option("e", 1, false),
            new Option("n", 1, false),
            new Option("s", 1, false)
    ))),
    REGISTER_WITH_RANDOM_PASSWORD("user\\s+create", new ArrayList<>(List.of(
            new Option("u", 1, false),
            new Option("p", 1, false),
            new Option("e", 1, false),
            new Option("n", 1, false),
            new Option("s", 1, false)
    ))),
    QUESTION_PICK("question\\s+pick", new ArrayList<>(List.of(
            new Option("q", 1, false),
            new Option("a", 1, false),
            new Option("c", 1, false)
    ))),

    //LoginMenu
    LOGIN("user\\s+login", new ArrayList<>(List.of(
            new Option("u", 1, false),
            new Option("p", 2, false),
            new Option("-stay-logged-in", 0, false)
    ))),
    FORGET_PASSWORD("forgot\\s+my\\s+password", new ArrayList<>(List.of(
            new Option("u", 1, true)
    ))),
    USER_LOGOUT("user\\s+logout", new ArrayList<>(
    )),

    //GameMenu
    SHOW_POPULARITY_FACTORS("show\\s+popularity\\s+factors", new ArrayList<>(
    )),
    SHOW_POPULARITY("show\\s+popularity", new ArrayList<>(
    )),
    FEAR_RATE("fear\\s+rate", new ArrayList<>(List.of(
            new Option("r", 1, true)
    ))),
    DROP_BUILDING("dropbuilding", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false),
            new Option("type", 1, false)
    ))),
    SELECT_BUILDING("select\\s+building", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false)
    ))),
    SELECT_UNIT("select\\s+unit", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false)
    ))),
    NEXT_TURN("next\\s+turn", new ArrayList<>(
    )),

    //BuildingMenu,
    SHOW_FOOD_LIST("show\\s+food\\s+list", new ArrayList<>(
    )),
    FOOD_RATE("food\\s+rate", new ArrayList<>(List.of(
            new Option("r", 1, true)
    ))),
    FOOD_RATE_SHOW("food\\s+rate\\s+show", new ArrayList<>(
    )),
    TAX_RATE("tax\\s+rate", new ArrayList<>(List.of(
            new Option("r", 1, true)
    ))),
    TAX_RATE_SHOW("tax\\s+rate\\s+show", new ArrayList<>(
    )),
    CREATE_UNIT("createunit", new ArrayList<>(List.of(
            new Option("t", 1, false),
            new Option("c", 1, false)
    ))),
    REPAIR("repair", new ArrayList<>(List.of(
            new Option("t", 1, false),
            new Option("c", 1, false)
    ))),

    //UnitMenu
    MOVE_UNIT_TO("move\\s+unit\\s+to", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false)
    ))),
    PATROL_UNIT("patrol\\s+unit", new ArrayList<>(List.of(
            new Option("x1", 1, false),
            new Option("y1", 1, false),
            new Option("x2", 1, false),
            new Option("y2", 1, false)
    ))),
    SET("set", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false),
            new Option("s", 1, false)
    ))),
    ATTACK("attack", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false)
    ))),
    POUR_OIL("pour\\s+oil", new ArrayList<>(List.of(
            new Option("d", 1, true)
    ))),
    DIG_TUNNEL("dig\\s+tunnel", new ArrayList<>(List.of(
            new Option("x", 1, false),
            new Option("y", 1, false)
    ))),
    BUILD("build", new ArrayList<>(List.of(
            new Option("q", 1, true)
    ))),
    DISBAND_UNIT("disband\\s+unit",  new ArrayList<>(
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
