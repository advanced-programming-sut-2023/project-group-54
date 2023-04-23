package view.commands.outputs;

public enum LoginMenuOutput {
    USER_NOT_FOUND("no user with this id exists"),

    WRONG_PASSWORD("wrong password for this username please wait for "),

    LOGGED_IN("logged in with user with username: "),

    LOGIN_FAILED("you have entered wrong password for 3 times : login failed"),

    NEW_PASSWORD_SET("new password has been set"),

    WRONG_ANSWER("you have entered wrong answer for security question please wait for "),

    FAILED_DURING_SETTING_NEW_PASSWORD("setting new password failed you have entered 3 wrong formats for password"),

    FAILED_DURING_ANSWERING_QUESTION("setting new password failed you have entered 3 wrong answers for security question"),

    LOGGED_OUT_FIRST_PART("user with username : "),

    LOGGED_OUT(" logged out"),

    FAILED_DURING_CAPTCHA("failed because of multiple wrong answers for captcha")
    ;
    private final String regex;

    private LoginMenuOutput(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
