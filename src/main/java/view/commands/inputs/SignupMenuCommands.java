package view.commands.inputs;

public enum SignupMenuCommands {
    REGISTER("");
    private final String regex;

    private SignupMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
