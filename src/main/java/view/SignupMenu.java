package view;

import controller.*;
import view.commands.CommonEnums;
import view.commands.inputs.SignupMenuCommands;
import view.commands.outputs.SignupMenuOutput;

import java.net.SocketTimeoutException;
import java.util.regex.Matcher;

public class SignupMenu {
    private SignupController signupController;

    public SignupMenu() {
        this.signupController = new SignupController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("\\s*back\\s*")) return "menu";
            else if (command.matches(CommonEnums.SHOW_MENU.getRegex())) System.out.println("Sign up menu");
            else if ((matcher = Menu.getMatcher(command, SignupMenuCommands.REGISTER.getRegex())) != null) createUser(matcher);
            else System.out.println("Invalid command in sign up menu");
        }
    }

    private void createUser(Matcher matcher) {
        String username = Controller.buildParameter(matcher.group("username"));
        String password = Controller.buildParameter(matcher.group("password"));
        String passwordConfirmation = Controller.buildParameter(matcher.group("passwordConfirmation"));
        String email = Controller.buildParameter(matcher.group("email"));
        String nickname = Controller.buildParameter(matcher.group("nickname"));
        String slogan = Controller.buildParameter(matcher.group("slogan"));
        SignupMenuOutput result = signupController.setUser(username, password, passwordConfirmation, nickname, email, slogan);
        SignupMenuOutput secondResult = SignupMenuOutput.NOTHING;
        switch (result) {
            case RANDOM_PASSWORD_BUILT:
                if (slogan.equals("random")) System.out.println("Your slogan is:  " + signupController.getSlogan());
                if (randomPasswordConfirmation().equals(SignupMenuOutput.PASSWORD_AND_PASSWORD_CON_SAME))
                    secondResult = securityQuestion();
                else System.out.println(SignupMenuOutput.PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED.getRegex());
                break;
            case SECURITY_QUESTION:
                if (slogan.equals("random")) System.out.println("Your slogan is:  " + signupController.getSlogan());
                System.out.println(result.getRegex());
                secondResult = securityQuestion();
                break;
            default:
                System.out.println(result.getRegex());
                break;
        }
        if (secondResult.equals(SignupMenuOutput.USER_CREATED)) {
            System.out.println(secondResult.getRegex()+signupController.getUsername());
            secondResult = chooseMap();
        }
        System.out.println(secondResult);
    }

    private SignupMenuOutput randomPasswordConfirmation() {
        System.out.println(SignupMenuOutput.RANDOM_PASSWORD_BUILT.getRegex() + signupController.getPassword() + "\n" +
                SignupMenuOutput.RANDOM_PASSWORD_REENTER);
        String passwordConfirmation = Menu.getScanner().nextLine();
        int i = 1;
        while (!passwordConfirmation.equals(signupController.getPassword()) &&
                i < 4) {
            System.out.println(SignupMenuOutput.PASSWORD_WRONG_CONFIRMATION.getRegex() + (i*5) + " seconds");
            passwordConfirmation = Controller.buildParameter(Menu.getScanner().nextLine());
            i++;
            Controller.timer(i-1);
        }
        if (passwordConfirmation.equals(signupController.getPassword()))
            return SignupMenuOutput.PASSWORD_AND_PASSWORD_CON_SAME;
        else return SignupMenuOutput.PASSWORD_AND_PASSWORD_CON_NOT_EQUAL_FAILED;
    }

    private SignupMenuOutput securityQuestion() {
        String command;
        Matcher matcher = null;
        boolean sign = true;
        for (int i = 0; i < 3; i++) {
            command = Menu.getScanner().nextLine();
            if ((matcher = Menu.getMatcher(command, SignupMenuCommands.QUESTION_PICK.getRegex())) != null) {
                sign = false;
                break;
            }
            System.out.println(SignupMenuOutput.INVALID_FORMAT_FOR_SECURITY_QUESTION.getRegex());
        }
        if (sign) return SignupMenuOutput.FAILED_DURING_PICKING_SECURITY_QUESTION;
        int securityQuestionNumber = Integer.parseInt(matcher.group("questionNumber"));
        String answer = Controller.buildParameter(matcher.group("answer"));
        String answerConfirmation = Controller.buildParameter(matcher.group("answerConfirmation"));
        return registrationAfterAnswerChecker(securityQuestionNumber,answer,answerConfirmation);
    }

    private SignupMenuOutput registrationAfterAnswerChecker(int securityQuestionNumber,String answer,String answerConfirmation) {
        boolean sign = true;
        String command = "";
        for (int i = 0; i < 3; i++) {
            if (securityQuestionNumber >= 1 &&
                    securityQuestionNumber <= 3) {
                sign = false;
                break;
            }
            System.out.println(SignupMenuOutput.INVALID_QUESTION_NUMBER.getRegex() + "please wait for " + ((i+1) * 5) + " seconds");
            Controller.timer(i);
            command = Menu.getScanner().nextLine();
            if (command.matches("-*\\d+")) securityQuestionNumber = Integer.parseInt(command);
        }
        if (sign) return SignupMenuOutput.FAILED_DURING_PICK_QUESTION_NUMBER;
        for (int i = 0; i < 3; i++) {
            if (answer.equals(answerConfirmation)) {
                if (!Menu.captchaChecker()) return SignupMenuOutput.FAILED_DURING_CAPTCHA;
                return signupController.createUser(securityQuestionNumber, answer, answerConfirmation);
            }
            System.out.println(SignupMenuOutput.INVALID_ANSWER_CONFIRMATION.getRegex());
            answerConfirmation = Controller.buildParameter(Menu.getScanner().nextLine());
        }
        return SignupMenuOutput.FAILED_DURING_ENTERING_ANSWER_CONFIRMATION;
    }


    private SignupMenuOutput chooseMap() {
        int mapNumber;
        System.out.println("please choose your map number:");
        //maps properties
        for (int i = 0; i < 3; i++) {
            mapNumber = Menu.getScanner().nextInt();
            // id ont know how many maps we have for now:
            if (mapNumber >= 1 && mapNumber <= 5) return signupController.chooseMap(mapNumber);
            System.out.println(SignupMenuOutput.MAP_INVALID_NUMBER.getRegex());
        }
        System.out.println(SignupMenuOutput.MAP_INVALID_NUMBER_OVER.getRegex());
        return signupController.chooseMap(1);
    }
}
