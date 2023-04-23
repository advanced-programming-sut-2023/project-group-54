package view.commands.inputs;

public enum ProfileMenuCommands {

    REGISTER("");
    private final String regex;

    private ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
