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
            new Option("p", 1, false),
            new Option("-stay-logged-in", 0, false)
    ))),
    FORGET_PASSWORD("forgot\\s+my\\s+password", new ArrayList<>(List.of(
            new Option("u", 1, true)
    ))),
    USER_LOGOUT("user\\s+logout", new ArrayList<>(
    )),

    //ProfileMenu
    CHANGE_USERNAME("profile\\s+change",new ArrayList<>(List.of(
            new Option("u",1,true)
    ))),
    CHANGE_NICKNAME("profile\\s+change",new ArrayList<>(List.of(
            new Option("n",1,true)
    ))),
    CHANGE_PASSWORD("profile\\s+change\\s+password",new ArrayList<>(List.of(
            new Option("o",1,false),
            new Option("n",1,false)
    ))),
    CHANGE_EMAIL("profile\\s+change",new ArrayList<>(List.of(
            new Option("e",1,true)
    ))),
    CHANGE_SLOGAN("profile\\s+change\\s+slogan",new ArrayList<>(List.of(
            new Option("s",1,true)
    ))),
    REMOVE_SLOGAN("profile\\s+remove\\s+slogan",new ArrayList<>(
    )),
    DISPLAY_SLOGAN("profile\\s+display\\s+slogan",new ArrayList<>(
    )),
    DISPLAY_PROFILE("profile\\s+display",new ArrayList<>(
    )),

    //ShopMenu
    SHOW_PRICE_LIST("show\\s+price\\s+list",new ArrayList<>(
    )),
    SHOP_BUY("buy\\s+",new ArrayList<>(List.of(
            new Option("i",1,false),
            new Option("a",1,false)
    ))),

    //GameMenu
    SHOW_POPULARITY_FACTORS("show\\s+popularity\\s+factors", new ArrayList<>(
    )),
    SHOW_POPULARITY("show\\s+popularity", new ArrayList<>(
    )),
    FEAR_RATE("fear\\s+rate", new ArrayList<>(List.of(
            new Option("r", 1, true)
    ))),
    EXIT_OF_GAME_MENU("exit\\s+from\\s+game\\s+menu",new ArrayList<>(
    )),
    SHOW_MAP("show\\s+map",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    SHOW_DETAILS("show\\s+details",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    SET_TEXTURE_FOR_ONE_HOUSE("set\\s+texture",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("t",1,false)
    ))),
    SET_TEXTURE_FOR_RECTANGLE("set\\s+texture",new ArrayList<>(List.of(
            new Option("x1",1,false),
            new Option("y1",1,false),
            new Option("x2",1,false),
            new Option("y2",1,false),
            new Option("t",1,false)
    ))),
    CLEAR("clear",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false)
    ))),
    START_GAME("start game", new ArrayList<>(List.of(
            new Option("count", 1, true),
            new Option("a", 1, true),
            new Option("b", 1, false),
            new Option("c", 1, false),
            new Option("d", 1, false),
            new Option("e", 1, false),
            new Option("f", 1, false),
            new Option("g", 1, false)
    ))),
    DROP_ROCK("drop\\s+rock",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("d",1,false)
    ))),
    DROP_TREE("drop\\s+tree",new ArrayList<>(List.of(
            new Option("x",1,false),
            new Option("y",1,false),
            new Option("t",1,false)
    ))),
    CLEAR_FOR_RECTANGLE("clear",new ArrayList<>(List.of(
            new Option("x1",1,false),
            new Option("y1",1,false),
            new Option("x2",1,false),
            new Option("y2",1,false),
            new Option("t",1,false)
    ))),
    DROP_ROCK_FOR_RECTANGLE("drop\\s+rock",new ArrayList<>(List.of(
            new Option("x1",1,false),
            new Option("y1",1,false),
            new Option("x2",1,false),
            new Option("y2",1,false),
            new Option("t",1,false)
    ))),
    DROP_TREE_FOR_RECTANGLE("drop\\s+tree",new ArrayList<>(List.of(
            new Option("x1",1,false),
            new Option("y1",1,false),
            new Option("x2",1,false),
            new Option("y2",1,false),
            new Option("t",1,false)
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
    REPAIR("repair", new ArrayList<>(
    )),
    OPEN_GATE("open gate", new ArrayList<>(
    )),
    CLOSE_GATE("close gate", new ArrayList<>(
    )),
    CHANGE_OUTPUT("change output", new ArrayList<>(List.of(
            new Option("r", 1, true)
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

    private final String commandRegex;
    private final ArrayList<Option> options;

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
