package view.commands.inputs;

public enum LoginMenuCommands {
    REGISTER("");
    private final String regex;

    private LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
