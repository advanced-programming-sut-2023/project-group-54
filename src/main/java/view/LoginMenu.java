package view;
import controller.*;

import java.util.regex.Matcher;

public class LoginMenu {
    private LoginController loginController;

    public LoginMenu() {
        this.loginController = new LoginController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while (true) {
//            command = Menu.getScanner().nextLine();
            //if command is back
            //if command is show current menu
            //if command is login
            //if command is forgot password
            //if command is logout
        }
    }

    private void loginUser(Matcher matcher) {

    }

    private void forgetPassword(Matcher matcher) {

    }

    private void logout() {

    }
}
