package com.ap.stronghold.view.enums.commands;

public enum Regexes {
    MAP_SHOW_MOVE("^\\s+map\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$"),
    MAP_DETAILS_MOVE("^\\s+map\\s+detail\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$"),
    SEND_MESSAGE("send\\sa\\smessage\\sc\\s(?<message>.+)"),
    ADD_MEMBER("add\\smember\\si\\s(?<id>\\S+)"),
    SHOW_ALL_MESSAGES("show\\sall\\smessages"),
    SHOW_ALL_MEMBERS("show\\sall\\smembers"),
    EDIT_MESSAGE("edit\\smessage\\s-n\\s(?<number>\\d+)"),
    DELETE_MESSAGE("delete\\smessage\\s-n\\s(?<number>\\d+)"),
    PUT_REACTION("put\\sreaction\\s-m\\s(?<reaction>\\S+)\\s-n\\s(?<number>\\d+)"),
    SHOW_FRIENDSHIP_REQUESTS("show\\sfriendship\\srequests"),
    CREATE_GROUP("create group -i (?<id>\\S+) -n (?<name>\\S+)"),
    CREATE_PRIVATE_CHAT("create private chat -i (?<id>\\S+) -n (?<name>\\S+)");

    private final String regex;

    private Regexes(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
