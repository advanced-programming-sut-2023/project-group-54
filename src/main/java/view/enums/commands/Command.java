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
            new Option("p", 2, false),
            new Option("-stay-logged-in", 0, false)
    ))),
    FORGET_PASSWORD("forgot\\s+my\\s+password", new ArrayList<>(Arrays.asList(
            new Option("u", 1, false)
    ))),
    USER_LOGOUT("user\\s+logout", new ArrayList<>(
    )),
    CHANGE_USERNAME("profile\\s+change",new ArrayList<>(Arrays.asList(
            new Option("u",1,true)
    ))),
    CHANGE_NICKNAME("profile\\s+change",new ArrayList<>(Arrays.asList(
            new Option("n",1,true)
    ))),
    CHANGE_PASSWORD("profile\\s+change\\s+password",new ArrayList<>(Arrays.asList(
            new Option("o",1,false),
            new Option("n",1,false)
    ))),
    CHANGE_EMAIL("profile\\s+change",new ArrayList<>(Arrays.asList(
            new Option("e",1,true)
    ))),
    CHANGE_SLOGAN("profile\\s+change\\s+slogan",new ArrayList<>(Arrays.asList(
            new Option("s",1,true)
    ))),
    REMOVE_SLOGAN("profile\\s+remove\\s+slogan",new ArrayList<>(

    )),
    DISPLAY_SLOGAN("profile\\s+display\\s+slogan",new ArrayList<>(

    )),
    DISPLAY_PROFILE("profile\\s+display",new ArrayList<>(

    )),
    PROFILE_DISPLAY_HIGH_SCORE("profile\\s+display\\s+highscore",new ArrayList<>(

    )),
    PROFILE_DISPLAY_RANK("profile\\s+display\\s+rank",new ArrayList<>(

    )),
    SHOW_PRICE_LIST("show\\s+price\\s+list",new ArrayList<>(

    )),
    SHOP_BUY("buy\\s+",new ArrayList<>(Arrays.asList(
            new Option("i",1,false),
            new Option("a",1,false)
    ))),
    TRADE("trade\\s+",new ArrayList<>(Arrays.asList(
            new Option("-t",1,false),
            new Option("-a",1,false),
            new Option("-p",1,false),
            new Option("-m",1,false)
    ))),
    TRADE_LIST("trade//s+list",new ArrayList<>(

    )),
    TRADE_ACCEPT("trade\\s+accept\\s+",new ArrayList<>(Arrays.asList(
            new Option("i",1,false),
            new Option("m",1,false)

    ))),
    TRADE_HISTORY("trade\\s+history",new ArrayList<>(

    ));



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
