package view.commands.inputs;

public enum TradeMenuCommands {
    REGISTER("");
    private final String regex;

    private TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
