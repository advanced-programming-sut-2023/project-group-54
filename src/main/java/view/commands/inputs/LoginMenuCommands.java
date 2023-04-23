package view.commands.inputs;

public enum LoginMenuCommands {
    Login("^(\\s*user\\s+login\\s*)(?=.*(-u\\s+(?<username>(([^\"\\s-]+)|(\"[^\"]+\"))?)))(?=.*(-p\\s+(?<password>(([^\\\"\\\\s-]+)|" +
            "(\\\"[^\\\"]+\\\"))?)))(?=.*(?<stayLoggedIn>--stay-logged-on))*"),
    FORGET_PASSWORD("^\\s*forgot\\s+my\\s+password\\s+-u\\s+(?<username>\\S+)\\s*");
    private final String regex;

    private LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
