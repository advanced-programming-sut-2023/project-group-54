package com.ap.stronghold.view.enums.commands;

public enum Regexes {
    MAP_SHOW_MOVE("^\\s+map\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$"),
    MAP_DETAILS_MOVE("^\\s+map\\s+detail\\s+(?<upOrDown>up|down)?(?<leftOrRight>left|right)?$");
    private final String regex;

    private Regexes(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
