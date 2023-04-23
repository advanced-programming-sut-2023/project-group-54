package view.commands.inputs;

public enum GameMenuCommands {
    REGISTER("");
    private final String regex;

    private GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
