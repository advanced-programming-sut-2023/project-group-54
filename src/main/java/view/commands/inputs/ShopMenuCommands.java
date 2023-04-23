package view.commands.inputs;

public enum ShopMenuCommands {
    REGISTER("");
    private final String regex;

    private ShopMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
