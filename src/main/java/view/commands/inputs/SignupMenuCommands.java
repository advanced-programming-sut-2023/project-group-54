package view.commands.inputs;

public enum SignupMenuCommands {
    REGISTER("^(\\s*user\\s+create\\s*)(?=.*(-u\\s+(?<username>(([^\"\\s-]+)|(\"[^\"]+\"))?)))(?=.*(-p\\s+" +
            "(?<password>(([^\"\\s-]+)|(\"[^\"]+\"))?)))(?=.*(?<passwordConfirmation>(([^\"\\s-]+)|(\"[^\"]+\"))?))*" +
            "(?=.*(-email\\s+(?<email>([^\"\\s-]+)|(\"[^\"]+\"))))(?=.*(-n\\s+(?<nickname>(([^\"\\s-]+)|(\"[^\"]+\"))?)))" +
            "(?=.*(-s\\s+(?<slogan>(([^\"\\s-]+)|(\"[^\"]+\"))?)))*"),
    USERNAME("^[A-Za-z_]+$"),
    Password_LENGTH("(?=^\\S{6,}$)"),
    PASSWORD_LETTER("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])"),
    PASSWORD_SPECIAL_LETTER("(?=.*[^A-Za-z0-9])"),
    EMAIL("^[A-Za-z_.0-9]+@(?<u>[A-Za-z_0-9]+)\\.(?<a>[A-Za-z_0-9]+)$"),
    QUESTION_PICK("^(\\s*question\\s+pick\\s*)(?=.*(-q\\s+(?<questionNumber>(-*\\d+)?)))(?=.*(-a\\s+(?<answer>(([^\"\\s-]+)" +
            "|(\"[^\"]+\"))?)))(?=.*(-c\\s+(?<answerConfirmation>(([^\"\\s-]+)|(\"[^\"]+\"))?)))");
    private final String regex;

    private SignupMenuCommands(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
