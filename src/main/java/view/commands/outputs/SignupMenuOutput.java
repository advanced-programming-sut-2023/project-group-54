package view.commands.outputs;

public enum SignupMenuOutput {
    PASSWORD_CONFIRMATION_NOT_ENTERED("you need to enter your password confirmation"),

    PASSWORD_CONFIRMATION_NOT_NEEDED("you have entered password confirmation while it was not needed because you entered random for password"),

    EMPTY_USERNAME("username field is empty"),

    EMPTY_PASSWORD("password field is empty"),

    EMPTY_PASSWORD_CONFIRMATION("password confirmation field is empty"),

    EMPTY_EMAIL("email field is empty"),

    EMPTY_NICKNAME("nickname field is empty"),

    EMPTY_SLOGAN("slogan field is empty"),

    WRONG_FORMAT_USERNAME("invalid format for username"),

    WRONG_FORMAT_PASSWORD_LENGTH("password length is too low at least 6 is needed"),

    WRONG_FORMAT_PASSWORD_LETTERS("your password should contain uppercase and lowercase letters and numbers"),

    WRONG_FORMAT_PASSWORD_SPECIAL("your password should contain special letters"),

    WRONG_FORMAT_EMAIL("invalid format for email"),

    RANDOM_PASSWORD_BUILT("your random password is:  "),

    RANDOM_PASSWORD_REENTER("please reenter your password:"),

    PASSWORD_WRONG_CONFIRMATION("entered password confirmation is not equal to password please wait for "),

    USERNAME_EXIST("this username already exists"),

    EMAIL_EXIST("this email already exists"),

    PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED("password and password confirmation are not equal registration failed"),

    PASSWORD_OK("password is strong enough"),

    SECURITY_QUESTION("Pick your security question:\n1. What is my father’s name?\n2. What was my first pet’s name?\n" +
            "3. What is my mother’s last name?"),

    PASSWORD_AND_PASSWORD_CON_SAME("successful"),

    FAILED_DURING_PICKING_SECURITY_QUESTION("you entered wrong format for security question 3 times registration failed"),

    INVALID_FORMAT_FOR_SECURITY_QUESTION("invalid format for picking security question"),

    INVALID_QUESTION_NUMBER("please enter number between 1 to 3 just enter number now"),

    FAILED_DURING_PICK_QUESTION_NUMBER("you entered wrong format for security question number 3 times registration failed"),

    INVALID_ANSWER_CONFIRMATION("re enter the answer confirmation,it is not equal with answer "),

    FAILED_DURING_ENTERING_ANSWER_CONFIRMATION("you entered wrong answer confirmation 3 times registration failed"),

    USER_CREATED("user created successfully with username "),

    MAP_SELECTED("map selected successfully"),

    MAP_INVALID_NUMBER("please enter valid number"),

    MAP_INVALID_NUMBER_OVER("you entered 3 invalid numbers map number 1 will be selected for you"),

    FAILED_DURING_CAPTCHA("failed because of multiple wrong answers for captcha"),

    NOTHING("")
    ;

    private final String regex;

    private SignupMenuOutput(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
