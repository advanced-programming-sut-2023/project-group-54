package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.SignupMenuController;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SignupMenu extends Application {

    public TextField nickname;
    public PasswordField password;
    public TextField username;
    public TextField email;
    public ChoiceBox recoveryQuestion;
    public Text message;
    public Text usernameCheck;
    public Text passwordCheck;
    public CheckBox haveSlogan;
    public TextField slogan;
    public ToggleGroup commonSlogan;
    public Button randomSlogan;
    public Text nicknameCheck;
    public Text emailCheck;

    public static void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        System.out.println("you are in sign up menu");
        while (true) {
            command = MainMenu.getScanner().nextLine();
//            if ((options = CommandHandler.parsCommand(Command.REGISTER, command)) != null)
//                createUser(options);
//            else if ((options = CommandHandler.parsCommand(Command.REGISTER_WITH_RANDOM_PASSWORD, command)) != null)
//                createUser(options);
//            else
                System.out.println("Invalid command in sign up menu");
        }
    }
//
//    private static void createUser(HashMap<String, ArrayList<String>> options) {
//        String username = null;
//        String password = null;
//        String passwordConfirmation = null;
//        String email = null;
//        String nickname = null;
//        String slogan = null;
//        for (String s : options.keySet()) {
//            switch (s) {
//                case "u":
//                    username = Controller.buildParameter(options.get(s).get(0));
//                    break;
//
//                case "p":
//                    password = Controller.buildParameter(options.get(s).get(0));
//                    if (options.get(s).size() > 1) {
//                        passwordConfirmation = Controller.buildParameter(options.get(s).get(1));
//                    }
//                    break;
//
//                case "e":
//                    email = Controller.buildParameter(options.get(s).get(0));
//                    break;
//                case "n":
//                    nickname = Controller.buildParameter(options.get(s).get(0));
//                    break;
//                case "s":
//                    slogan = Controller.buildParameter(options.get(s).get(0));
//                    break;
//            }
//        }
//        if (username == null) {
//            System.out.println("username not entered");
//            return;
//        }
//        if (password == null) {
//            System.out.println("password not entered");
//            return;
//        }
//        if (passwordConfirmation == null && !password.equals("random")) {
//            System.out.println("password confirmation not entered");
//            return;
//        }
//        if (email == null) {
//            System.out.println("email not entered");
//            return;
//        }
//        if (nickname == null) {
//            System.out.println("nickname not entered");
//            return;
//        }
//        SignupMenuMessage result = SignupMenuController.setUser(username, password, passwordConfirmation, nickname, email, slogan);
//        String secondResult = "";
//        switch (result) {
//            case RANDOM_PASSWORD_BUILT:
//                if (slogan != null && slogan.equals("random"))
//                    System.out.println("Your slogan is:  " + SignupMenuController.getSlogan());
//
//                if (randomPasswordConfirmation().equals(SignupMenuMessage.PASSWORD_AND_PASSWORD_CON_SAME)) {
//                    System.out.println("""
//                            Pick your security question:
//                            1. What is my father’s name?
//                            2. What was my first pet’s name?
//                            3. What is my mother’s last name?""");
//
//                    secondResult = securityQuestion();
//                } else
//                    System.out.println("password and password confirmation are not equal registration failed");
//
//                break;
//
//            case SECURITY_QUESTION:
//                if (slogan != null &&slogan.equals("random"))
//                    System.out.println("Your slogan is:  " + SignupMenuController.getSlogan());
//
//                System.out.println("""
//                        Pick your security question:
//                        1. What is my father’s name?
//                        2. What was my first pet’s name?
//                        3. What is my mother’s last name?""");
//
//                secondResult = securityQuestion();
//
//                break;
//
//            case EMAIL_EXIST:
//                System.out.println("this email already exists");
//                break;
//            case WRONG_FORMAT_USERNAME:
//                System.out.println("invalid format for username");
//                break;
//            case USERNAME_EXIST:
//                System.out.println("this username already exists");
//                break;
//            case WRONG_FORMAT_PASSWORD_LENGTH:
//                System.out.println("password length is too low at least 6 is needed");
//                break;
//            case WRONG_FORMAT_PASSWORD_LETTERS:
//                System.out.println("your password should contain uppercase and lowercase letters and numbers");
//                break;
//            case WRONG_FORMAT_PASSWORD_SPECIAL:
//                System.out.println("your password should contain special letters");
//                break;
//            case PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED:
//                System.out.println("password and password confirmation are not equal registration failed");
//                break;
//            case WRONG_FORMAT_EMAIL:
//                System.out.println("invalid format for email");
//                break;
//        }
//        if (secondResult.equals("success")) {
//            System.out.println("user created successfully with username " + username);
//            User.saveUser();
////            chooseMap();
//            secondResult = "";
//        }
//        if(!secondResult.isEmpty())
//            System.out.println(secondResult);
//    }

    private static SignupMenuMessage randomPasswordConfirmation() {
        System.out.println("your random password is: " + SignupMenuController.getPassword() + "\n" + "please reenter your password: ");
        String passwordConfirmation = MainMenu.getScanner().nextLine();
        int i = 1;
        while (!passwordConfirmation.equals(SignupMenuController.getPassword()) && i < 4) {
            System.out.println("entered password confirmation is not equal to password please wait for " + (i * 5) + " seconds");
            passwordConfirmation = Controller.buildParameter(MainMenu.getScanner().nextLine());
            i++;
            Controller.timer(i - 2);
        }

        if (passwordConfirmation.equals(SignupMenuController.getPassword()))
            return SignupMenuMessage.PASSWORD_AND_PASSWORD_CON_SAME;
        else
            return SignupMenuMessage.PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED;
    }

    private static String securityQuestion() {
        String command;
        HashMap<String, ArrayList<String>> options = new HashMap<>();
        boolean sign = true;
        for (int i = 0; i < 3; i++) {
            command = MainMenu.getScanner().nextLine();
            if ((options = CommandHandler.parsCommand(Command.QUESTION_PICK, command)) != null) {
                sign = false;
                break;
            }
            System.out.println("invalid format for picking security question, try again");
        }
        if (sign)
            return "you entered wrong format for security question 3 times registration failed";

        int questionNumber = 0;
        String answer = null;
        String answerConfirm = null;

        for (String s : options.keySet()) {
            switch (s) {
                case "q" -> questionNumber = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                case "a" -> answer = Controller.buildParameter(options.get(s).get(0));
                case "c" -> answerConfirm = Controller.buildParameter(options.get(s).get(0));
            }
        }

        if (answer == null) {
            return "answer not entered";
        }

        if (answerConfirm == null) {
            return "answer confirmation not entered";
        }

        SignupMenuMessage result = registrationAfterAnswerChecker(questionNumber, answer, answerConfirm);
        switch (result) {
            case FAILED_DURING_PICK_QUESTION_NUMBER -> {
                return "you entered wrong format for security question number 3 times registration failed";
            }
            case FAILED_DURING_CAPTCHA -> {
                return "failed because of multiple wrong answers for captcha";
            }
            case FAILED_DURING_ENTERING_ANSWER_CONFIRMATION -> {
                return "you entered wrong answer confirmation 3 times registration failed";
            }
            case SUCCESS -> {
                return "success";
            }
        }
        return "";
    }

    private static SignupMenuMessage registrationAfterAnswerChecker(int securityQuestionNumber, String answer, String answerConfirmation) {
        boolean sign = true;
        String command = "";
        for (int i = 0; i < 3; i++) {
            if (securityQuestionNumber >= 1 && securityQuestionNumber <= 3) {
                sign = false;
                break;
            }
            System.out.println("please enter number between 1 to 3 just enter number now " + "please wait for " + ((i + 1) * 5) + " seconds");
            Controller.timer(i);
            command = MainMenu.getScanner().nextLine();
            if (command.matches("-\\d+"))
                securityQuestionNumber = Integer.parseInt(command);
        }
        if (sign) return SignupMenuMessage.FAILED_DURING_PICK_QUESTION_NUMBER;
        for (int i = 0; i < 3; i++) {
            if (answer.equals(answerConfirmation)) {
                if (!MainMenu.captchaChecker())
                    return SignupMenuMessage.FAILED_DURING_CAPTCHA;
                return SignupMenuController.createUser(securityQuestionNumber, answer);
            }
            System.out.println("re enter the answer confirmation,it is not equal with answer");
            answerConfirmation = Controller.buildParameter(MainMenu.getScanner().nextLine());
        }
        return SignupMenuMessage.FAILED_DURING_ENTERING_ANSWER_CONFIRMATION;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/signUpMenu.fxml"));
        Scene scene = new Scene(pane);

        stage.setScene(scene);
    }

    public void back() throws Exception {
        (new MainMenu()).start(MainMenu.stage);
    }

    public void signUp() {
        SignupMenuMessage result = SignupMenuController.setUser(username.getText()
                , password.getText(), nickname.getText(), email.getText(), slogan.getText());
        switch (result) {
            case SECURITY_QUESTION:

//                secondResult = securityQuestion();

                break;
        }
        initialize();
    }

    public void changeHaveSlogan() {
        if(!haveSlogan.isSelected()){
            slogan.setVisible(false);
            slogan.setText("");
            randomSlogan.setVisible(false);
            commonSlogan.selectToggle(null);
        }else{
            slogan.setVisible(true);
            randomSlogan.setVisible(true);
        }
    }

    public void changeRandomSlogan() {
        slogan.setText(SignupMenuController.getRandomSlogan());
        commonSlogan.selectToggle(null);
    }

    public void commonSloganSelect() {
        slogan.setText(commonSlogan.getSelectedToggle().getUserData().toString());
    }

    public void initialize(){
        emailCheck.setFill(Color.RED);
        nicknameCheck.setFill(Color.RED);
        passwordCheck.setFill(Color.RED);
        usernameCheck.setFill(Color.RED);

        if(username.getText().isEmpty())
            usernameCheck.setText("username not entered");
        if(password.getText().isEmpty())
            passwordCheck.setText("password not entered");
        if(nickname.getText().isEmpty())
            nicknameCheck.setText("nickname not entered");
        if(email.getText().isEmpty())
            emailCheck.setText("email not entered");

        username.textProperty().addListener((observable, oldText, newText)->{
            if(username.getText().isEmpty())
                usernameCheck.setText("username not entered");
            else {
                SignupMenuMessage result = Controller.checkUsernameValidity(username.getText());
                usernameCheck.setText(getError(result));
            }
        });
        password.textProperty().addListener((observable, oldText, newText)->{
            if(password.getText().isEmpty())
                passwordCheck.setText("password not entered");
            else{
                SignupMenuMessage result = Controller.checkPasswordValidity(password.getText());
                passwordCheck.setText(getError(result));
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText)->{
            if(!nickname.getText().isEmpty())
                nicknameCheck.setText("");
        });
        email.textProperty().addListener((observable, oldText, newText)->{
            if(email.getText().isEmpty())
                emailCheck.setText("email not entered");
            else {
                SignupMenuMessage result = Controller.checkEmailValidity(email.getText());
                emailCheck.setText(getError(result));
            }
        });
    }

    public String getError(SignupMenuMessage result){
        String message = "";

        switch (result){
            case EMAIL_EXIST:
                message = "this email already exists";
                break;
            case WRONG_FORMAT_USERNAME:
                message = "invalid format for username";
                break;
            case USERNAME_EXIST:
                message = "this username already exists";
                break;
            case WRONG_FORMAT_PASSWORD_LENGTH:
                message = "password length is too low at least 6 is needed";
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                message = "your password should contain uppercase and lowercase letters and numbers";
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                message = "your password should contain special letters";
                break;
            case WRONG_FORMAT_EMAIL:
                message = "invalid format for email";
                break;
        }
        return message;
    }
}
