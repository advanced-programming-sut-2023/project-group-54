package controller;
import model.*;
import view.commands.inputs.SignupMenuCommands;
import view.commands.outputs.LoginMenuOutput;
import view.commands.outputs.SignupMenuOutput;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;

public class SignupController {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String email;
    private String slogan;

    public SignupMenuOutput setUser(String username,String password,String passwordConfirmation,String nickname,String email,String slogan) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        return checkFieldsNotEmpty();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSlogan() {
        return slogan;
    }

    public SignupMenuOutput checkFieldsNotEmpty() {
        if (passwordConfirmation == null && !password.equals("random")) return SignupMenuOutput.PASSWORD_CONFIRMATION_NOT_ENTERED;
        if (passwordConfirmation != null && password.equals("random")) return SignupMenuOutput.PASSWORD_CONFIRMATION_NOT_NEEDED;
        if (username.equals("")) return SignupMenuOutput.EMPTY_USERNAME;
        if (password.equals("")) return SignupMenuOutput.EMPTY_PASSWORD;
        if (passwordConfirmation.equals("")) return SignupMenuOutput.EMPTY_PASSWORD_CONFIRMATION;
        if (email.equals("")) return SignupMenuOutput.EMPTY_EMAIL;
        if (nickname.equals("")) return SignupMenuOutput.EMPTY_NICKNAME;
        if (slogan.equals("")) return SignupMenuOutput.EMPTY_SLOGAN;
        return createUserChecker();
    }

    public SignupMenuOutput createUserChecker() {
        if (!username.matches(SignupMenuCommands.USERNAME.getRegex()))return SignupMenuOutput.WRONG_FORMAT_USERNAME;
        if (Controller.findUserByUsername(username) != null) return SignupMenuOutput.USERNAME_EXIST;
        SignupMenuOutput passwordChecker = Controller.checkPasswordValidity(password);
        if (!passwordChecker.equals(SignupMenuOutput.PASSWORD_OK)) return passwordChecker;
        if (!password.equals("random") &&
                !password.equals(passwordConfirmation)) return SignupMenuOutput.PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED;
        if (Controller.findEmail(email) != null) return SignupMenuOutput.EMAIL_EXIST;
        if (!email.matches(SignupMenuCommands.EMAIL.getRegex())) return SignupMenuOutput.WRONG_FORMAT_EMAIL;
        if (slogan.equals("random")) randomSloganSetter();
        if (password.equals("random")) return createRandomPassword();
        return SignupMenuOutput.SECURITY_QUESTION;
    }

    public SignupMenuOutput createRandomPassword() {
        char[] randomPassword = new char[15];
        randomPassword[0] = (char)Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomPassword[1] = (char)Math.floor(Math.random() * (122 - 97 + 1) + 97);
        randomPassword[2] = (char)Math.floor(Math.random() * (57 - 48 + 1) + 48);
        randomPassword[3] = (char)Math.floor(Math.random() * (47 - 33 + 1) + 33);
        for (int i = 4; i < 15; i++)
             randomPassword[i] = (char)Math.floor(Math.random() * (126 - 33 + 1) + 33);
        password = Arrays.toString(randomPassword);
        return SignupMenuOutput.RANDOM_PASSWORD_BUILT;
    }

    public void randomSloganSetter() {
        int random = (int)Math.floor(Math.random() * (5 + 1) );
        switch (random) {
            case 0:
                slogan = "Leave one wold alive and sheep are never safe";
                break;
            case 1:
                slogan = "Some battles are won with swords and spears, others with quills and ravens";
                break;
            case 2:
                slogan = "a very small man can cast a very large shadow";
                break;
            case 3:
                slogan = "When you play the game of thrones, you win or you die";
                break;
            case 4:
                slogan = "Winter is coming...";
                break;
            case 5:
                slogan = "Fear cuts deeper than swords";
                break;
        }
    }

    public SignupMenuOutput createUser(int questionNumber,String questionAnswer,String repetition) {
        passwordToHash();
        Game.addUsers(new User(username,password,nickname,email,slogan,questionNumber,questionAnswer));
        //add to file
        return SignupMenuOutput.USER_CREATED;
    }

    private void passwordToHash() {
        //to complete
    }

    public SignupMenuOutput chooseMap(int mapNumber){
        // to complete
        return SignupMenuOutput.MAP_SELECTED;
    }
}
