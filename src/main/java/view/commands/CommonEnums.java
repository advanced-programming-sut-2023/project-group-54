package view.commands;

public enum CommonEnums {
    BACK("\\s*back\\s*"),
    EXIT("\\s*exit\\s*"),
    SHOW_MENU("\\s*show\\s+current\\s+menu\\s*"),
    ENTER_CAPTCHA("please enter captcha (only uppercase letters):"),
    WRONG_CAPTCHA("you entered captcha wrong you have "),
    FAILED_DURING_CAPTCHA("failed because of multiple wrong answers for captcha")
    ;

    private final String regex;

    private CommonEnums(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
