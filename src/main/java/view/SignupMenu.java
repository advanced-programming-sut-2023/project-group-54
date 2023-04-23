package view;
import controller.*;

import java.util.regex.Matcher;

public class SignupMenu {
    private SignupController signupController;

    public SignupMenu() {
        this.signupController = new SignupController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while(true) {
            command = Menu.getScanner().nextLine();
            //if command is back
            // if command is to create user
            //if command is show current menu
        }
    }
    private void createUser(Matcher matcher) {

    }
    private void chooseMap(int mapNumber){

    }
}
