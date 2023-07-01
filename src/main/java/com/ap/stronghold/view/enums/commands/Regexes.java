package com.ap.stronghold.view.enums.commands;

public enum Regexes {
    MAP_SHOW_MOVE("^\\s+map\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$"),
    MAP_DETAILS_MOVE("^\\s+map\\s+detail\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$"),
    SEND_MESSAGE("send\\sa\\smessage\\sc\\s(?<message>.+)"),
    ADD_MEMBER("add\\smember\\si\\s(?<id>\\S+)"),
    SHOW_ALL_MESSAGES("show\\sall\\smessages"),
    SHOW_ALL_MEMBERS("show\\sall\\smembers");

    private final String regex;

    private Regexes(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
