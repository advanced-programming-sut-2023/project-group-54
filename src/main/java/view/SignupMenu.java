package view;

import controller.Controller;
import controller.SignupMenuController;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.SignupMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class SignupMenu {
    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            System.out.println(command);
            if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("sign up menu");
            else if ((options = CommandHandler.parsCommand(Command.REGISTER, command)) != null)
                createUser(options);
            else if ((options = CommandHandler.parsCommand(Command.REGISTER_WITH_RANDOM_PASSWORD, command)) != null)
                createUser(options);
            else
                System.out.println("Invalid command in sign up menu");
        }
    }

    private void createUser(HashMap<String, ArrayList<String>> options) {
        String username = null;
        String password = null;
        String passwordConfirmation = null;
        String email = null;
        String nickname = null;
        String slogan = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    username = Controller.buildParameter(options.get(s).get(0));
                    break;

                case "p":
                    password = Controller.buildParameter(options.get(s).get(0));
                    if (options.get(s).size() > 1) {
                        passwordConfirmation = Controller.buildParameter(options.get(s).get(1));
                    }
                    break;

                case "e":
                    email = Controller.buildParameter(options.get(s).get(0));
                    break;
                case "n":
                    nickname = Controller.buildParameter(options.get(s).get(0));
                    break;
                case "s":
                    slogan = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (username == null) {
            System.out.println("username not entered");
            return;
        }
        if (password == null) {
            System.out.println("password not entered");
            return;
        }
        if (passwordConfirmation == null && !password.equals("random")) {
            System.out.println("password confirmation not entered");
            return;
        }
        if (email == null) {
            System.out.println("email not entered");
            return;
        }
        if (nickname == null) {
            System.out.println("nickname not entered");
            return;
        }
        SignupMenuMessage result = SignupMenuController.setUser(username, password, passwordConfirmation, nickname, email, slogan);
        String secondResult = "";
        switch (result) {
            case RANDOM_PASSWORD_BUILT:
                if (slogan.equals("random"))
                    System.out.println("Your slogan is:  " + SignupMenuController.getSlogan());

                if (randomPasswordConfirmation().equals(SignupMenuMessage.PASSWORD_AND_PASSWORD_CON_SAME)) {
                    System.out.println("""
                            Pick your security question:
                            1. What is my father’s name?
                            2. What was my first pet’s name?
                            3. What is my mother’s last name?""");

                    secondResult = securityQuestion();
                } else
                    System.out.println("password and password confirmation are not equal registration failed");

                break;

            case SECURITY_QUESTION:
                if (slogan.equals("random"))
                    System.out.println("Your slogan is:  " + SignupMenuController.getSlogan());

                System.out.println("""
                        Pick your security question:
                        1. What is my father’s name?
                        2. What was my first pet’s name?
                        3. What is my mother’s last name?""");

                secondResult = securityQuestion();

                break;

            case EMAIL_EXIST:
                System.out.println("this email already exists");
                break;
            case WRONG_FORMAT_USERNAME:
                System.out.println("invalid format for username");
                break;
            case USERNAME_EXIST:
                System.out.println("this username already exists");
                break;
            case WRONG_FORMAT_PASSWORD_LENGTH:
                System.out.println("password length is too low at least 6 is needed");
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                System.out.println("your password should contain uppercase and lowercase letters and numbers");
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                System.out.println("your password should contain special letters");
                break;
            case PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED:
                System.out.println("password and password confirmation are not equal registration failed");
                break;
            case WRONG_FORMAT_EMAIL:
                System.out.println("invalid format for email");
                break;
        }
        if (secondResult.equals("success")) {
            System.out.println("user created successfully with username " + Controller.getLoggedInUser().getUsername());
            chooseMap();
            secondResult = "";
        }
        if(!secondResult.isEmpty())
            System.out.println(secondResult);
    }

    private SignupMenuMessage randomPasswordConfirmation() {
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

    private String securityQuestion() {
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

    private SignupMenuMessage registrationAfterAnswerChecker(int securityQuestionNumber, String answer, String answerConfirmation) {
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

    private void chooseMap() {
        int mapNumber;
        System.out.println("please choose your map number:");
        //maps properties
        for (int i = 0; i < 3; i++) {
            mapNumber = Integer.parseInt(MainMenu.getScanner().nextLine());
            // I don't know how many maps we have for now:
            if (mapNumber >= 1 && mapNumber <= 5){
                if(SignupMenuController.chooseMap(mapNumber).equals(SignupMenuMessage.MAP_SELECTED)){
                    System.out.println("map selected successfully");
                    return;
                }
            }
            System.out.println("please enter valid number");
        }
        System.out.println("you entered 3 invalid numbers map number 1 will be selected for you");
        if(SignupMenuController.chooseMap(1).equals(SignupMenuMessage.MAP_SELECTED))
            System.out.println("map selected successfully");
    }
}
