package controller;

import com.google.common.hash.Hashing;
import model.Government;
import model.User;
import view.enums.messages.SignupMenuMessage;

import java.nio.charset.StandardCharsets;

public class SignupMenuController {
    private static String username;
    private static String password;
    private static String passwordConfirmation;

    public static String getUsername() {
        return username;
    }

    private static String nickname;
    private static String email;
    private static String slogan;

    public static SignupMenuMessage setUser(String username, String password, String passwordConfirmation, String nickname, String email, String slogan) {
        SignupMenuController.username = username;
        SignupMenuController.password = password;
        SignupMenuController.passwordConfirmation = passwordConfirmation;
        SignupMenuController.nickname = nickname;
        SignupMenuController.email = email;
        SignupMenuController.slogan = slogan;
        return createUserChecker();
    }

    public static String getPassword() {
        return password;
    }

    public static String getSlogan() {
        return slogan;
    }

    public static SignupMenuMessage createUser(int questionNumber, String questionAnswer) {
        passwordToHash();
        User user = new User(username, password, nickname, email, slogan, questionNumber, questionAnswer, new Government());
        User.addUser(user);
//        Controller.setLoggedInUser(user);

        //add to file
        return SignupMenuMessage.SUCCESS;
    }

//    public static SignupMenuMessage chooseMap(int mapNumber) {
//        // to complete
//        return SignupMenuMessage.MAP_SELECTED;
//    }

    public static SignupMenuMessage createUserChecker() {
        SignupMenuMessage usernameChecker = Controller.checkUsernameValidity(username);
        if (!usernameChecker.equals(SignupMenuMessage.SUCCESS))
            return usernameChecker;
        if(!password.equals("random")){
            SignupMenuMessage passwordChecker = Controller.checkPasswordValidity(password);
            if (!passwordChecker.equals(SignupMenuMessage.SUCCESS))
                return passwordChecker;
        }

        if (!password.equals("random") && !password.equals(passwordConfirmation))
            return SignupMenuMessage.PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED;

        SignupMenuMessage emailChecker = Controller.checkEmailValidity(email);
        if (!emailChecker.equals(SignupMenuMessage.SUCCESS)) {
            return emailChecker;
        }

        if (slogan != null &&slogan.equals("random"))
            randomSloganSetter();

        if (password.equals("random"))
            return createRandomPassword();

        return SignupMenuMessage.SECURITY_QUESTION;
    }

    public static SignupMenuMessage createRandomPassword() {
        char[] randomPassword = new char[15];
        randomPassword[0] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomPassword[1] = (char) Math.floor(Math.random() * (122 - 97 + 1) + 97);
        randomPassword[2] = (char) Math.floor(Math.random() * (57 - 48 + 1) + 48);
        randomPassword[3] = (char) Math.floor(Math.random() * (47 - 33 + 1) + 33);
        for (int i = 4; i < 15; i++)
            randomPassword[i] = (char) Math.floor(Math.random() * (126 - 33 + 1) + 33);
        password = new String(randomPassword);
        return SignupMenuMessage.RANDOM_PASSWORD_BUILT;
    }

    public static void randomSloganSetter() {
        int random = (int) Math.floor(Math.random() * (5 + 1));
        switch (random) {
            case 0 -> slogan = "Leave one wold alive and sheep are never safe";
            case 1 -> slogan = "Some battles are won with swords and spears, others with quills and ravens";
            case 2 -> slogan = "a very small man can cast a very large shadow";
            case 3 -> slogan = "When you play the game of thrones, you win or you die";
            case 4 -> slogan = "Winter is coming...";
            case 5 -> slogan = "Fear cuts deeper than swords";
        }
    }

    private static void passwordToHash() {
        password = Controller.hashString(password);
    }
}
